package domain;

import dbmejorada.EventoDTO;
import dbmejorada.SalaDTO;
import main.Main;

public class SalaEventos extends Sala {
	private Evento evento;

	public SalaEventos(int capacidad, int id, int piso, Evento evento) {
		super(capacidad, id, piso);
		this.evento = evento;
	}
	
	public SalaEventos(Evento evento) {
		super();
		this.evento = evento;
	}
	
	public SalaEventos(SalaDTO salaDTO) {
		super(salaDTO.getCapacidad(),salaDTO.getId(),salaDTO.getPiso());
		EventoDTO evento = Main.getEventoDAO().getEvento(salaDTO.getId());
		this.evento = new Evento(evento);
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		return "SalaEventos [evento=" + evento + ", getCapacidad()=" + getCapacidad() + ", getId()=" + getId()
				+ ", getPiso()=" + getPiso() + "]";
	}
}
