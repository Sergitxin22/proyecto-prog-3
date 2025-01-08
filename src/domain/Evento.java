package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import dbmejorada.EventoDTO;
import dbmejorada.SalaDTO;
import dbmejorada.UsuarioDTO;
import main.Main;

public class Evento {

	private int id;
	private String titulo;
	private TipoEvento tipoEvento;
	private ArrayList<UsuarioDTO> asistentes;
	private Sala sala;
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
	
	public ArrayList<UsuarioDTO> getAsistentes() {
		return asistentes;
	}
	
	public void setAsistentes(ArrayList<UsuarioDTO> asistentes) {
		this.asistentes = asistentes;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
	
	public Evento(int id, String titulo, TipoEvento tipoEvento, ArrayList<UsuarioDTO> asistentes, Sala sala, LocalDateTime fechaHora) {
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
	

	public Evento(EventoDTO eventoDTO) {
		super();
		this.id = eventoDTO.getId();
		this.titulo = eventoDTO.getTitulo();
		this.tipoEvento = eventoDTO.getTipoEvento();
		this.asistentes = new ArrayList<>();
		SalaDTO sala = Main.getSalaDAO().getSala(eventoDTO.getIdSala());
		this.sala = new SalaEventos(sala, eventoDTO.getId());
		this.fechaHora = eventoDTO.getFechaHora();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", tipoEvento=" + tipoEvento + ", asistentes=" + asistentes + ", sala=" + sala + ", fecha=" + fechaHora + "]";
	}
}
