package dbmejorada;

import BiblioTech.Usuario;

public interface ClienteDAOInterface {
	boolean guardarCliente(Usuario cliente);
	void borrarRegistros();
}
