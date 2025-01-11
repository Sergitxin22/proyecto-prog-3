package dbmejorada;

import domain.Recurso;
import domain.Sala;

import java.util.ArrayList;
import java.util.HashMap;

import domain.Cliente;

public interface SalaDAOInterface {
	boolean addSala(Sala sala);
	boolean deleteSala(int id);
	SalaDTO getSala(int id);
	ArrayList<Sala> getSalas();
	int getTipoSalaId(String tipoSala);
	String getTipoSala(int id);
	int getRecursoId(Recurso recurso);
	ArrayList<Integer> getIdsRecursosDisponiblesByIdSala(int idSala);
	Recurso getRecurso(int id);
	HashMap<Integer, Cliente> getClientesPorBloque();
	void borrarRegistros();
}
