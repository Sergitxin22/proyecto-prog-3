package dbmejorada;

import java.util.ArrayList;

import domain.Reserva;

public interface ReservaSalaDAOInterface {
	boolean addReservaSala(Reserva reserva);
	Reserva getReservaSalaById(int idSala);
	Reserva getReservaSalaByUsuarioDTO(UsuarioDTO usuario);
	boolean deleteReservaSalaById(int idSala);
	boolean isSalaReservable(Reserva reserva);
	boolean updateReservaSala(Reserva reserva);
	// TODO Refactorizar para recibir LocalDateTime
	ArrayList<Integer> getIdSalasDisponiblesEntreFechas(String fechaI, String fechaF);
	void borrarRegistros();
}
