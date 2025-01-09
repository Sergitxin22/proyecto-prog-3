package domain;

import java.util.HashMap;

import dbmejorada.SalaDTO;

public class SalaPublica extends Sala {
	private HashMap<Integer, Cliente> clientesPorBloque;
	
	public SalaPublica(int capacidad, int iD, int piso) {
		super(capacidad, iD, piso);

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

		this.clientesPorBloque = mapa;
	}
	
	public SalaPublica(SalaDTO salaDTO) {
		super(salaDTO.getCapacidad(), salaDTO.getId(), salaDTO.getPiso());
		HashMap<Integer, Cliente> mapa = new HashMap<>();
		for (int i = 1; i < 251; i++) {
			mapa.put(i, null);
		}

		this.clientesPorBloque = mapa;
	}
	
	public HashMap<Integer, Cliente> getClientesPorBloque() {
		return clientesPorBloque;
	}

	public void setClientesPorBloque(HashMap<Integer, Cliente> clientesPorBloque) {
		this.clientesPorBloque = clientesPorBloque;
	}

	@Override
	public String toString() {
		return "SalaPublica [getCapacidad()=" + getCapacidad() + ", getId()="
				+ getId() + ", getPiso()=" + getPiso() + ", getClientesPorBloque()=" + getClientesPorBloque() + "]";
	}

	
	
	
	
	

}
