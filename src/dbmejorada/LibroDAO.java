package dbmejorada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Libro;
import main.Main;

public class LibroDAO implements LibroDAOInterface{
	
	private Connection conexionBD;
	private Logger logger;
	
	public LibroDAO() {
		this.conexionBD = Main.getConexionBD();
		this.logger = Main.getLogger();
		pruebas();
	}
	

	@Override
	public boolean addLibro(Libro libro) {
		try {
            String insertSQLLibro = "INSERT INTO Libro VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmtLibro = conexionBD.prepareStatement(insertSQLLibro);
            preparedStmtLibro.setLong(1, libro.getIsbn());
            preparedStmtLibro.setString(2, libro.getTitulo());
            preparedStmtLibro.setString(3, libro.getAutor());
            preparedStmtLibro.setInt(4, libro.getNumeroDePaginas());
            preparedStmtLibro.setString(5, libro.getSinopsis());
            preparedStmtLibro.setString(6, libro.getGenero());
            preparedStmtLibro.setInt(7, libro.getRating());
            preparedStmtLibro.setInt(8, libro.getFechaPublicacion());
            
            preparedStmtLibro.executeUpdate();
            preparedStmtLibro.close();
            
		}catch(SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el libro: ", e);
            return false;
		}
		return true;
		
	}
	
	@Override
	public LibroDTO getLibro(long isbn) {
		LibroDTO libro = null;
		
		String selectSQL = "SELECT * FROM Libro WHERE isbn = ?";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setLong(1, isbn);
			 try (ResultSet rs = preparedStmt.executeQuery()) {
	                
	                while (rs.next()) {
	                	libro = new LibroDTO();
						libro.setIsbn(rs.getLong("isbn"));
						libro.setTitulo(rs.getString("titulo"));
						libro.setAutor(rs.getString("autor"));
						libro.setNumeroDePaginas(rs.getInt("número de páginas"));
						libro.setSinopsis(rs.getString("sinopsis"));
						libro.setGenero(rs.getString("género"));
						libro.setRating(rs.getInt("rating"));
						libro.setFechaPublicacion(rs.getInt("fecha publicación"));
			
						System.out.println("Libro recuperado correctamente");
		                
		    			preparedStmt.close();
		    			
	                }
			 }

		}catch(SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar el libro: ", e);
				return libro;
			}
		}
		return libro;
				
	}
	
	public void pruebas() {
		
	}
}
