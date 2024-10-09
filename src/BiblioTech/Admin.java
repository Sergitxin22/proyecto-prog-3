package BiblioTech;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Admin extends Usuario {

	private ArrayList<String> horasDisponible;
	private ArrayList<String> logAcciones;
	
	public Admin(String dni, String nombre, String email, LocalDateTime fechaCreacion, String contrasena,
			ArrayList<String> horasDisponible, ArrayList<String> logAcciones) {
		super(dni, nombre, email, fechaCreacion, contrasena);
		this.horasDisponible = horasDisponible;
		this.logAcciones = logAcciones;
	}
	
	public Admin() {
		super();
		this.horasDisponible = new ArrayList<String>();
		this.logAcciones = new ArrayList<String>();
	}

	public ArrayList<String> getHorasDisponible() {
		return horasDisponible;
	}

	public void setHorasDisponible(ArrayList<String> horasDisponible) {
		this.horasDisponible = horasDisponible;
	}

	public ArrayList<String> getLogAcciones() {
		return logAcciones;
	}

	public void setLogAcciones(ArrayList<String> logAcciones) {
		this.logAcciones = logAcciones;
	}

	@Override
	public String toString() {
		return "Admin [horasDisponible=" + horasDisponible + ", logAcciones=" + logAcciones + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getEmail()=" + getEmail() + ", getFechaCreacion()="
				+ getFechaCreacion() + ", getContrasena()=" + getContrasena() + "]";
	}

}
