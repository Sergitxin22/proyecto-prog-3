package dbmejorada;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaSalaPublicaDTO {
	private int id;
	private LocalDate fechaEntrada;
	private String dniCliente;
	private int numeroBloque;
	
	public ReservaSalaPublicaDTO(int id, LocalDate fechaEntrada, String dniCliente, int numeroBloque) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.dniCliente = dniCliente;
		this.numeroBloque = numeroBloque;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public String getDniCliente() {
		return dniCliente;
	}
	
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	
	public int getNumeroBloque() {
		return numeroBloque;
	}
	
	public void setNumeroBloque(int numeroBloque) {
		this.numeroBloque = numeroBloque;
	}

	@Override
	public String toString() {
		return "ReservaSalaPublicaDTO [id=" + id + ", fechaEntrada=" + fechaEntrada + ", dniCliente=" + dniCliente
				+ ", numeroBloque=" + numeroBloque + "]";
	}
	
}
