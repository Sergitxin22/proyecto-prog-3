package dbmejorada;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaSalaDTO {
	private int id;
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSalida;
	private LocalDate fechaReserva;// Fecha en la que se ha hecho la reserva, no la fecha de la reserva
	private String dniCliente;
	private int idSala;
	
	public ReservaSalaDTO(int id, LocalDateTime horaEntrada, LocalDateTime horaSalida, LocalDate fechaReserva,
			String dniCliente, int idSala) {
		super();
		this.id = id;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
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

	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
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
		return "ReservaSalaDTO [id=" + id + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida
				+ ", fechaReserva=" + fechaReserva + ", dniCliente=" + dniCliente + ", idSala=" + idSala + "]";
	} 
	
	
	
}
