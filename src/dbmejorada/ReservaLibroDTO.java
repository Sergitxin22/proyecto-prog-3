package dbmejorada;

import java.time.LocalDateTime;

public class ReservaLibroDTO {
	private int id;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private Long isbn;
	private String dniCliente;

	public ReservaLibroDTO(int id, LocalDateTime fechaInicio, LocalDateTime fechaFin, Long isbn, String dniCliente) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.isbn = isbn;
		this.dniCliente = dniCliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	@Override
	public String toString() {
		return "ReservaLibroDTO [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", isbn="
				+ isbn + ", dniCliente=" + dniCliente + "]";
	}
}
