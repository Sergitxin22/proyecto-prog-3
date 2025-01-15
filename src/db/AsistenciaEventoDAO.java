package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Main;

public class AsistenciaEventoDAO implements AsistenciaEventoDAOInterface{
	private Connection conexionBD;
	private Logger logger;
	
	public AsistenciaEventoDAO() {
		conexionBD = Main.getConexionBD();
		logger = Main.getLogger();
	}
	
	public AsistenciaEventoDAO(Connection conexionBD, Logger logger) {
		this.conexionBD = conexionBD;
		this.logger = logger;
	}
	
	@Override
	public boolean addAsistenciaEvento(AsistenciaEventoDTO asistenciaEventoDTO) {
		String insertsql= "INSERT INTO AsistenciaEvento(dni_asistente, id_evento) VALUES (?, ?)";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(insertsql);
			preparedStmt.setString(1, asistenciaEventoDTO.getDni_asistente());
			preparedStmt.setInt(2, asistenciaEventoDTO.getId_evento());
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar un evento: ", e);
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean isUsuarioAsistente(String dni_usuario, int id_evento) {
		String sql = "SELECT dni_asistente FROM AsistenciaEvento WHERE dni_asistente = ? AND id_evento = ?";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(sql);
			preparedStmt.setString(1, dni_usuario);
			preparedStmt.setInt(2, id_evento);
			ResultSet rs1 = preparedStmt.executeQuery();
			boolean result = rs1.next();
			preparedStmt.close();
			return result;
			
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar un evento: ", e);
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = ("DELETE FROM AsistenciaEvento;");

			stmt.executeUpdate(instruccion);
			stmt.close();
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
}
