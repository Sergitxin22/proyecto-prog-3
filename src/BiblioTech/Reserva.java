package BiblioTech;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Reserva {

	private SalaPrivada sala;
	private LocalDate fechaReserva; // Fecha en la que se ha hecho la reserva, no la fecha de la reserva
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSalida;
	private ArrayList<Cliente> clientesReserva;
	
	public SalaPrivada getSala() {
		return sala;
	}
	public void setSala(SalaPrivada sala) {
		this.sala = sala;
	}
	public LocalDate getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
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
	public ArrayList<Cliente> getClientesReserva() {
		return clientesReserva;
	}
	public void setClientesReserva(ArrayList<Cliente> clientesReserva) {
		this.clientesReserva = clientesReserva;
	}
	
	
	public Reserva(SalaPrivada sala, LocalDate fechaReserva, LocalDateTime horaEntrada, LocalDateTime horaSalida,
			ArrayList<Cliente> clientesReserva) {
		super();
		this.sala = sala;
		this.fechaReserva = fechaReserva;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.clientesReserva = clientesReserva;
	}
	
	public Reserva() {
		super();
		this.sala = new SalaPrivada();
		this.fechaReserva = LocalDate.now();
		this.horaEntrada = null;
		this.horaSalida = null;
		this.clientesReserva = new ArrayList<>();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(clientesReserva, other.clientesReserva)
				&& Objects.equals(fechaReserva, other.fechaReserva) && Objects.equals(horaEntrada, other.horaEntrada)
				&& Objects.equals(horaSalida, other.horaSalida) && Objects.equals(sala, other.sala);
	}
	
	@Override
	public String toString() {
		return "Reserva [sala=" + sala + ", fechaReserva=" + fechaReserva + ", horaEntrada=" + horaEntrada
				+ ", horaSalida=" + horaSalida + ", clientesReserva=" + clientesReserva + "]";
	}
	
	

}
