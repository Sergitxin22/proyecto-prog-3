package BiblioTech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Evento {

	private int id;
	private String titulo;
	private TipoEvento tipoEvento;
	private ArrayList<Cliente> asistentes;
	private SalaEventos sala;
	private LocalDate fecha;
	private int hora;

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
	
	public SalaEventos getSala() {
		return sala;
	}
	
	public void setSala(SalaEventos sala) {
		this.sala = sala;
	}

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
	
	public Evento(int id, String titulo, TipoEvento tipoEvento, ArrayList<Cliente> asistentes, SalaEventos sala, LocalDate fecha, int hora) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.tipoEvento = tipoEvento;
		this.asistentes = asistentes;
		this.sala = sala;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public Evento() {
		super();
		this.id = 0;
		this.titulo = "";
		this.tipoEvento = null;
		this.asistentes = new ArrayList<>();
		this.sala = null;
		this.fecha = null;
		this.hora = 0;
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
		return "Evento [id=" + id + ", tipoEvento=" + tipoEvento + ", asistentes=" + asistentes + ", sala=" + sala + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
}
