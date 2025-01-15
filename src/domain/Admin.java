package domain;

import java.time.LocalDate;
import java.util.ArrayList;

import db.UsuarioDTO;
import main.Main;

public class Admin extends Usuario {

	private ArrayList<LogAccion> logAcciones;
	
	public Admin(String dni, String nombre, String email, LocalDate fechaCreacion, String contrasena, ArrayList<LogAccion> logAcciones) {
		super(dni, nombre, email, fechaCreacion, contrasena);
		this.logAcciones = logAcciones;
	}
	
	public Admin() {
		super();
		this.logAcciones = new ArrayList<>();
	}
	
	public Admin(UsuarioDTO usuarioDTO) {
		super(usuarioDTO);
		this.logAcciones = Main.getUsuarioDAO().getLogAccionesByAdminDni(usuarioDTO.getDni());
	}

	public ArrayList<LogAccion> getLogAcciones() {
		return logAcciones;
	}

	public void setLogAcciones(ArrayList<LogAccion> logAcciones) {
		this.logAcciones = logAcciones;
	}

	@Override
	public String toString() {
		return "Admin [logAcciones=" + logAcciones + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getEmail()=" + getEmail() + ", getFechaCreacion()="
				+ getFechaCreacion() + ", getContrasena()=" + getContrasena() + "]";
	}
}
