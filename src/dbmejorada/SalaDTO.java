package dbmejorada;

import java.util.ArrayList;

import domain.Evento;
import domain.Recurso;

public class SalaDTO {

	private int capacidad;
	private int id;
	private int piso;
	private ArrayList<Recurso> recursos;
	private int idEvento;
	private String tipo;
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPiso() {
		return piso;
	}
	
	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	public ArrayList<Recurso> getRecursos() {
		return recursos;
	}
	
	public void setRecursos(ArrayList<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	public int getIdEvento() {
		return idEvento;
	}
	
	public void setEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
