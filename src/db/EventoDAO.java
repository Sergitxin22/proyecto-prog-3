package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Evento;
import domain.SalaEventos;
import domain.TipoEvento;
import main.Main;

public class EventoDAO implements EventoDAOInterface {

	private Connection conexionBD;
	private Logger logger;

	public EventoDAO() {
		this.conexionBD = Main.getConexionBD();
		this.logger = Main.getLogger();
	}
	
	public EventoDAO(Connection conexionBD, Logger logger) {
		this.conexionBD = conexionBD;
		this.logger = logger;
	}

	@Override
	public boolean addEvento(Evento evento) {
		try {
			String insertSQLEvento = "INSERT INTO Evento(id, titulo, id_tipo_evento, id_sala, fecha) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmtEvento = conexionBD.prepareStatement(insertSQLEvento);
			preparedStmtEvento.setInt(1, evento.getId());
			preparedStmtEvento.setString(2, evento.getTitulo());
			preparedStmtEvento.setInt(3, getTipoEventoId(evento.getTipoEvento()));
			preparedStmtEvento.setInt(4, evento.getSala().getId());
			preparedStmtEvento.setString(5, evento.getFechaHora().toString());

			preparedStmtEvento.executeUpdate();
			preparedStmtEvento.close();

			String insertSQL = "INSERT INTO AsistenciaEvento(id, dni_cliente) VALUES (?, ?)";

			for (UsuarioDTO usuario : evento.getAsistentes()) {
				PreparedStatement preparedStmtAsistenciaEvento = conexionBD.prepareStatement(insertSQL);
				preparedStmtAsistenciaEvento.setInt(1, evento.getId());
				preparedStmtAsistenciaEvento.setString(2, usuario.getDni());
				preparedStmtAsistenciaEvento.executeUpdate();
				preparedStmtAsistenciaEvento.close();
			}

			return true;
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al añadir el evento: ", e);
			return false;
		}
	}

	@Override
	public boolean deleteEvento(int id) {
		String deleteSQL = "DELETE FROM EVENTO WHERE id = ?";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(deleteSQL);
			preparedStmt.setInt(1, id);
			preparedStmt.executeUpdate();
			preparedStmt.close();

		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al eliminar el evento: ", e);
				return false;
			}
		}
		return true;
	}

	@Override
	public EventoDTO getEvento(int id) {
		EventoDTO evento = null;

		String selectSQL = "SELECT * FROM Evento WHERE id = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);

			try (ResultSet rs = preparedStmt.executeQuery()) {

				while (rs.next()) {
					evento = new EventoDTO();
					evento.setId(rs.getInt("id"));
					evento.setTitulo(rs.getString("titulo"));
					evento.setFecha(LocalDateTime.parse(rs.getString("fecha"))); // TODO: Comprobar que esto funciona
					evento.setIdSala(rs.getInt("id_sala"));
					evento.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				}

				preparedStmt.close();

			}

		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar el evento: ", e);
				return evento;
			}
		}

		return evento;
	}

	@Override
	public ArrayList<Evento> getEventos() {
		ArrayList<Evento> result = new ArrayList<>();

		String selectSQL = "SELECT * FROM Evento";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(selectSQL);

			try (ResultSet rs = preparedStmt.executeQuery()) {
				while (rs.next()) {
					
					SalaEventos salaEventos = new SalaEventos(Main.getSalaDAO().getSala(rs.getInt("id_sala")));
					Evento evento = new Evento(rs.getInt("id"), rs.getString("titulo"), getTipoEvento(rs.getInt("id_tipo_evento")), getAsistentesEvento(rs.getInt("id")), salaEventos, LocalDateTime.parse(rs.getString("fecha")));
					result.add(evento);
				}

				preparedStmt.close();

			}

		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar un evento: ", e);
				return result;
			}
		}

		return result;
	}

	@Override
	public ArrayList<UsuarioDTO> getAsistentesEvento(int id) {
		ArrayList<UsuarioDTO> result = new ArrayList<>();

		String selectSQL = "SELECT * FROM AsistenciaEvento WHERE id_evento = ?";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSQL);
			preparedStmt.setInt(1, id);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				result.add(Main.getUsuarioDAO().getUsuario(rs.getString("dni_asistente")));
			}

			preparedStmt.close();

		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar un evento: ", e);
				return result;
			}
		}

		return result;
	}
	
	@Override
	public int getTipoEventoId(TipoEvento tipoEvento) {
		@SuppressWarnings("unchecked")
		HashMap<TipoEvento, Integer> idTipoEventoByTipo = (HashMap<TipoEvento, Integer>) getIdsTipoEvento(0);
		return idTipoEventoByTipo.get(tipoEvento);
	}

	@Override
	public TipoEvento getTipoEvento(int id) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, TipoEvento> tipoEventoById = (HashMap<Integer, TipoEvento>) getIdsTipoEvento(1);
		
		return tipoEventoById.get(id);
	}

	private HashMap<?, ?> getIdsTipoEvento(int ordenMapa){ // ordenMapa 0 --> tipo,id, ordenMapa 1 --> id,tipo
		HashMap<Object, Object> idsTipoSala = new HashMap<>();
		String selectSql = "SELECT * FROM TipoEvento;";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(selectSql);
			try (ResultSet rs = preparedStmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String tipoString = rs.getString("descripcion");
					
					TipoEvento tipo = TipoEvento.valueOf(tipoString);
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

	@Override
	public boolean addTiposEvento() {
		try {
			String insertSQLCharla = "INSERT INTO TipoEvento VALUES (NULL, ?)";
			String insertSQLDebate = "INSERT INTO TipoEvento VALUES (NULL, ?)";
			String insertSQLSeminario = "INSERT INTO TipoEvento VALUES (NULL, ?)";
			String insertSQLCursillo = "INSERT INTO TipoEvento VALUES (NULL, ?)";
			String insertSQLTaller = "INSERT INTO TipoEvento VALUES (NULL, ?)";
			String insertSQLConferencia = "INSERT INTO TipoEvento VALUES (NULL, ?)";

			PreparedStatement preparedStmtOrdenadores = conexionBD.prepareStatement(insertSQLCharla);
			PreparedStatement preparedStmtProyector = conexionBD.prepareStatement(insertSQLDebate);
			PreparedStatement preparedStmtPizarra = conexionBD.prepareStatement(insertSQLSeminario);
			PreparedStatement preparedStmtCursillo = conexionBD.prepareStatement(insertSQLCursillo);
			PreparedStatement preparedStmtTaller = conexionBD.prepareStatement(insertSQLTaller);
			PreparedStatement preparedStmtConferencia = conexionBD.prepareStatement(insertSQLConferencia);

			preparedStmtOrdenadores.setString(1, "CHARLA");
			preparedStmtProyector.setString(1, "DEBATE");
			preparedStmtPizarra.setString(1, "SEMINARIO");
			preparedStmtCursillo.setString(1, "CURSILLO");
			preparedStmtTaller.setString(1, "TALLER");
			preparedStmtConferencia.setString(1, "CONFERENCIA");

			preparedStmtOrdenadores.executeUpdate();
			preparedStmtProyector.executeUpdate();
			preparedStmtPizarra.executeUpdate();
			preparedStmtCursillo.executeUpdate();
			preparedStmtTaller.executeUpdate();
			preparedStmtConferencia.executeUpdate();

			preparedStmtOrdenadores.close();
			preparedStmtProyector.close();
			preparedStmtPizarra.close();
			preparedStmtCursillo.close();
			preparedStmtTaller.close();
			preparedStmtConferencia.close();

		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al añadir los tipos de evento: ", e);
			return false;
		}
		return true;
	}
	
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = ("DELETE FROM Evento;");

			stmt.executeUpdate(instruccion);
			stmt.close();
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
}
