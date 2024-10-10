package BiblioTech;

import java.util.ArrayList;

public class SalaPublica extends Sala {
	private ArrayList<Admin> supervisores;
	
	public SalaPublica(int capacidad, int iD, int piso, ArrayList<Cliente> listaClientes,
			ArrayList<Admin> supervisores) {
		super(capacidad, iD, piso, listaClientes);
		this.supervisores = supervisores;
	}
	
	public SalaPublica() {
		super();
		this.supervisores = new ArrayList<Admin>();
	}

	public ArrayList<Admin> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(ArrayList<Admin> supervisores) {
		this.supervisores = supervisores;
	}

	@Override
	public String toString() {
		return "SalaPublica [supervisores=" + supervisores + ", getCapacidad()=" + getCapacidad() + ", getId()="
				+ getId() + ", getPiso()=" + getPiso() + ", getListaClientes()=" + getListaClientes() + "]";
	}

	
	
	
	
	

}
