package dbmejorada;

import domain.Evento;
import domain.Libro;

public interface LibroDAOInterface {
	boolean addLibro(Libro libro);
	LibroDTO getLibro(long isbn);
	
}
