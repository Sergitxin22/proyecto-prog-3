package domain;

import java.util.ArrayList;

import dbmejorada.SalaDTO;
import main.Main;

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

	public SalaPrivada(SalaDTO salaDTO) {
		super(salaDTO.getCapacidad(), salaDTO.getId(), salaDTO.getPiso());
		ArrayList<Integer> recursosIds = Main.getSalaDAO().getIdsRecursosDisponiblesByIdSala(salaDTO.getId());
		this.recursos = getArrayListRecursos(recursosIds);
//		this.reservas = Main.getReservaSalaDAO().getReservasSalaByIdSala(salaDTO.getId()); TODO: hacer el metodo
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
	
	private ArrayList<Recurso> getArrayListRecursos(ArrayList<Integer> recursosIds) {
		ArrayList<Recurso> recursos = new ArrayList<Recurso>();
		for (Integer recursoId : recursosIds) {
			Recurso recurso = Main.getSalaDAO().getRecurso(recursoId);
			recursos.add(recurso);
		}
		return recursos;
	}
	
}
