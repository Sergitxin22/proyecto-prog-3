package dbmejorada;

public class AsistenciaEventoDTO {
	private int id;
	private String dni_asistente;
	private int id_evento;
	
	
	public AsistenciaEventoDTO(int id, String dni_asistente, int id_evento) {
		super();
		this.id = id;
		this.dni_asistente = dni_asistente;
		this.id_evento = id_evento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni_asistente() {
		return dni_asistente;
	}
	public void setDni_asistente(String dni_asistente) {
		this.dni_asistente = dni_asistente;
	}
	public int getId_evento() {
		return id_evento;
	}
	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}
	
	
	@Override
	public String toString() {
		return "AsistenciaEventoDTO [id=" + id + ", dni_asistente=" + dni_asistente + ", id_evento=" + id_evento+ "]";
	}
	
	
	

}
