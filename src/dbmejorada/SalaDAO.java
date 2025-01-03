package dbmejorada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Cliente;
import domain.Recurso;
import domain.Reserva;
import domain.Sala;
import domain.SalaPrivada;
import domain.SalaPublica;
import main.Main;

public class SalaDAO implements SalaDAOInterface {

	private Connection conexionBD;
	private Logger logger;
	
	public SalaDAO() {
		conexionBD = Main.getConexionBD();
		logger = Main.getLogger();
	}
	
	@Override
	public boolean addSala(Sala sala) {
		try {
            String insertSQLSala = "INSERT INTO Sala VALUES (?,?,?,?)";
            PreparedStatement preparedStmtSala = conexionBD.prepareStatement(insertSQLSala);
            preparedStmtSala.setInt(1, sala.getId());
            preparedStmtSala.setInt(2, sala.getPiso());
            preparedStmtSala.setInt(3, sala.getCapacidad());
            
            if (sala instanceof SalaPublica) {
            	preparedStmtSala.setString(4, "PUBLICA");
            	
            	String insertSQLAsistenciaSalaPublica = "INSERT INTO SalaPrivadaRecurso VALUES (?,?)";
            	for (Integer bloque : (((SalaPublica) sala).getClientesPorBloque().keySet())) {
            		PreparedStatement preparedStmtAsistenciaSalaPublica = conexionBD.prepareStatement(insertSQLAsistenciaSalaPublica);
            		
            		preparedStmtAsistenciaSalaPublica.setString(1, ((SalaPublica) sala).getClientesPorBloque().get(bloque).getDni());
            		preparedStmtAsistenciaSalaPublica.setInt(2, bloque);
            		
            		preparedStmtAsistenciaSalaPublica.executeUpdate();
            		preparedStmtAsistenciaSalaPublica.close();
            	}
            } else if (sala instanceof SalaPrivada) {
            	preparedStmtSala.setString(4, "PRIVADA");
            	
            	String insertSQLSalaPrivadaRecurso = "INSERT INTO SalaPrivadaRecurso(id_recurso, id_sala) VALUES (?,?)";
            	for (Recurso recurso : ((SalaPrivada) sala).getRecursos()) {
            		PreparedStatement preparedStmtSalaPrivadaRecurso = conexionBD.prepareStatement(insertSQLSalaPrivadaRecurso);
            		preparedStmtSalaPrivadaRecurso.setInt(1, getRecursoId(recurso));
            		preparedStmtSalaPrivadaRecurso.setInt(2, sala.getId());
            		
            		preparedStmtSalaPrivadaRecurso.executeUpdate();
            		preparedStmtSalaPrivadaRecurso.close();
            	}
            	
        	
        	for (Reserva reserva : ((SalaPrivada) sala).getReservas()) {
        		Main.getReservaSalaDAO().addReservaSala(reserva);
        	}
        	
            } else {
            	preparedStmtSala.setString(4, "EVENTOS");
            	// TODO: Asignarle el evento cuando EventoDAO esté hecho
            }

            preparedStmtSala.executeUpdate();
            preparedStmtSala.close();

        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la sala: ", e);
            return false;
        }
		
		return true;
	}

	@Override
	public SalaDTO getSala(int id) {
		SalaDTO sala = null;
		
		String selectSQL = "SELECT * FROM Sala WHERE id = ?";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                   sala = new SalaDTO();
                   sala.setId(rs.getInt("id"));
                   sala.setCapacidad(rs.getInt("capacidad"));
                   sala.setPiso(rs.getInt("piso"));
                   sala.setTipo(getTipoSala(rs.getInt("tipo")));
                }
                System.out.println("Sala recuperada correctamente");
                
                getDatosAdicionalesSala(sala);
    			preparedStmt.close();
                
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la sala: ", e);
				return sala;
			}
		} 
		
		return sala;	
	}

	@Override
	public void getDatosAdicionalesSala(SalaDTO sala) {

		sala.setEvento(null); // TODO
		ArrayList<Recurso> recursos = new ArrayList<>();
		String selectSQL = "SELECT id_recurso FROM SalaPrivadaRecurso WHERE id_sala = ?";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setInt(1, sala.getId());
			
			try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                   recursos.add(getRecurso(rs.getInt("id_recurso")));
                }
                
                System.out.println("Datos adicionales de la sala recuperados correctamente");
                sala.setRecursos(recursos);
                preparedStmt.close();
            }
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar los datos adicionales de la sala: ", e);
			}
		}
	}

	@Override
	public int getTipoSalaId(String tipoSala) {
		switch(tipoSala) {
		case "PUBLICA": return 0;
		case "PRIVADA": return 1;
		default: return 2;
		}
	}

	@Override
	public String getTipoSala(int id) {
		switch(id) {
		case 0: return "PUBLICA";
		case 1: return "PRIVADA";
		default: return "EVENTOS";
		}
	}
	
	@Override
	public int getRecursoId(Recurso recurso) {
		if (recurso.equals(Recurso.ORDENADORES)) {
			return 0;
		} else if (recurso.equals(Recurso.PROYECTOR)) {
			return 1;
		} else {
			return 2;
		}
		
	}
	
	@Override
	public ArrayList<Integer> getIdsRecursosDisponiblesByIdSala(int idSala) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Recurso getRecurso(int id) {
		switch(id) {
		case 0: return Recurso.ORDENADORES;
		case 1: return Recurso.PROYECTOR;
		default: return Recurso.PIZARRA;
		}
	}

	@Override
	public HashMap<Integer, Cliente> getClientesPorBloque() {
		HashMap<Integer, Cliente> result = new HashMap<>();
		
		// TODO
		
		return result;
	}

	

	
	
}
