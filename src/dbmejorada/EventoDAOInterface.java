package dbmejorada;

import java.util.ArrayList;

import domain.Evento;
import domain.TipoEvento;

public interface EventoDAOInterface {
	boolean addEvento(Evento evento);
	boolean deleteEvento(int id);
	EventoDTO getEvento(int id);
	ArrayList<Evento> getEventos();
	ArrayList<UsuarioDTO> getAsistentesEvento(int id);
	TipoEvento getTipoEvento(int id);
	int getTipoEventoID(TipoEvento tipoEvento);
	boolean addTiposEvento();
}
