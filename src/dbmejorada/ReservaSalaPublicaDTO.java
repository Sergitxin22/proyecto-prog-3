package dbmejorada;

import java.time.LocalDateTime;

public class ReservaSalaPublicaDTO {
	private int id;
	private LocalDateTime fechaEntrada;
	private String dniCliente;
	private int numeroBloque;
	
	public ReservaSalaPublicaDTO() {
		super();
		this.id = 0;
		this.fechaEntrada = LocalDateTime.now();
		this.dniCliente = "";
		this.numeroBloque = 0;
	}
	
	public ReservaSalaPublicaDTO(int id, LocalDateTime fechaEntrada, String dniCliente, int numeroBloque) {
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
	
	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}
	
	public void setFechaEntrada(LocalDateTime fechaEntrada) {
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
