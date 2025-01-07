package dbmejorada;

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
	// TODO Refactorizar para recibir LocalDateTime
	ArrayList<Integer> getIdSalasPrivadasDisponiblesEntreFechas(String fechaI, String fechaF);
	void borrarRegistros();
}
