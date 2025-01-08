package domain;

import dbmejorada.SalaDTO;

public class SalaEventos extends Sala {

	public SalaEventos(int capacidad, int id, int piso) {
		super(capacidad, id, piso);
	}
	
	public SalaEventos(Evento evento) {
		super();
	}
	
	public SalaEventos(SalaDTO salaDTO) {
		super(salaDTO.getCapacidad(),salaDTO.getId(),salaDTO.getPiso());
	}


	@Override
	public String toString() {
		return "SalaEventos [getCapacidad()=" + getCapacidad() + ", getId()=" + getId()
				+ ", getPiso()=" + getPiso() + "]";
	}
}
