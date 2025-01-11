package dbmejorada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import domain.Cliente;
import domain.Recurso;
import domain.Reserva;
import domain.Sala;
import domain.SalaEventos;
import domain.SalaPrivada;
import domain.SalaPublica;
import main.Main;

public class SalaDAO implements SalaDAOInterface {

	private Connection conexionBD;
	private Logger logger;
	
	public SalaDAO() {
		conexionBD = Main.getConexionBD();
		logger = Main.getLogger();	
		
		try {
		// Inicialización de los recursos posibles de salas privadas en la BBDD:
			
		String insertSQLOrdenadores = "INSERT INTO Recurso VALUES (?, ?)";
		String insertSQLProyector = "INSERT INTO Recurso VALUES (?, ?)";
		String insertSQLPizarra = "INSERT INTO Recurso VALUES (?, ?)";
		
		PreparedStatement preparedStmtOrdenadores = conexionBD.prepareStatement(insertSQLOrdenadores);
		PreparedStatement preparedStmtProyector = conexionBD.prepareStatement(insertSQLProyector);
		PreparedStatement preparedStmtPizarra = conexionBD.prepareStatement(insertSQLPizarra);
		
		preparedStmtOrdenadores.setInt(1, 0);
		preparedStmtOrdenadores.setString(2, "ORDENADORES");
		preparedStmtProyector.setInt(1, 1);
		preparedStmtProyector.setString(2, "PROYECTOR");
		preparedStmtPizarra.setInt(1, 2);
		preparedStmtPizarra.setString(2, "PIZARRA");
		
		preparedStmtOrdenadores.executeUpdate();
		preparedStmtProyector.executeUpdate();
		preparedStmtPizarra.executeUpdate();
		
		preparedStmtOrdenadores.close();
		preparedStmtProyector.close();
		preparedStmtPizarra.close();
		
		// Inicialización de los tipos posibles de salas privadas en la BBDD:
		
		String insertSQLPublica = "INSERT INTO TipoSala VALUES (?, ?)";
		String insertSQLPrivada = "INSERT INTO TipoSala VALUES (?, ?)";
		String insertSQLEventos = "INSERT INTO TipoSala VALUES (?, ?)";
		
		PreparedStatement preparedStmtPublica = conexionBD.prepareStatement(insertSQLPublica);
		PreparedStatement preparedStmtPrivada = conexionBD.prepareStatement(insertSQLPrivada);
		PreparedStatement preparedStmtEventos = conexionBD.prepareStatement(insertSQLEventos);
		
		preparedStmtPublica.setInt(1, 0);
		preparedStmtPublica.setString(2, "PUBLICA");
		preparedStmtPrivada.setInt(1, 1);
		preparedStmtPrivada.setString(2, "PRIVADA");
		preparedStmtEventos.setInt(1, 2);
		preparedStmtEventos.setString(2, "EVENTOS");
		
		preparedStmtPublica.executeUpdate();
		preparedStmtPrivada.executeUpdate();
		preparedStmtEventos.executeUpdate();
		
		preparedStmtPublica.close();
		preparedStmtPrivada.close();
		preparedStmtEventos.close();
		
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir los recursos: ", e);
		}
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
            	preparedStmtSala.setInt(4, 0);
            	
            	for (Integer bloque : (((SalaPublica) sala).getClientesPorBloque().keySet())) {
            		if (((SalaPublica) sala).getClientesPorBloque().get(bloque) != null) {
            			
            			ReservaSalaPublicaDTO reservaSalaPublicaDTO = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), ((SalaPublica) sala).getClientesPorBloque().get(bloque).getDni(), bloque);
	            		if (Main.getReservaSalaPublicaDAO().addReservaSalaPublica(reservaSalaPublicaDTO));
            		}
            	}
            } else if (sala instanceof SalaPrivada) {
            	preparedStmtSala.setInt(4, 1);
            	
            	String insertSQLSalaPrivadaRecurso = "INSERT INTO SalaPrivadaRecurso(id_recurso, id_sala) VALUES (?,?)";
            	for (Recurso recurso : ((SalaPrivada) sala).getRecursos()) {
            		PreparedStatement preparedStmtSalaPrivadaRecurso = conexionBD.prepareStatement(insertSQLSalaPrivadaRecurso);
            		preparedStmtSalaPrivadaRecurso.setInt(1, getRecursoId(recurso));
            		preparedStmtSalaPrivadaRecurso.setInt(2, sala.getId());
            		
            		preparedStmtSalaPrivadaRecurso.executeUpdate();
            		preparedStmtSalaPrivadaRecurso.close();
            	}
            	
        	
            	for (Reserva reserva : ((SalaPrivada) sala).getReservas()) {
            		Main.getReservaSalaPrivadaDAO().addReservaSalaPrivada(new ReservaSalaPrivadaDTO(0, reserva.getHoraEntrada(), reserva.getHoraSalida(), reserva.getFechaReserva(),  reserva.getClienteReserva().getDni(), reserva.getSala().getId()));
            	} 
            	
            } else {
            	preparedStmtSala.setInt(4, 2);  	
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
                   sala.setIdTipo(rs.getInt("tipo"));
                }
                System.out.println("Sala recuperada correctamente");
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
	public ArrayList<Sala> getSalas() {
		ArrayList<Sala> result = new ArrayList<>();
		
		String selectSQL = "SELECT * FROM Sala";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSQL);
			try (ResultSet rs = preparedStmt.executeQuery()) {
				while(rs.next()) {
							
					switch (rs.getInt("tipo")) {
					case 0:
						SalaPublica salaPublica = new SalaPublica(rs.getInt("capacidad"), 0, rs.getInt("piso"));
						result.add(salaPublica);
						break;
					case 1:
						ArrayList<Recurso> recursos = new ArrayList<>();
						for (Integer integer : getIdsRecursosDisponiblesByIdSala(rs.getInt("id"))) {
							recursos.add(getRecurso(integer));
						}
						
						SalaPrivada salaPrivada = new SalaPrivada(rs.getInt("capacidad"), rs.getInt("id"), rs.getInt("piso"), recursos, new ArrayList<>());
						result.add(salaPrivada);
						break;
					default:
						SalaEventos salaEventos = new SalaEventos(rs.getInt("capacidad"), rs.getInt("id"), rs.getInt("piso"));
						result.add(salaEventos);
						
					}
				}
				
				preparedStmt.close();
			}
			
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar alguna sala: ", e);
				return result;
			}
		}
		return result;
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
		ArrayList<Integer> result = new ArrayList<>();
		
		String selectSQL = "SELECT id_recurso FROM SalaPrivadaRecurso WHERE id_sala = ?";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setInt(1, idSala);
			
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getInt("id_recurso"));
			}
			
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar algún recurso: ", e);
				return result;
			}
		}
		
		return result;
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
	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = "DELETE FROM Sala;"
					+ "DELETE FROM Recurso;"
					+ "DELETE FROM TipoSala;";
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}

}
