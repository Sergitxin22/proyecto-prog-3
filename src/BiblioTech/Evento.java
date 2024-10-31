package BiblioTech;

import java.util.ArrayList;
import java.util.Objects;

public class Evento {

	private String titulo;
	private TipoEvento tipoEvento;
	private ArrayList<Cliente> asistentes;
	private SalaEventos sala;
	
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
	
	public Evento(String titulo, TipoEvento tipoEvento, ArrayList<Cliente> asistentes, SalaEventos sala) {
		super();
		this.titulo = titulo;
		this.tipoEvento = tipoEvento;
		this.asistentes = asistentes;
		this.sala = sala;
	}
	
	public Evento() {
		super();
		this.titulo = "";
		this.tipoEvento = null;
		this.asistentes = new ArrayList<>();
		this.sala = new SalaEventos();
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
		return "Evento [tipoEvento=" + tipoEvento + ", asistentes=" + asistentes + ", sala=" + sala + "]";
	}
}
