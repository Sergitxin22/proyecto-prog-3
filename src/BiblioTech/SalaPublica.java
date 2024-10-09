package BiblioTech;

import java.util.ArrayList;

public class SalaPublica extends Sala {
	private ArrayList<Cliente> supervisores;

	public SalaPublica(int capacidad, int iD, int piso, ArrayList<Cliente> listaClientes,
			ArrayList<Cliente> supervisores) {
		super(capacidad, iD, piso, listaClientes);
		this.supervisores = supervisores;
	}
	
	public SalaPublica() {
		super();
		this.supervisores = new ArrayList<Cliente>();
	}

	public ArrayList<Cliente> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(ArrayList<Cliente> supervisores) {
		this.supervisores = supervisores;
	}

	@Override
	public String toString() {
		return "SalaPublica [supervisores=" + supervisores + ", getCapacidad()=" + getCapacidad() + ", getId()="
				+ getId() + ", getPiso()=" + getPiso() + ", getListaClientes()=" + getListaClientes() + "]";
	}

	
	
	
	
	

}
