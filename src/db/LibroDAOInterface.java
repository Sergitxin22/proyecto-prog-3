package db;

import java.util.ArrayList;

import domain.Cliente;
import domain.Libro;

public interface LibroDAOInterface {
	boolean addLibro(Libro libro);
	LibroDTO getLibro(long isbn);
	ArrayList<Libro> getLibros();
	boolean añadirReserva(long isbn, int diasDevolucion, Cliente cliente);
	ArrayList<LibroDTO> getHistorialByCliente(String dniCliente);
	boolean libroLeidoByDniCliente(String dniCliente, Long isbn);
	boolean updateLibro(LibroDTO libro, long isbnAntiguo);
	boolean deleteLibroByIsbn(long isbnLibro);
	void borrarRegistros();
}
