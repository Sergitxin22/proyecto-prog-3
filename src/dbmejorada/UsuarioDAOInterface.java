package dbmejorada;

import BiblioTech.Usuario;

public interface UsuarioDAOInterface {
	boolean addUsuario(Usuario usuario);
	UsuarioDTO getUsuario(String dni, String password);
	void getDatosAdicionalesUsuario(UsuarioDTO usuario);
	boolean updatePassword(UsuarioDTO usuario, String password);
	void borrarRegistros();
}
