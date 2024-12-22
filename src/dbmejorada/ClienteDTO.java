package dbmejorada;

public class ClienteDTO {
	private String dni;
	private String nombre;
	private int amonestaciones;
	
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
}
