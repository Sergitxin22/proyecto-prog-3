package dbmejorada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Libro;
import domain.Review;
import domain.Usuario;
import main.Main;

public class ReviewDAO implements ReviewDAOInterface { 
	// Nota: Las Reviews no necesitan DTOs ya que todos los atributos de Review son visibles en la aplicación

	private Connection conexionBD;
	private Logger logger;
	
	public ReviewDAO() {
		this.conexionBD = Main.getConexionBD();
		this.logger = Main.getLogger();
	}

	@Override
	public boolean addReview(Review review) {
		try {
            String insertSQL = "INSERT INTO Review(comentario, rating, isbn_libro, dni_cliente) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1, review.getComentario());
            preparedStmt.setInt(2, review.getRating());
            preparedStmt.setLong(3, review.getLibroDTO().getIsbn());
            preparedStmt.setString(4, review.getCliente().getDni());

            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            preparedStmt.close();
            return true;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el usuario: ", e);
            return false;
        }
	}

	@Override
	public ArrayList<Review> getReviewsUsuario(Usuario usuario) {
		ArrayList<Review> result = null;
		
		String selectSQL = "SELECT * FROM Review WHERE dni_cliente = ?";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setString(1, usuario.getDni());

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
	        	result = new ArrayList<>();
                while (rs.next()) {
                   Review review = new Review();
                   
                   review.setLibroDTO(Main.getLibroDAO().getLibro(rs.getInt("isbn_libro")));
                   review.setCliente(Main.getUsuarioDAO().getUsuario(rs.getString("dni_cliente")));
                   review.setRating(rs.getInt("rating"));
                   review.setComentario(rs.getString("comentario"));
                   
                   result.add(review);
                }
                
                System.out.println("Sala recuperada correctamente");
    			preparedStmt.close();
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la sala: ", e);
				return result;
			}
		} 
		
		return result;	
	}

	@Override
	public ArrayList<Review> getReviewsLibro(Libro libro) {
		ArrayList<Review> result = null;
		
		String selectSQL = "SELECT * FROM Review WHERE isbn_libro = ?";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setLong(1, libro.getIsbn());

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
	        	result = new ArrayList<>();
                while (rs.next()) {
                   Review review = new Review();
                   review.setLibroDTO(Main.getLibroDAO().getLibro(rs.getInt("isbn_libro")));
                   review.setCliente(Main.getUsuarioDAO().getUsuario(rs.getString("dni_cliente")));
                   review.setRating(rs.getInt("rating"));
                   review.setComentario(rs.getString("comentario"));
                   
                   result.add(review);
                }
                
                System.out.println("Sala recuperada correctamente");
    			preparedStmt.close();
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la sala: ", e);
				return result;
			}
		} 
		
		return result;	
	}
}
