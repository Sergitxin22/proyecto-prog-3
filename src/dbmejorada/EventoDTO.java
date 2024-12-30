package dbmejorada;

import java.time.LocalDateTime;

import domain.Sala;
import domain.TipoEvento;

public class EventoDTO {

	private int id;
	private String titulo;
	private TipoEvento tipoEvento;
	private SalaDTO sala;
	private LocalDateTime fechaHora;
	private int hora;

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
	
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}
	
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	public SalaDTO getSala() {
		return sala;
	}
	
	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public int getHora() {
		return hora;
	}
	
	public void setHora(int hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "EventoDTO [id=" + id + ", titulo=" + titulo + ", tipoEvento=" + tipoEvento + ", sala=" + sala
				+ ", fecha=" + fechaHora + ", hora=" + hora + "]";
	}
	
	
}
