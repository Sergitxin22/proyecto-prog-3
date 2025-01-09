package dbmejorada;

import java.time.LocalDateTime;

import domain.Sala;
import domain.TipoEvento;

public class EventoDTO {

	private int id;
	private String titulo;
	private int idTipoEvento;
	private int idSala;
	private LocalDateTime fecha;
	
	public EventoDTO() {
		super();
		this.id = 0;
		this.titulo = "";
		this.idTipoEvento = 0;
		this.idSala = 0;
		this.fecha = LocalDateTime.now();
	}

	public EventoDTO(int id, String titulo, int idTipoEvento, int idSala, LocalDateTime fecha) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.idTipoEvento = idTipoEvento;
		this.idSala = idSala;
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getIdTipoEvento() {
		return idTipoEvento;
	}
	
	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	
	public int getIdSala() {
		return idSala;
	}
	
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "EventoDTO [id=" + id + ", titulo=" + titulo + ", idTipoEvento=" + idTipoEvento + ", idSala=" + idSala
				+ ", fecha=" + fecha + "]";
	}
}
