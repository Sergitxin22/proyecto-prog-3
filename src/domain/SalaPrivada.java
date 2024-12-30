package domain;

import java.util.ArrayList;

public class SalaPrivada extends Sala {
	private ArrayList<Recurso> recursos;
	private ArrayList<Reserva> reservas;
	
	public SalaPrivada(int capacidad, int id, int piso, ArrayList<Recurso> recursos, ArrayList<Reserva> reservas) {
		super(capacidad, id, piso);
		this.recursos = recursos;
		this.reservas = reservas;
	}
	
	public SalaPrivada() {
		super();
		this.recursos = new ArrayList<>();
		this.reservas = new ArrayList<>();
	}

	public ArrayList<Recurso> getRecursos() {
		return recursos;
	}
	
	public void setRecursos(ArrayList<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
