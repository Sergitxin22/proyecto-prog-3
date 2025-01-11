package dbmejorada;

import java.util.ArrayList;

import domain.Review;

public interface ReviewDAOInterface {	
	boolean addReview(Review review);
	ArrayList<Review> getReviewsByUsuarioDni(String dniCliente);
	ArrayList<Review> getReviewsLibroByIsbn(Long isbnLibro);	
}
