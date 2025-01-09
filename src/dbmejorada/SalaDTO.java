package dbmejorada;

import java.util.ArrayList;

import domain.Evento;
import domain.Recurso;

public class SalaDTO {

	private int id;
	private int piso;
	private int capacidad;
	private int idTipo;

	public SalaDTO() {
		this.id = 0;
		this.piso = 0;
		this.capacidad = 0;
		this.idTipo = 0;
	}

	public SalaDTO(int id, int piso, int capacidad, int idTipo) {
		this.id = id;
		this.piso = piso;
		this.capacidad = capacidad;
		this.idTipo = idTipo;
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

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public String toString() {
		return "SalaDTO [id=" + id + ", piso=" + piso + ", capacidad=" + capacidad + ", idTipo=" + idTipo + "]";
	}
}
