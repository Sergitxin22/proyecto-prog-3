package dbmejorada;

import domain.Recurso;
import domain.Sala;

import java.util.HashMap;

import domain.Cliente;

public interface SalaDAOInterface {
	boolean addSala(Sala sala);
	SalaDTO getSala(int id);
	void getDatosAdicionalesSala(SalaDTO sala);
	int getTipoSalaId(String tipoSala);
	String getTipoSala(int id);
	int getRecursoID(Recurso recurso);
	Recurso getRecurso(int id);
	HashMap<Integer, Cliente> getClientesPorBloque();
}
