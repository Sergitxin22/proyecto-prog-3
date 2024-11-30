package BiblioTech;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class LibroLectura extends Libro {
	private String sinopsis;
	private ArrayList<Review> reviews;
	private String genero;
	private int rating;
	
	public LibroLectura(String titulo, String autor, int numeroDePaginas, ImageIcon image, int id, String sinopsis,
			ArrayList<Review> reviews, String genero, int rating) {
		super(titulo, autor, numeroDePaginas, image, id);
		this.sinopsis = sinopsis;
		this.reviews = reviews;
		this.genero = genero;
		this.rating = rating;
	}
	
	public LibroLectura() {
		super();
		this.sinopsis = "";
		this.reviews = new ArrayList<>();
		this.genero = "";
		this.rating = 0;
	}
	

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public ArrayList<Review> getReview() {
		return reviews;
	}

	public void setReview(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "LibroLectura [sinopsis=" + sinopsis + ", review=" + reviews + ", genero=" + genero + ", rating=" + rating
				+ ", getTitulo()=" + getTitulo() + ", getAutor()=" + getAutor() + ", getNumeroDePaginas()="
				+ getNumeroDePaginas() + ", getFoto()=" + getFoto() + ", getId()=" + getId() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}
