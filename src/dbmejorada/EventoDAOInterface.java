package dbmejorada;

import domain.Evento;
import domain.TipoEvento;

public interface EventoDAOInterface {
	boolean addEvento(Evento evento);
	EventoDTO getEvento(int id);
	TipoEvento getTipoEvento(int id);
	int getTipoEventoID(TipoEvento tipoEvento);
	
}
