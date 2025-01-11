package dbmejorada;

import java.util.ArrayList;

import domain.Libro;
import domain.Review;
import domain.Usuario;


public interface ReviewDAOInterface {	
	boolean addReview(Review review);
	ArrayList<Review> getReviewsUsuario(Usuario usuario);
	ArrayList<Review> getReviewsLibroByIsbn(Long isbnLibro);	
}
