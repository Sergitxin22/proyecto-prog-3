package dbmejorada;

import java.util.ArrayList;

import domain.Reserva;

public interface ReservaSalaDAOInterface {
	boolean addReservaSala(ReservaSalaDTO reserva);
	ReservaSalaDTO getReservaSalaById(int idReservaSala);
	ArrayList<ReservaSalaDTO> getReservasSalaByUsuarioDTO(UsuarioDTO usuario);
	boolean deleteReservaSalaById(int idSala);
	boolean isSalaReservable(ReservaSalaDTO reserva);
	boolean updateReservaSala(ReservaSalaDTO reserva);
	ArrayList<ReservaSalaDTO> getReservasSalaByIdSala(int idSala);
	// TODO Refactorizar para recibir LocalDateTime
	ArrayList<Integer> getIdSalasDisponiblesEntreFechas(String fechaI, String fechaF);
	void borrarRegistros();
}
