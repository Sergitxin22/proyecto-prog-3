package dbmejorada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

	@Override
	public boolean addEvento(Evento evento) {
		try {
            String insertSQLEvento = "INSERT INTO Evento(id, titulo, id_tipo_evento, id_sala, fecha) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmtEvento = conexionBD.prepareStatement(insertSQLEvento);
            preparedStmtEvento.setInt(1, evento.getId());
            preparedStmtEvento.setString(2, evento.getTitulo());
            preparedStmtEvento.setInt(3, getTipoEventoID(evento.getTipoEvento()));
            preparedStmtEvento.setInt(4, evento.getSala().getId());
            preparedStmtEvento.setString(5, evento.getFechaHora().toString());

            preparedStmtEvento.executeUpdate();
            preparedStmtEvento.close();

            String insertSQL = "INSERT INTO AsistenciaEvento(id, dni_cliente) VALUES (?, ?)";
            
            for (UsuarioDTO asistente : evento.getAsistentes()) {
            	 PreparedStatement preparedStmtAsistenciaEvento = conexionBD.prepareStatement(insertSQL);
                 preparedStmtAsistenciaEvento.setInt(1, evento.getId());
                 preparedStmtAsistenciaEvento.setString(2, asistente.getDni());
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
                System.out.println("Evento recuperado correctamente");
                
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
	        	while(rs.next()) {
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
			
			while(rs.next()) {
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
	public TipoEvento getTipoEvento(int id) {
		switch(id) {
		case 0: return TipoEvento.CHARLA;
		case 1: return TipoEvento.DEBATE;
		case 2: return TipoEvento.SEMINARIO;
		default : return TipoEvento.TALLER;
		}
	}

	@Override
	public int getTipoEventoID(TipoEvento tipoEvento) {
		if (tipoEvento.equals(TipoEvento.CHARLA)) {
			return 0;
		} else if (tipoEvento.equals(TipoEvento.DEBATE)) {
			return 1;
		} else if (tipoEvento.equals(TipoEvento.SEMINARIO)) {
			return 2;
		} else {
			return 3;
		}
	}
	
}
