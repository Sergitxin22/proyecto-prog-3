package dbmejorada;

import java.time.LocalDate;
import java.util.ArrayList;

import domain.Reserva;

public interface ReservaSalaPublicaDAOInterface {
	boolean addReservaSalaPublica(ReservaSalaPublicaDTO reservaSalaPublica);
	ReservaSalaPublicaDTO getReservaSalaPublicaById(int idReservaSalaPublica);
	ArrayList<ReservaSalaPublicaDTO> getReservasSalaPublicaByUsuarioDTO(UsuarioDTO usuario);
	boolean deleteReservaSalaPublicaById(int idReservaSalaPublica);
	boolean isSalaPublicaReservable(ReservaSalaPublicaDTO reservaSalaPublica);
	boolean updateReservaSalaPublica(ReservaSalaPublicaDTO reservaSalaPublica);
	ArrayList<Integer> getAsientosDisponibles(LocalDate fecha);
	void borrarRegistros();
}
