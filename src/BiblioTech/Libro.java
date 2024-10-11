package BiblioTech;

import java.awt.Image;

public abstract class Libro implements Reservable {
	private String titulo;
	private String autor;
	private int numeroDePaginas;
	private Image foto;
	private int id;
	
	public Libro(String titulo, String autor, int numeroDePaginas, Image foto, int id) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.foto = foto;
		this.id = id;
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

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", numeroDePaginas=" + numeroDePaginas + ", foto="
				+ foto + ", id=" + id + "]";
	}
	
	
	
	
	
	
	
}
