package dbmejorada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public boolean isUsuarioAsistente(String dni_usuario) {
		String sql = "SELECT dni_asistente FROM AsistenciaEvento WHERE dni_asistente = dni_usuario";
		try {
			PreparedStatement preparedStmt = conexionBD.prepareStatement(sql);
			ResultSet rs1 = preparedStmt.executeQuery();
			return rs1.next();
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar un evento: ", e);
				return false;
			}
		}
		
		return false;
	}
	
}
