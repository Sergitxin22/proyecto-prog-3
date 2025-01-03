package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import dbmejorada.ReservaSalaDTO;
import dbmejorada.SalaDTO;
import dbmejorada.UsuarioDTO;
import main.Main;

public class Reserva {

	private Sala sala;
	private LocalDate fechaReserva; // Fecha en la que se ha hecho la reserva, no la fecha de la reserva
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSalida;
	private Cliente cliente;
	
	public Reserva(Sala sala, LocalDate fechaReserva, LocalDateTime horaEntrada, LocalDateTime horaSalida,
			Cliente cliente) {
		super();
		this.sala = sala;
		this.fechaReserva = fechaReserva;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.cliente = cliente;
	}

	public Reserva() {
		super();
		this.sala = null;
		this.fechaReserva = LocalDate.now();
		this.horaEntrada = null;
		this.horaSalida = null;
		this.cliente = new Cliente();
	}

	
	public Reserva(ReservaSalaDTO reserva) {
		super();
		SalaDTO salaDTO = Main.getSalaDAO().getSala(reserva.getIdSala());
		this.sala = new SalaPrivada(salaDTO);
		this.fechaReserva = reserva.getFechaReserva();
		this.horaEntrada = reserva.getHoraEntrada();
		this.horaSalida = reserva.getHoraSalida();
		UsuarioDTO usuarioDTO = Main.getUsuarioDAO().getUsuario(reserva.getDniCliente());
		if (!usuarioDTO.isAdmin()) {
			this.cliente = new Cliente(usuarioDTO);
		}
	}

	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
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
	
	public Cliente getClienteReserva() {
		return cliente;
	}
	
	public void setClienteReserva(Cliente cliente) {
		this.cliente = cliente;
	}
		
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaReserva, horaEntrada, horaSalida);
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
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaReserva, other.fechaReserva)
				&& Objects.equals(horaEntrada, other.horaEntrada) && Objects.equals(horaSalida, other.horaSalida);
	}
	
	@Override
	public String toString() {
		return "Reserva [fechaReserva=" + fechaReserva + ", horaEntrada=" + horaEntrada
				+ ", horaSalida=" + horaSalida + ", cliente=" + cliente + "]";
	}
}
