package BiblioTech;

import java.awt.Image;

public class LibroLectura extends Libro {
	private String sinopsis;
	private String review;
	private Genero genero;
	private int rating;
	
	public LibroLectura(String titulo, String autor, int numeroDePaginas, Image foto, int id, String sinopsis,
			String review, Genero genero, int rating) {
		super(titulo, autor, numeroDePaginas, foto, id);
		this.sinopsis = sinopsis;
		this.review = review;
		this.genero = genero;
		this.rating = rating;
	}
	
	public LibroLectura() {
		super();
		this.sinopsis = "";
		this.review = "";
		this.genero = Genero.CIENCIA_FICCION;
		this.rating = 0;
	}
	

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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
		return "LibroLectura [sinopsis=" + sinopsis + ", review=" + review + ", genero=" + genero + ", rating=" + rating
				+ ", getTitulo()=" + getTitulo() + ", getAutor()=" + getAutor() + ", getNumeroDePaginas()="
				+ getNumeroDePaginas() + ", getFoto()=" + getFoto() + ", getId()=" + getId() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}
