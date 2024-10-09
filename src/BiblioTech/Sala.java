package BiblioTech;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Sala implements Reservable {
	private int capacidad;
	private int id;
	private int piso;
	private ArrayList<Cliente> listaClientes;
	
	
	public Sala(int capacidad, int id, int piso, ArrayList<Cliente> listaClientes) {
		super();
		this.capacidad = capacidad;
		this.id = id;
		this.piso = piso;
		this.listaClientes = listaClientes;
	}
	
	public Sala() {
		super();
		this.capacidad = 0;
		this.id = 0;
		this.piso = 0;
		this.listaClientes = new ArrayList<Cliente>();
	}

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

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Sala [capacidad=" + capacidad + ", ID=" + id + ", piso=" + piso + ", listaClientes=" + listaClientes
				+ "]";
	}
	
	
	
	
	

}
