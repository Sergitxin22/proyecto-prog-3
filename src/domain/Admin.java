package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dbmejorada.UsuarioDTO;
import main.Main;

public class Admin extends Usuario {

	private ArrayList<String> logAcciones;
	
	public Admin(String dni, String nombre, String email, LocalDateTime fechaCreacion, String contrasena, ArrayList<LogAccion> logAcciones) {
		super(dni, nombre, email, fechaCreacion, contrasena);
		this.logAcciones = logAcciones;
	}
	
	public Admin() {
		super();
		this.logAcciones = new ArrayList<>();
	}
	
	public Admin(UsuarioDTO usuarioDTO) {
		super(usuarioDTO);
//		this.logAcciones = Main.getUsuarioDAO().getLogAccionesByAdminDni(usuarioDTO.getDni()); TODO: crearlo en usuarioDAO
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
