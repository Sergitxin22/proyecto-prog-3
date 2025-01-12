package dbmejorada;

import java.time.LocalDateTime;
import java.util.ArrayList;

import domain.Reserva;

public interface ReservaSalaPrivadaDAOInterface {
	boolean addReservaSalaPrivada(ReservaSalaPrivadaDTO reserva);
	ReservaSalaPrivadaDTO getReservaSalaPrivadaById(int idReservaSala);
	ArrayList<ReservaSalaPrivadaDTO> getReservasSalaPrivadaByUsuarioDTO(UsuarioDTO usuario);
	boolean deleteReservaSalaPrivadaById(int idReservaSala);
	boolean isSalaPrivadaReservable(ReservaSalaPrivadaDTO reserva);
	boolean updateReservaSalaPrivada(ReservaSalaPrivadaDTO reserva);
	ArrayList<ReservaSalaPrivadaDTO> getReservasSalaPrivadaByIdSala(int idSala);
	void borrarRegistros();
	ArrayList<Integer> getIdSalasPrivadasNoDisponiblesEntreFechas(int id, LocalDateTime fechaI, LocalDateTime fechaF);
}
