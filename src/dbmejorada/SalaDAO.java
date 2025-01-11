package dbmejorada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
//		borrarRegistros();
		
//		try {
////		 Inicialización de los recursos posibles de salas privadas en la BBDD:
//			
//		String insertSQLOrdenadores = "INSERT INTO Recurso VALUES (NULL, ?)";
//		String insertSQLProyector = "INSERT INTO Recurso VALUES (NULL, ?)";
//		String insertSQLPizarra = "INSERT INTO Recurso VALUES (NULL, ?)";
//		
//		PreparedStatement preparedStmtOrdenadores = conexionBD.prepareStatement(insertSQLOrdenadores);
//		PreparedStatement preparedStmtProyector = conexionBD.prepareStatement(insertSQLProyector);
//		PreparedStatement preparedStmtPizarra = conexionBD.prepareStatement(insertSQLPizarra);
//		
//		preparedStmtOrdenadores.setString(1, "ORDENADORES");
//		preparedStmtProyector.setString(1, "PROYECTOR");
//		preparedStmtPizarra.setString(1, "PIZARRA");
//		
//		preparedStmtOrdenadores.executeUpdate();
//		preparedStmtProyector.executeUpdate();
//		preparedStmtPizarra.executeUpdate();
//		
//		preparedStmtOrdenadores.close();
//		preparedStmtProyector.close();
//		preparedStmtPizarra.close();
//		
//		// Inicialización de los tipos posibles de salas privadas en la BBDD:
//		
//		String insertSQLPublica = "INSERT INTO TipoSala VALUES (NULL, ?)";
//		String insertSQLPrivada = "INSERT INTO TipoSala VALUES (NULL, ?)";
//		String insertSQLEventos = "INSERT INTO TipoSala VALUES (NULL, ?)";
//		
//		PreparedStatement preparedStmtPublica = conexionBD.prepareStatement(insertSQLPublica);
//		PreparedStatement preparedStmtPrivada = conexionBD.prepareStatement(insertSQLPrivada);
//		PreparedStatement preparedStmtEventos = conexionBD.prepareStatement(insertSQLEventos);
//		
//		preparedStmtPublica.setString(1, "PUBLICA");
//		preparedStmtPrivada.setString(1, "PRIVADA");
//		preparedStmtEventos.setString(1, "EVENTOS");
//		
//		preparedStmtPublica.executeUpdate();
//		preparedStmtPrivada.executeUpdate();
//		preparedStmtEventos.executeUpdate();
//		
//		preparedStmtPublica.close();
//		preparedStmtPrivada.close();
//		preparedStmtEventos.close();
//		
//		} catch (SQLException e) {
//			if (logger != null)
//                logger.log(Level.SEVERE, "Error al añadir los recursos: ", e);
//		}
	}
	
	@Override
	public boolean addSala(Sala sala) {
		try {
            String insertSQLSala = "INSERT INTO Sala VALUES (NULL,?,?,?)";
            PreparedStatement preparedStmtSala = conexionBD.prepareStatement(insertSQLSala);
            preparedStmtSala.setInt(1, sala.getPiso());
            preparedStmtSala.setInt(2, sala.getCapacidad());
            
            if (sala instanceof SalaPublica) {
            	preparedStmtSala.setInt(3, getTipoSalaId("PUBLICA"));
            	
            	for (Integer bloque : (((SalaPublica) sala).getClientesPorBloque().keySet())) {
            		if (((SalaPublica) sala).getClientesPorBloque().get(bloque) != null) {
            			
            			ReservaSalaPublicaDTO reservaSalaPublicaDTO = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), ((SalaPublica) sala).getClientesPorBloque().get(bloque).getDni(), bloque);
	            		if (Main.getReservaSalaPublicaDAO().addReservaSalaPublica(reservaSalaPublicaDTO));
            		}
            	}
            } else if (sala instanceof SalaPrivada) {
            	preparedStmtSala.setInt(3, getTipoSalaId("PRIVADA"));
            	
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
            	preparedStmtSala.setInt(3, getTipoSalaId("EVENTOS"));  	
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
	public boolean deleteSala(int id) {
		String deleteSQL = "DELETE FROM SALA WHERE id = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.executeUpdate();
			preparedStmt.close();
			
		} catch (SQLException e) {
			 if (logger != null)
	                logger.log(Level.SEVERE, "Error al eliminar la sala: ", e);
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
							
					switch (getTipoSala(rs.getInt("tipo"))) {
						case "PUBLICA":
							SalaPublica salaPublica = new SalaPublica(rs.getInt("capacidad"), 0, rs.getInt("piso"));
							result.add(salaPublica);
							break;
						case "PRIVADA":
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
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> idTipoSalaByTipo = (HashMap<String, Integer>) getIdsTipoSala(0);
		
		return idTipoSalaByTipo.get(tipoSala);
	}

	@Override
	public String getTipoSala(int id) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, String> tipoSalaById = (HashMap<Integer, String>) getIdsTipoSala(1);
		
		return tipoSalaById.get(id);
	}
	
	@Override
	public int getRecursoId(Recurso recurso) {
		@SuppressWarnings("unchecked")
		HashMap<Recurso, Integer> idRecursoByRecurso = (HashMap<Recurso, Integer>) getIdsRecurso(0);
		
		return idRecursoByRecurso.get(recurso);	
	}
	
	@Override
	public Recurso getRecurso(int id) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, Recurso> recursoById = (HashMap<Integer, Recurso>) getIdsRecurso(1);
		
		return recursoById.get(id);	
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
	public HashMap<Integer, Cliente> getClientesPorBloque() {
		HashMap<Integer, Cliente> result = new HashMap<>();
		
		// TODO
		
		return result;
	}	
	
	private HashMap<?, ?> getIdsTipoSala(int ordenMapa){ // ordenMapa 0 --> tipo,id, ordenMapa 1 --> id,tipo
		
		HashMap<Object, Object> idsTipoSala = new HashMap<>();
		String selectSql = "SELECT * FROM TipoSala;";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSql);
			try (ResultSet rs = preparedStmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String tipo = rs.getString("tipo");
					
					if (ordenMapa == 0) {
						idsTipoSala.put(tipo, id);
					}else {
						idsTipoSala.put(id, tipo);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idsTipoSala;
	}
	
	private HashMap<?, ?> getIdsRecurso(int ordenMapa){ // ordenMapa 0 --> recurso,id, ordenMapa 1 --> id,recurso
		
		HashMap<Object, Object> idsRecurso = new HashMap<>();
		String selectSql = "SELECT * FROM Recurso;";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSql);
			try (ResultSet rs = preparedStmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					Recurso descripcion = Recurso.valueOf(rs.getString("descripcion"));
					
					if (ordenMapa == 0) {
						idsRecurso.put(descripcion, id);
					}else {
						idsRecurso.put(id, descripcion);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idsRecurso;
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
