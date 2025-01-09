package dbmejorada;

import java.util.ArrayList;

import domain.Cliente;
import domain.Evento;
import domain.Libro;

public interface LibroDAOInterface {
	boolean addLibro(Libro libro);
	LibroDTO getLibro(long isbn);
	void añadirReserva(long isbn, int diasDevolucion, Cliente cliente);
	ArrayList<LibroDTO> getHistorialByCliente(String dniCliente);
	boolean libroLeidoByDniCliente(String dniCliente, Long isbn);
	boolean updateLibro(LibroDTO libro);
	boolean deleteLibroByIsbn(long isbnLibro);
}
