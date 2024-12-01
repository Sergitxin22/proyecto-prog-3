package BiblioTech;

import java.util.ArrayList;
import java.util.HashMap;

public class SalaPublica extends Sala {
	private ArrayList<Admin> supervisores;
	private HashMap<Integer, Cliente> clientesPorBloque;
	
	public SalaPublica(int capacidad, int iD, int piso, ArrayList<Cliente> listaClientes) {
		super(capacidad, iD, piso, listaClientes);

		this.supervisores = new ArrayList<>();
		HashMap<Integer, Cliente> mapa = new HashMap<>();
		for (int i = 1; i < 251; i++) {
			mapa.put(i, null);
		}

		this.clientesPorBloque = mapa;
	}
	
	public SalaPublica() {
		super();
		HashMap<Integer, Cliente> mapa = new HashMap<>();
		for (int i = 1; i < 251; i++) {
			mapa.put(i, null);
		}

		this.supervisores = new ArrayList<>();
		this.clientesPorBloque = mapa;
	}

	public ArrayList<Admin> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(ArrayList<Admin> supervisores) {
		this.supervisores = supervisores;
	}

	public HashMap<Integer, Cliente> getClientesPorBloque() {
		return clientesPorBloque;
	}

	public void setClientesPorBloque(HashMap<Integer, Cliente> clientesPorBloque) {
		this.clientesPorBloque = clientesPorBloque;
	}

	@Override
	public String toString() {
		return "SalaPublica [supervisores=" + supervisores + ", getCapacidad()=" + getCapacidad() + ", getId()="
				+ getId() + ", getPiso()=" + getPiso() + ", getListaClientes()=" + getListaClientes() + ", getClientesPorBloque()=" + getClientesPorBloque() + "]";
	}

	
	
	
	
	

}
