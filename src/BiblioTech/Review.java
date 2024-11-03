package BiblioTech;

import java.util.Objects;

public class Review {

	private LibroLectura libro;
	private Cliente cliente;
	private String comentario;
	private int rating;
	
	public LibroLectura getLibro() {
		return libro;
	}
	public void setLibro(LibroLectura libro) {
		this.libro = libro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Review(LibroLectura libro, Cliente cliente, String comentario, int rating) {
		super();
		this.libro = libro;
		this.cliente = cliente;
		this.comentario = comentario;
		this.rating = rating;
	}
	
	public Review() {
		super();
		this.libro = new LibroLectura();
		this.cliente = new Cliente();
		this.comentario = "";
		this.rating = 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(comentario, other.comentario)
				&& Objects.equals(libro, other.libro) && rating == other.rating;
	}
	
	@Override
	public String toString() {
		return "Review [libro=" + libro + ", cliente=" + cliente + ", comentario=" + comentario + ", rating=" + rating
				+ "]";
	}
}
