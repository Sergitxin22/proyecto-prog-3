package BiblioTech;

import java.awt.Image;
import java.util.ArrayList;

public class LibroLectura extends Libro {
	private String sinopsis;
	private ArrayList<Review> reviews;
	private Genero genero;
	private int rating;
	
	public LibroLectura(String titulo, String autor, int numeroDePaginas, Image foto, int id, String sinopsis,
			ArrayList<Review> reviews, Genero genero, int rating) {
		super(titulo, autor, numeroDePaginas, foto, id);
		this.sinopsis = sinopsis;
		this.reviews = reviews;
		this.genero = genero;
		this.rating = rating;
	}
	
	public LibroLectura() {
		super();
		this.sinopsis = "";
		this.reviews = new ArrayList<>();
		this.genero = Genero.CIENCIA_FICCION;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
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
