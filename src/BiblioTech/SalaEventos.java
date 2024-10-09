package BiblioTech;

import java.util.ArrayList;

public class SalaEventos extends Sala {
	private Evento evento;

	public SalaEventos(int capacidad, int id, int piso, ArrayList<Cliente> listaClientes, Evento evento) {
		super(capacidad, id, piso, listaClientes);
		this.evento = evento;
	}
	
	public SalaEventos() {
		super();
		this.evento = new Evento();
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
				+ ", getPiso()=" + getPiso() + ", getListaClientes()=" + getListaClientes() + "]";
	}

	
	
	
	

}
