package dbmejorada;

public interface AsistenciaEventoDAOInterface {
		boolean addAsistenciaEvento(AsistenciaEventoDTO asistenciaEventoDTO);
		boolean isUsuarioAsistente(String dni_usuario);
}
