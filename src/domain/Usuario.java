package domain;

import java.time.LocalDate;
import java.util.Objects;

import db.UsuarioDTO;

public abstract class Usuario {

	private String dni;
	private String nombre;
	private String email;
	private LocalDate fechaCreacion;
	private String contrasena;
	
	public Usuario(String dni, String nombre, String email, LocalDate fechaCreacion, String contrasena) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.fechaCreacion = fechaCreacion;
		this.contrasena = contrasena;
	}
	
	public Usuario() {
		super();
		this.dni = "";
		this.nombre = "";
		this.email = "";
		this.fechaCreacion = LocalDate.now();
		this.contrasena = "";
	}
	
	public Usuario(UsuarioDTO usuarioDTO) {
		super();
		this.dni = usuarioDTO.getDni();
		this.nombre = usuarioDTO.getNombre();
		this.email = usuarioDTO.getEmail();
		this.fechaCreacion = usuarioDTO.getFechaCreacion();
		this.contrasena = "";
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", fechaCreacion=" + fechaCreacion
				+ ", contrasena=" + contrasena + "]";
	}

}
