package db;

import java.util.ArrayList;

public interface ReservaLibroDAOInterface {
	boolean addReservaLibro(ReservaLibroDTO reserva);
	ReservaLibroDTO getReservaLibroById(int idReservaSala);
	ArrayList<ReservaLibroDTO> getHistorialByDniUsuarioDTO(String dniUsuario);
	boolean deleteReservaLibroById(int idReservaSala);
	boolean isLibroDisponible(ReservaLibroDTO reserva); 
	boolean libroLeidoByUsuarioDni(String dniCliente, Long isbnLibro);
	boolean updateReservaLibro(ReservaLibroDTO reserva);
	void borrarRegistros();
}
