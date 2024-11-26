package BiblioTech;

import javax.swing.ImageIcon;

public abstract class Libro implements Reservable {
	private String titulo;
	private String autor;
	private int numeroDePaginas;
	private ImageIcon foto;
	private long id;
	
	public Libro(String titulo, String autor, int numeroDePaginas, ImageIcon foto, long id) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
		this.foto = foto;
		this.id = id;
	}
	
	public Libro() {
		super();
		this.titulo = "";
		this.autor = "";
		this.numeroDePaginas = 0;
		this.foto = null;
		this.id = 0l;
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

	public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", numeroDePaginas=" + numeroDePaginas + ", foto="
				+ foto + ", id=" + id + "]";
	}
	
	
	
	
	
	
	
}
