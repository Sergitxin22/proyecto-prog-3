package dbmejorada;

public class UsuarioDTO {
	private String dni;
	private String nombre;
	private int amonestaciones;
	private boolean admin;
	
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
	
	public int getAmonestaciones() {
		return amonestaciones;
	}
	
	public void setAmonestaciones(int amonestaciones) {
		this.amonestaciones = amonestaciones;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [dni=" + dni + ", nombre=" + nombre + ", amonestaciones=" + amonestaciones + ", admin="
				+ admin + "]";
	}
}
