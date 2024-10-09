package BiblioTech;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Usuario {

	private String dni;
	private String nombre;
	private String email;
	private LocalDateTime fechaCreacion;
	private String contrasena;
	
	public Usuario(String dni, String nombre, String email, LocalDateTime fechaCreacion, String contrasena) {
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
		this.fechaCreacion = LocalDateTime.now();
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

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
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
