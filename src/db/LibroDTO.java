package db;

import java.util.Objects;

public class LibroDTO {

	private long isbn;
	private String titulo;
	private String autor;
	private int numeroDePaginas;
	private String sinopsis;
	private String genero;
	private int rating;
	private int fechaPublicacion;
	
	public LibroDTO() {
		super();
		this.isbn = 0;
		this.titulo = "";
		this.autor = "";
		this.numeroDePaginas = 0;
		this.sinopsis = "";
		this.genero = "";
		this.rating = 0;
		this.fechaPublicacion = 0;
	}
	
	public LibroDTO(long isbn, String titulo, String autor, int numeroDePaginas, String sinopsis, String genero,
			int rating, int fechaPublicacion) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.rating = rating;
		this.fechaPublicacion = fechaPublicacion;
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

	@Override
	public String toString() {
		return "LibroDTO [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", numeroDePaginas="
				+ numeroDePaginas + ", sinopsis=" + sinopsis + ", genero=" + genero + ", rating=" + rating
				+ ", fechaPublicacion=" + fechaPublicacion + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibroDTO other = (LibroDTO) obj;
		return Objects.equals(autor, other.autor) && fechaPublicacion == other.fechaPublicacion
				&& Objects.equals(genero, other.genero) && isbn == other.isbn
				&& numeroDePaginas == other.numeroDePaginas && rating == other.rating
				&& Objects.equals(sinopsis, other.sinopsis) && Objects.equals(titulo, other.titulo);
	}
}
