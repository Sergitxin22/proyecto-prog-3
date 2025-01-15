package dbmejorada;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Cliente;
import domain.Libro;
import main.Main;
import utils.Utils;

public class LibroDAO implements LibroDAOInterface{
	
	private Connection conexionBD;
	private Logger logger;
	
	public LibroDAO() {
		this.conexionBD = Main.getConexionBD();
		this.logger = Main.getLogger();
	}
	

	@Override
	public boolean addLibro(Libro libro) {
	    String insertSQLLibro = "INSERT INTO Libro (isbn, titulo, autor, numPaginas, sinopsis, genero, rating, fecha_publicacion) VALUES (?,?,?,?,?,?,?,?)";

	    try (PreparedStatement preparedStmtLibro = conexionBD.prepareStatement(insertSQLLibro)) {
	        preparedStmtLibro.setLong(1, libro.getIsbn());
	        preparedStmtLibro.setString(2, libro.getTitulo());
	        preparedStmtLibro.setString(3, libro.getAutor());
	        preparedStmtLibro.setInt(4, libro.getNumeroDePaginas());
	        preparedStmtLibro.setString(5, libro.getSinopsis());
	        preparedStmtLibro.setString(6, libro.getGenero());
	        preparedStmtLibro.setInt(7, libro.getRating());
	        preparedStmtLibro.setInt(8, libro.getFechaPublicacion());

	        preparedStmtLibro.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        if (logger != null) {
	            logger.log(Level.SEVERE, "Error al añadir el libro: ", e);
	        }
	        return false;
	    }
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
	                libro.setNumeroDePaginas(rs.getInt("numPaginas"));
	                libro.setSinopsis(rs.getString("sinopsis"));
	                libro.setGenero(rs.getString("género"));
	                libro.setRating(rs.getInt("rating"));
	                libro.setFechaPublicacion(rs.getInt("fecha_publicacion"));
	                

	                System.out.println("Libro recuperado correctamente");
	                preparedStmt.close();
	            }
	        }
	    } catch (SQLException e) {
	        if (logger != null) {
	            logger.log(Level.SEVERE, "Error al recuperar el libro: ", e);
	            return libro;
	        }
	    }
	    return libro;
	}

	@Override
	public ArrayList<Libro> getLibros() {
		ArrayList<Libro> result = new ArrayList<>();
		
		String selectSQLibro = "SELECT * FROM Libro";
		try {
			PreparedStatement preparedStmtLibro = conexionBD.prepareStatement(selectSQLibro);
			ResultSet rs = preparedStmtLibro.executeQuery();
			while (rs.next()) {
				long isbn = rs.getLong("isbn");
				System.out.println(rs.getInt("rating"));
				Libro libro = new Libro(isbn, rs.getString("titulo"), rs.getString("autor"), rs.getInt("numPaginas"), rs.getString("sinopsis"), rs.getString("genero"), rs.getInt("rating"), rs.getInt("fecha_publicacion"), Utils.loadImage("books/" + Long.toString(isbn) + ".jpg", 98, 151), new ArrayList<>());
				libro.setReviews(Main.getReviewDAO().getReviewsLibroByIsbn(libro.getIsbn()));
				result.add(libro);
			}
		} catch (SQLException e) {
			if (logger != null) {
	            logger.log(Level.SEVERE, "Error al recuperar todos los libros: ", e);
	            return result;
	        }
		}
		return result;
	}
	
	@Override
	public void añadirReserva(long isbn, int diasDevolucion, Cliente cliente) {
		String insertSQL = "INSERT INTO ReservaLibro(fecha_inicio, fecha_fin, isbn, dni_cliente";
		  PreparedStatement preparedStmt;
			try {
				preparedStmt = conexionBD.prepareStatement(insertSQL);
				preparedStmt.setString(1, LocalDateTime.now().toString());
				preparedStmt.setString(1, LocalDateTime.now().plusDays(diasDevolucion).toString());
				preparedStmt.setLong(1, isbn);
				preparedStmt.setString(1, cliente.getDni());
				 		
				preparedStmt.executeUpdate();
				preparedStmt.close();
			} catch(SQLException e) {
				if (logger != null) {
					logger.log(Level.SEVERE, "Error al realizar la reserva: ", e);
				}
			}	
	}
	
	@Override
	public ArrayList<LibroDTO>getHistorialByCliente(String dniCliente){
		ArrayList<LibroDTO> historialCliente = new ArrayList<LibroDTO>();
		
		String insertSQL = "SELECT L.* FROM ReservaLibro R, Libro L WHERE R.isbn_libro = L.isbn AND R.dni_cliente = ?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, dniCliente);
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while(rs.next()) {
                	LibroDTO libro = null;
                	long isbn = rs.getLong("isbn");
                	String titulo = rs.getString ("titulo");
                	String autor = rs.getString("autor");
                	int numeroDePaginas = rs.getInt("numPaginas");
                	String sinopsis = rs.getString("sinopsis");
                	String genero = rs.getString("genero");
                	int rating = rs.getInt("rating");
                	int fechaPublicacion = rs.getInt("fecha_publicacion");
                	
                	libro = new LibroDTO(isbn, titulo, autor, numeroDePaginas, sinopsis, genero, rating, fechaPublicacion);
                	historialCliente.add(libro);
                }
                System.out.println("Historial cliente sin fallos");
                return historialCliente;
	        }
		
		}catch(SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar el bhistorial del cliente: ", e);
			}
			return historialCliente;
				
		}
	}
	
	
	@Override
	public boolean updateLibro(LibroDTO libroEditado, long isbnAntiguo) {
		try {
            String insertSQL = "UPDATE Libro SET isbn=?,titulo=?,autor=?,numPaginas=?,sinopsis=?, genero=?, rating=?, fecha_publicacion=? WHERE isbn=?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setLong(1, libroEditado.getIsbn());
            preparedStmt.setString(2, libroEditado.getTitulo());
            preparedStmt.setString(3, libroEditado.getAutor());
            preparedStmt.setInt(4, libroEditado.getNumeroDePaginas());
            preparedStmt.setString(5, libroEditado.getSinopsis());
            preparedStmt.setString(6, libroEditado.getGenero());
            preparedStmt.setInt(7, libroEditado.getRating());
            preparedStmt.setInt(8, libroEditado.getFechaPublicacion());
            preparedStmt.setLong(9, isbnAntiguo);
            
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);
            
            preparedStmt.close();
            return (filas > 0) ? true : false;
            
		}catch(SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al actualizar la reserva de la sala privada: ", e);
            return false;
		}
	}
	
	@Override
	public boolean deleteLibroByIsbn(long isbnLibro) {
		try {
            String insertSQL = "DELETE FROM Libro where isbn = ?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setLong(1,isbnLibro);
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
            
		}catch(SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al eliminar la reserva de la sala privada: ", e);
            return false;
			
		}
	}


	@Override
	public boolean libroLeidoByDniCliente(String dniCliente, Long isbn) {
	    String query = "SELECT count(*) AS veces_leido FROM ReservaLibro WHERE dni_cliente = ? AND isbn = ?";

	    try (PreparedStatement preparedStmt = conexionBD.prepareStatement(query)) {
	       
	        preparedStmt.setString(1, dniCliente);
	        preparedStmt.setLong(2, isbn);

	        try (ResultSet rs = preparedStmt.executeQuery()) {
	            if (rs.next()) {
	                // Obtener el número de veces que el cliente ha leído el libro
	                int vecesLeido = rs.getInt("veces_leido");

	                // Si el libro ha sido leído al menos una vez, retornar true, de lo contrario false
	                return vecesLeido > 0;
	            }
	        }
	    } catch (SQLException e) {
	        if (logger != null) {
	            logger.log(Level.SEVERE, "Error al verificar si el cliente ha leído el libro: ", e);
	        }
	    }
	    return false;
	}
	
}
