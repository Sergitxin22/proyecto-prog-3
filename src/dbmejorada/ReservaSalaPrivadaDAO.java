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

import main.Main;

public class ReservaSalaPrivadaDAO implements ReservaSalaPrivadaDAOInterface {

	private Connection conexionBD;
    private Logger logger;

    public ReservaSalaPrivadaDAO() {
       	conexionBD = Main.getConexionBD();
      	logger = Main.getLogger();
        pruebas();
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
                System.out.println("Reserva sala privada sin fallos");
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
                System.out.println("Reserva sala privada sin fallos");
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
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al eliminar la reserva de la sala privada: ", e);
            return false;
        }
	}

	@Override
	public boolean isSalaPrivadaReservable(ReservaSalaPrivadaDTO reservaSalaPrivada) {
		ArrayList<Integer> salasDisponibles = getIdSalasPrivadasDisponiblesEntreFechas(reservaSalaPrivada.getfechaEntrada(), reservaSalaPrivada.getfechaSalida());
		if (salasDisponibles.contains(reservaSalaPrivada.getIdSala())) {
			return true;
		}
		return false;
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
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al actualizar la reserva de la sala privada: ", e);
            return false;
        }
	}

	@Override
	public ArrayList<Integer> getIdSalasPrivadasDisponiblesEntreFechas(LocalDateTime fechaI, LocalDateTime fechaF) {
		ArrayList<Integer> salasDisponibles = new ArrayList<Integer>();
		int idTipoSala = Main.getSalaDAO().getTipoSalaId("PRIVADA");
		String insertSQL = "SELECT s.*"
				+ " FROM Sala s"
				+ " LEFT JOIN ReservaSalaPrivada r"
				+ " ON s.id = r.id_sala"
				+ " AND r.fecha_entrada <= ?" // Fecha de Fin
				+ " AND r.fecha_salida >= ?"  // Fecha de Inicio
				+ " WHERE r.id IS NULL"
				+ " AND s.tipo = ?;"; // TODO: necesito obtener el id del SalaDAO
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, fechaF.toString());
	        preparedStmt.setString(2, fechaI.toString());
	        preparedStmt.setInt(3, idTipoSala);

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                    int idSala = rs.getInt("id");
                    
                    salasDisponibles.add(idSala);
                }
                System.out.println("Ids de salas privadas recuperados correctamente");
            }
	        	        
		} catch (SQLException e) {
			if (logger != null)
				System.out.println(insertSQL);
                logger.log(Level.SEVERE, "Error al recuperar los Ids de salas privadas: ", e);
            return salasDisponibles;
		}
		
		return salasDisponibles;
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
                System.out.println("Reserva sala privada sin fallos");
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
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
	
	public void pruebas() {
		LocalDateTime fecha = LocalDateTime.now();
		ReservaSalaPrivadaDTO reservaSalaPrivada = new ReservaSalaPrivadaDTO(1, fecha, fecha, LocalDate.now(), "00000000A", 54);
//		addReservaSalaPrivada(reservaSalaPrivada);
//		System.out.println(getReservaSalaPrivadaById(29));
//		UsuarioDTO usuario = new UsuarioDTO();
//		usuario.setDni("00000000A");
//		System.out.println(getReservasSalaPrivadaByUsuarioDTO(usuario));
//		System.out.println(deleteReservaSalaPrivadaById(28));
//		System.out.println(isSalaPrivadaReservable(reservaSalaPrivada));
//		System.out.println(updateReservaSalaPrivada(reservaSalaPrivada));
//		System.out.println(getReservasSalaPrivadaByIdSala(3));
//		System.out.println(getIdSalasPrivadasDisponiblesEntreFechas(fecha, fecha));	
//		borrarRegistros();
		
//		boolean addReservaSalaPrivada(ReservaSalaPrivadaDTO reserva);  ✅
//		ReservaSalaPrivadaDTO getReservaSalaPrivadaById(int idReservaSala);  ✅
//		ArrayList<ReservaSalaPrivadaDTO> getReservasSalaPrivadaByUsuarioDTO(UsuarioDTO usuario);  ✅
//		boolean deleteReservaSalaPrivadaById(int idReservaSala);  ✅
//		boolean isSalaPrivadaReservable(ReservaSalaPrivadaDTO reserva);  ✅
//		boolean updateReservaSalaPrivada(ReservaSalaPrivadaDTO reserva);  ✅
//		ArrayList<ReservaSalaPrivadaDTO> getReservasSalaPrivadaByIdSala(int idSala); ✅
//		ArrayList<Integer> getIdSalasPrivadasDisponiblesEntreFechas(LocalDateTime fechaI, LocalDateTime fechaF); ✅
//		void borrarRegistros(); ✅
	}

}
