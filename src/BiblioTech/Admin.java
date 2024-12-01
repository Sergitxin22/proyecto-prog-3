package BiblioTech;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Admin extends Usuario {

	private ArrayList<String> logAcciones;
	
	public Admin(String dni, String nombre, String email, LocalDateTime fechaCreacion, String contrasena, ArrayList<String> logAcciones) {
		super(dni, nombre, email, fechaCreacion, contrasena);
		this.logAcciones = logAcciones;
	}
	
	public Admin() {
		super();
		this.logAcciones = new ArrayList<>();
	}

	public ArrayList<String> getLogAcciones() {
		return logAcciones;
	}

	public void setLogAcciones(ArrayList<String> logAcciones) {
		this.logAcciones = logAcciones;
	}

	@Override
	public String toString() {
		return "Admin [logAcciones=" + logAcciones + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getEmail()=" + getEmail() + ", getFechaCreacion()="
				+ getFechaCreacion() + ", getContrasena()=" + getContrasena() + "]";
	}

}
