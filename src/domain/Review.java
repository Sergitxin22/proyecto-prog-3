package domain;

import java.util.Objects;

import db.LibroDTO;
import db.UsuarioDTO;

public class Review {

	private LibroDTO libroDTO;
	private UsuarioDTO cliente;
	private String comentario;
	private int rating;
	
	public LibroDTO getLibroDTO() {
		return libroDTO;
	}
	public void setLibroDTO(LibroDTO libroDTO) {
		this.libroDTO = libroDTO;
	}
	public UsuarioDTO getCliente() {
		return cliente;
	}
	public void setCliente(UsuarioDTO cliente) {
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
	
	public Review(LibroDTO libroDTO, UsuarioDTO cliente, String comentario, int rating) {
		super();
		this.libroDTO = libroDTO;
		this.cliente = cliente;
		this.comentario = comentario;
		this.rating = rating;
	}
	
	public Review() {
		super();
		this.libroDTO = new LibroDTO();
		this.cliente = new UsuarioDTO();
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
				&& Objects.equals(libroDTO, other.libroDTO) && rating == other.rating;
	}
	
	@Override
	public String toString() {
		return String.format("@%s (Rating: %d/10): %s", cliente.getNombre(), getRating(), getComentario());
	}
}
