package db;

import domain.Recurso;
import domain.Sala;
import domain.SalaPublica;

import java.util.ArrayList;
import java.util.HashMap;

import domain.Cliente;

public interface SalaDAOInterface {
	boolean addSala(Sala sala);
	boolean deleteSala(int id);
	SalaDTO getSala(int id);
	SalaPublica getSalaPublica();
	ArrayList<Sala> getSalas();
	int getTipoSalaId(String tipoSala);
	String getTipoSala(int id);
	int getRecursoId(Recurso recurso);
	ArrayList<Integer> getIdsRecursosDisponiblesByIdSala(int idSala);
	Recurso getRecurso(int id);
	HashMap<Integer, Cliente> getClientesPorBloque();
	boolean addRecursos();
	boolean addTiposSala();
	void borrarRegistros();
}
