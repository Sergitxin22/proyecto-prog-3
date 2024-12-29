package domain;

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
