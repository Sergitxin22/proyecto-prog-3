package BiblioTech;

import java.util.ArrayList;

public class SalaPrivada extends Sala {
	private ArrayList<Recurso> recursos;

	public SalaPrivada(int capacidad, int id, int piso, ArrayList<Cliente> listaClientes, ArrayList<Recurso> recursos) {
		super(capacidad, id, piso, listaClientes);
		this.recursos = recursos;
	}
	
	public SalaPrivada() {
		super();
		this.recursos = new ArrayList<Recurso>();
	}

	public ArrayList<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(ArrayList<Recurso> recursos) {
		this.recursos = recursos;
	}

	@Override
	public String toString() {
		return "SalaPrivada [recursos=" + recursos + ", getCapacidad()=" + getCapacidad() + ", getId()=" + getId()
				+ ", getPiso()=" + getPiso() + ", getListaClientes()=" + getListaClientes() + "]";
	}

	
	

}
