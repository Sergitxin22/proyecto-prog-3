package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import dbmejorada.SalaDTO;

public class Evento {

	private int id;
	private String titulo;
	private TipoEvento tipoEvento;
	private ArrayList<Cliente> asistentes;
	private SalaDTO sala;
	private LocalDateTime fechaHora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getTitulo() {
		return titulo;	
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public TipoEvento getTipoEvento() {
		return tipoEvento;	
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	public ArrayList<Cliente> getAsistentes() {
		return asistentes;
	}
	
	public void setAsistentes(ArrayList<Cliente> asistentes) {
		this.asistentes = asistentes;
	}
	
	public SalaDTO getSala() {
		return sala;
	}
	
	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
	
	public Evento(int id, String titulo, TipoEvento tipoEvento, ArrayList<Cliente> asistentes, SalaDTO sala, LocalDateTime fechaHora) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.tipoEvento = tipoEvento;
		this.asistentes = asistentes;
		this.sala = sala;
		this.fechaHora = fechaHora;
	}
	
	public Evento() {
		super();
		this.id = 0;
		this.titulo = "";
		this.tipoEvento = null;
		this.asistentes = new ArrayList<>();
		this.sala = null;
		this.fechaHora = LocalDateTime.now();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(asistentes, other.asistentes) && Objects.equals(sala, other.sala)
				&& tipoEvento == other.tipoEvento;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", tipoEvento=" + tipoEvento + ", asistentes=" + asistentes + ", sala=" + sala + ", fecha=" + fechaHora + "]";
	}
}
