package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Main;

public class ReservaSalaPrivadaDAO implements ReservaSalaPrivadaDAOInterface {

	private Connection conexionBD;
    private Logger logger;

    public ReservaSalaPrivadaDAO() {
       	conexionBD = Main.getConexionBD();
      	logger = Main.getLogger();
    }
    
    public ReservaSalaPrivadaDAO(Connection conexionBD, Logger logger) {
       	this.conexionBD = conexionBD;
      	this.logger = logger;
    }
	
	@Override
	public boolean addReservaSalaPrivada(ReservaSalaPrivadaDTO reservaSalaPrivada) {
		String insertSQLReservaSalaPrivada = "INSERT INTO ReservaSalaPrivada(fecha_entrada, fecha_salida, fecha_reserva, dni_cliente, id_sala) VALUES (?, ?, ?, ?, ?)";

    	PreparedStatement preparedStmtReservaSalaPrivada;
		try {
			preparedStmtReservaSalaPrivada = conexionBD.prepareStatement(insertSQLReservaSalaPrivada);
			preparedStmtReservaSalaPrivada.setString(1, reservaSalaPrivada.getfechaEntrada().toString());
	   		preparedStmtReservaSalaPrivada.setString(2, reservaSalaPrivada.getfechaSalida().toString());
	   		preparedStmtReservaSalaPrivada.setString(3, reservaSalaPrivada.getFechaReserva().toString());
	    	preparedStmtReservaSalaPrivada.setString(4, reservaSalaPrivada.getDniCliente());
	    	preparedStmtReservaSalaPrivada.setInt(5, reservaSalaPrivada.getIdSala());
	    		
	    	preparedStmtReservaSalaPrivada.executeUpdate();
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la reserva de la sala privada: ", e);
            return false;
		}
		return true;
	}
    

	@Override
	public ReservaSalaPrivadaDTO getReservaSalaPrivadaById(int idReservaSalaPrivada) {
		ReservaSalaPrivadaDTO reservaSalaPrivada = null;
		
		String insertSQL = "SELECT * FROM ReservaSalaPrivada WHERE id=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setInt(1, idReservaSalaPrivada);
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                	int id = rs.getInt("id");
                	String horaEntrada = rs.getString("fecha_entrada");
                	String horaSalida = rs.getString("fecha_salida");
                	String fechaReserva = rs.getString("fecha_reserva");
                	String dniCliente = rs.getString("dni_cliente");
                	int idSala = rs.getInt("id_sala");
                	
                	reservaSalaPrivada = new ReservaSalaPrivadaDTO(id, LocalDateTime.parse(horaEntrada), LocalDateTime.parse(horaSalida), LocalDate.parse(fechaReserva), dniCliente, idSala);
                }
                return reservaSalaPrivada;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva de sala privada: ", e);
			}
			return reservaSalaPrivada;
		}
	}

	@Override
	public ArrayList<ReservaSalaPrivadaDTO> getReservasSalaPrivadaByUsuarioDTO(UsuarioDTO usuario) {
		ArrayList<ReservaSalaPrivadaDTO> reservasSalaPrivada = new ArrayList<ReservaSalaPrivadaDTO>();
		
		String insertSQL = "SELECT * FROM ReservaSalaPrivada WHERE dni_cliente=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, usuario.getDni());
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                	ReservaSalaPrivadaDTO reserva = null;
                	//int id, LocalDateTime horaEntrada, LocalDateTime horaSalida, LocalDate fechaReserva,String dniCliente, int idSala
                   int id = rs.getInt("id");
                   String horaEntrada = rs.getString("fecha_entrada");
                   String horaSalida = rs.getString("fecha_salida");
                   String fechaReserva = rs.getString("fecha_reserva");
                   String dniCliente = rs.getString("dni_cliente");
                   int idSala = rs.getInt("id_sala");
                   
                   reserva = new ReservaSalaPrivadaDTO(id, LocalDateTime.parse(horaEntrada), LocalDateTime.parse(horaSalida), LocalDate.parse(fechaReserva), dniCliente, idSala);
                   reservasSalaPrivada.add(reserva);
                }
                return reservasSalaPrivada;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva sala privada: ", e);
			}
			return reservasSalaPrivada;
		}
	}

	@Override
	public boolean deleteReservaSalaPrivadaById(int idReservaSalaPrivada) {
		try {
            String insertSQL = "DELETE FROM ReservaSalaPrivada where id = ?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setInt(1,idReservaSalaPrivada);
           
            int filas = preparedStmt.executeUpdate();

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al eliminar la reserva de la sala privada: ", e);
            return false;
        }
	}

	@Override
	public boolean isSalaPrivadaReservable(ReservaSalaPrivadaDTO reservaSalaPrivada) {
		ArrayList<Integer> salasNoDisponibles = getIdSalasPrivadasNoDisponiblesEntreFechas(reservaSalaPrivada.getIdSala(), reservaSalaPrivada.getfechaEntrada(), reservaSalaPrivada.getfechaSalida());
		if (salasNoDisponibles.contains(reservaSalaPrivada.getIdSala())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateReservaSalaPrivada(ReservaSalaPrivadaDTO reservaSalaPrivada) {
		try {
            String insertSQL = "UPDATE ReservaSalaPrivada SET fecha_entrada=?,fecha_salida=?,fecha_reserva=?,dni_cliente=?,id_sala=? WHERE id=?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1,reservaSalaPrivada.getfechaEntrada().toString());
            preparedStmt.setString(2,reservaSalaPrivada.getfechaSalida().toString());
            preparedStmt.setString(3,reservaSalaPrivada.getFechaReserva().toString());
            preparedStmt.setString(4,reservaSalaPrivada.getDniCliente());
            preparedStmt.setInt(5,reservaSalaPrivada.getIdSala());
            preparedStmt.setInt(6,reservaSalaPrivada.getId());
            
            int filas = preparedStmt.executeUpdate();

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al actualizar la reserva de la sala privada: ", e);
            return false;
        }
	}

	@Override
	public ArrayList<Integer> getIdSalasPrivadasNoDisponiblesEntreFechas(int id, LocalDateTime fechaI, LocalDateTime fechaF) {
		ArrayList<Integer> salasNoDisponibles = new ArrayList<Integer>();
		String insertSQL = "SELECT id_sala FROM ReservaSalaPrivada WHERE fecha_entrada >= ? AND fecha_entrada < ? and id_sala = ?";
		
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, fechaI.toString());
	        preparedStmt.setString(2, fechaF.toString());
	        preparedStmt.setInt(3, id);  

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                    salasNoDisponibles.add(rs.getInt("id_sala"));
                }
            }
	        	        
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al recuperar los Ids de salas privadas: ", e);
            return salasNoDisponibles;
		}
		
		return salasNoDisponibles;
	}
	
	@Override
	public ArrayList<ReservaSalaPrivadaDTO> getReservasSalaPrivadaByIdSala(int idReservaSalaPrivada) {
		ArrayList<ReservaSalaPrivadaDTO> reservasSalaPrivada = new ArrayList<ReservaSalaPrivadaDTO>();
		
		String insertSQL = "SELECT * FROM ReservaSalaPrivada WHERE id_sala = ?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setInt(1, idReservaSalaPrivada);
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                	ReservaSalaPrivadaDTO reserva = null;
                	//int id, LocalDateTime horaEntrada, LocalDateTime horaSalida, LocalDate fechaReserva,String dniCliente, int idSala
                   int id = rs.getInt("id");
                   String horaEntrada = rs.getString("fecha_entrada");
                   String horaSalida = rs.getString("fecha_salida");
                   String fechaReserva = rs.getString("fecha_reserva");
                   String dniCliente = rs.getString("dni_cliente");
                   int idSala = rs.getInt("id_sala");
                   
                   reserva = new ReservaSalaPrivadaDTO(id, LocalDateTime.parse(horaEntrada), LocalDateTime.parse(horaSalida), LocalDate.parse(fechaReserva), dniCliente, idSala);
                   reservasSalaPrivada.add(reserva);
                }
                return reservasSalaPrivada;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva sala privada: ", e);
			}
			return reservasSalaPrivada;
		}
	}

	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = "DELETE FROM ReservaSalaPrivada;";
			
			stmt.executeUpdate(instruccion);
			stmt.close();
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
}
