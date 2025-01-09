package dbmejorada;

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

import domain.Reserva;
import main.Main;

public class ReservaSalaPublicaDAO implements ReservaSalaPublicaDAOInterface {

	private Connection conexionBD;
    private Logger logger;

    public ReservaSalaPublicaDAO() {
       	conexionBD = Main.getConexionBD();
      	logger = Main.getLogger();
        pruebas();
    }

	@Override
	public boolean addReservaSalaPublica(ReservaSalaPublicaDTO reservaSalaPublica) {
		String insertSQLReservaSalaPublica = "INSERT INTO ReservaSalaPublica(fecha_entrada, dni_cliente, numero_bloque) VALUES (?, ?, ?)";

    	PreparedStatement preparedStmtReservaSalaPublica;
		try {
			preparedStmtReservaSalaPublica = conexionBD.prepareStatement(insertSQLReservaSalaPublica);
			preparedStmtReservaSalaPublica.setString(1, reservaSalaPublica.getFechaEntrada().toString());
			preparedStmtReservaSalaPublica.setString(2, reservaSalaPublica.getDniCliente());
			preparedStmtReservaSalaPublica.setInt(3, reservaSalaPublica.getNumeroBloque());
	    		
			preparedStmtReservaSalaPublica.executeUpdate();
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la reserva de sala publica: ", e);
            return false;
		}
		return true;
	}

	@Override
	public ReservaSalaPublicaDTO getReservaSalaPublicaById(int idReservaSalaPublica) {
		ReservaSalaPublicaDTO reservaSalaPublica = null;
		
		String insertSQL = "SELECT * FROM ReservaSalaPublica WHERE id=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setInt(1, idReservaSalaPublica);
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                   int id = rs.getInt("id");
                   String fechaEntrada = rs.getString("fecha_entrada");
                   String dniCliente = rs.getString("dni_cliente");
                   int numeroBloque = rs.getInt("numero_bloque");
                   
                   reservaSalaPublica = new ReservaSalaPublicaDTO(id, LocalDateTime.parse(fechaEntrada), dniCliente, numeroBloque);
                }
                System.out.println("Reserva sala publica sin fallos");
                return reservaSalaPublica;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva de sala publica: ", e);
			}
			return reservaSalaPublica;
		}
	}

	@Override
	public ArrayList<ReservaSalaPublicaDTO> getReservasSalaPublicaByUsuarioDTO(UsuarioDTO usuario) {
		ArrayList<ReservaSalaPublicaDTO> reservasSalaPublica = new ArrayList<ReservaSalaPublicaDTO>();
		
		String insertSQL = "SELECT * FROM ReservaSalaPublica WHERE dni_cliente=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, usuario.getDni());
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                	ReservaSalaPublicaDTO reserva = null;
                	 int id = rs.getInt("id");
                     String fechaEntrada = rs.getString("fecha_entrada");
                     String dniCliente = rs.getString("dni_cliente");
                     int numeroBloque = rs.getInt("numero_bloque");
                   
                   reserva = new ReservaSalaPublicaDTO(id, LocalDateTime.parse(fechaEntrada), dniCliente, numeroBloque);
                   reservasSalaPublica.add(reserva);
                }
                System.out.println("Reserva sala publica sin fallos");
                return reservasSalaPublica;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva de sala publica: ", e);
			}
			return reservasSalaPublica;
		}
	}

	@Override
	public boolean deleteReservaSalaPublicaById(int idReservaSalaPublica) {
		try {
            String insertSQL = "DELETE FROM ReservaSalaPublica where id = ?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setInt(1,idReservaSalaPublica);
           
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al eliminar la reserva de la sala publica: ", e);
            return false;
        }
	}

	@Override
	public boolean isSalaPublicaReservable(ReservaSalaPublicaDTO reservaSalaPublica) {
		String insertSQL = "SELECT 250 - count(*) As asientos_disponibles from ReservaSalaPublica WHERE fecha_entrada = ?;";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, reservaSalaPublica.getFechaEntrada().toString());
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
	        	int asientosDisponibles = 0;
                while (rs.next()) {
                	asientosDisponibles = rs.getInt("asientos_disponibles");
                }
                System.out.println("Reserva sala publica sin fallos");
                if (asientosDisponibles > 0) {
                	return true;
				}
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva de sala publica: ", e);
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean updateReservaSalaPublica(ReservaSalaPublicaDTO reservaSalaPublica) {
		try {
            String insertSQL = "UPDATE ReservaSalaPublica SET fecha_entrada=?, dni_cliente=?, numero_bloque=? WHERE id=?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1,reservaSalaPublica.getFechaEntrada().toString());
            preparedStmt.setString(2,reservaSalaPublica.getDniCliente());
            preparedStmt.setInt(3,reservaSalaPublica.getNumeroBloque());
            preparedStmt.setInt(4,reservaSalaPublica.getId());
            
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al actualizar la reserva de la sala publica: ", e);
            return false;
        }
	}

	@Override
	public ArrayList<Integer> getAsientosDisponibles(LocalDate fecha) {
		ArrayList<Integer> asientosDisponibles = new ArrayList<Integer>();
		for (int i = 1; i < 251; i++) {
			asientosDisponibles.add(i);
		}
		
		String insertSQL = "SELECT numero_bloque from ReservaSalaPublica WHERE fecha_entrada = ?;"; 
		
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, fecha.toString());

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                    int numeroBloque = rs.getInt("numero_bloque");
                    
                    asientosDisponibles.remove(Integer.valueOf(numeroBloque));
                }
                System.out.println("Ids de salas recuperados correctamente");
            }
	        	        
		} catch (SQLException e) {
			if (logger != null)
				System.out.println(insertSQL);
                logger.log(Level.SEVERE, "Error al recuperar los Ids de salas: ", e);
            return asientosDisponibles;
		}
		return asientosDisponibles;
	}
	
	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = "DELETE FROM ReservaSalaPublica;";
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
	
	public void pruebas() {
		ReservaSalaPublicaDTO reservaSalaPublica = new ReservaSalaPublicaDTO(1, LocalDateTime.parse("2025-02-02T00:00:00"), "00000000A", 45);
//		addReservaSalaPublica(reservaSalaPublica);
//		System.out.println(getReservaSalaPublicaById(2));
//		UsuarioDTO usuario = new UsuarioDTO();
//		usuario.setDni("00000000A");
//		System.out.println(getReservasSalaPublicaByUsuarioDTO(usuario));
//		System.out.println(deleteReservaSalaPublicaById(3));
//		System.out.println(isSalaPublicaReservable(reservaSalaPublica));
//		System.out.println(updateReservaSalaPublica(reservaSalaPublica));
//		System.out.println(getAsientosDisponibles(LocalDate.parse("2025-02-02")));
	}

}
