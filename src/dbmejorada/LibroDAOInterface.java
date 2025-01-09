package dbmejorada;

import domain.Cliente;
import domain.Evento;
import domain.Libro;

public interface LibroDAOInterface {
	boolean addLibro(Libro libro);
	LibroDTO getLibro(long isbn);
	void añadirReserva(long isbn, int diasDevolucion, Cliente cliente);
}
