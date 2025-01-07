package dbmejorada;

import domain.Usuario;

public interface UsuarioDAOInterface {
	boolean addUsuario(Usuario usuario);
	boolean isUsuarioCorrecto(String dni, String password);
	UsuarioDTO getUsuario(String dni);
	void getDatosAdicionalesUsuario(UsuarioDTO usuario);
	boolean updatePassword(UsuarioDTO usuario, String password);
	void borrarRegistros();
}
