package dbmejorada;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaSalaPrivadaDTO {
	private int id;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
	private LocalDate fechaReserva;// Fecha en la que se ha hecho la reserva, no la fecha de la reserva
	private String dniCliente;
	private int idSala;
	
	public ReservaSalaPrivadaDTO() {
		super();
		this.id = 0;
		this.fechaEntrada = LocalDateTime.now();
		this.fechaSalida = LocalDateTime.now();
		this.fechaReserva = LocalDate.now();
		this.dniCliente = "";
		this.idSala = 0;
	}
	
	public ReservaSalaPrivadaDTO(int id, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, LocalDate fechaReserva, String dniCliente, int idSala) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.fechaReserva = fechaReserva;
		this.dniCliente = dniCliente;
		this.idSala = idSala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getfechaEntrada() {
		return fechaEntrada;
	}

	public void setfechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDateTime getfechaSalida() {
		return fechaSalida;
	}

	public void setfechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	@Override
	public String toString() {
		return "ReservaSalaDTO [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida
				+ ", fechaReserva=" + fechaReserva + ", dniCliente=" + dniCliente + ", idSala=" + idSala + "]";
	}	
}
