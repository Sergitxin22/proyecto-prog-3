package db;

public class ReviewDTO {
	private int id;
	private String comentario;
	private int rating;
	private long isbnLibro;
	private String dniCliente;
	
	public ReviewDTO() {
		super();
		this.id = 0;
		this.comentario = "";
		this.rating = 0;
		this.isbnLibro = 0l;
		this.dniCliente = "";
	}
	
	public ReviewDTO(int id, String comentario, int rating, long isbnLibro, String dniCliente) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.rating = rating;
		this.isbnLibro = isbnLibro;
		this.dniCliente = dniCliente;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public long getIsbnLibro() {
		return isbnLibro;
	}
	
	public void setIsbnLibro(long isbnLibro) {
		this.isbnLibro = isbnLibro;
	}
	
	public String getDniCliente() {
		return dniCliente;
	}
	
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	
	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", comentario=" + comentario + ", rating=" + rating + ", isbnLibro=" + isbnLibro
				+ ", dniCliente=" + dniCliente + "]";
	}
}
