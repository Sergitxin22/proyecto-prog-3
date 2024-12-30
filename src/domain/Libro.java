package domain;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Libro implements Reservable {
	// campos de la bbdd
	private long isbn;
	private String titulo;
	private String autor;
	private int numeroDePaginas;
	private String sinopsis;
	private String genero;
	private int rating;
	private int fechaPublicacion;
	
	// campos no relacionados con la bbdd
	private ImageIcon foto;
	private ArrayList<Review> reviews;
	
	public Libro(long isbn, String titulo, String autor, int numeroDePaginas, String sinopsis, String genero,
			int rating, int fechaPublicacion, ImageIcon foto, ArrayList<Review> reviews) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.rating = rating;
		this.fechaPublicacion = fechaPublicacion;
		this.foto = foto;
		this.reviews = reviews;
	}

	public Libro() {
		super();
		this.isbn = 0000000000000l;
		this.titulo = "";
		this.autor = "";
		this.numeroDePaginas = 0;
		this.sinopsis = "";
		this.genero = "";
		this.rating = 0;
		this.fechaPublicacion = 0;
		this.foto = null;
		this.reviews = new ArrayList<Review>();
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
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

	public int getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(int fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}
	
	public void agregarReview(Review review) {
        this.reviews.add(review);
    }

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}


	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", numeroDePaginas="
				+ numeroDePaginas + ", sinopsis=" + sinopsis + ", genero=" + genero + ", rating=" + rating
				+ ", fechaPublicacion=" + fechaPublicacion + ", foto=" + foto + ", reviews=" + reviews + "]";
	}
}
