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

public class ReservaSalaDAO implements ReservaSalaDAOInterface {

	private Connection conexionBD;
    private Logger logger;

    public ReservaSalaDAO() {
       	conexionBD = Main.getConexionBD();
      	logger = Main.getLogger();
        pruebas();
    }
	
	// nuevos
	@Override
	public boolean addReservaSala(ReservaSalaDTO reserva) {
		String insertSQLReservaSalaPrivada = "INSERT INTO ReservaSala(fecha_entrada, fecha_salida, fecha_reserva, dni_cliente, id_sala) VALUES (?, ?, ?, ?, ?)";

    	PreparedStatement preparedStmtReservaSalaPrivada;
		try {
			preparedStmtReservaSalaPrivada = conexionBD.prepareStatement(insertSQLReservaSalaPrivada);
			preparedStmtReservaSalaPrivada.setString(1, reserva.getHoraEntrada().toString());
	   		preparedStmtReservaSalaPrivada.setString(2, reserva.getHoraSalida().toString());
	   		preparedStmtReservaSalaPrivada.setString(3, reserva.getFechaReserva().toString());
	    	preparedStmtReservaSalaPrivada.setString(4, reserva.getDniCliente());
	    	preparedStmtReservaSalaPrivada.setInt(5, reserva.getIdSala());
	    		
	    	preparedStmtReservaSalaPrivada.executeUpdate();
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la reserva: ", e);
            return false;
		}
		
		return true;
	}
    

	@Override
	public ReservaSalaDTO getReservaSalaById(int idReservaSala) {
		ReservaSalaDTO reservaSala = null;
		
		String insertSQL = "SELECT * FROM ReservaSala WHERE id=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setInt(1, idReservaSala);
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                	//int id, LocalDateTime horaEntrada, LocalDateTime horaSalida, LocalDate fechaReserva,String dniCliente, int idSala
                   int id = rs.getInt("id");
                   String horaEntrada = rs.getString("fecha_entrada");
                   String horaSalida = rs.getString("fecha_salida");
                   String fechaReserva = rs.getString("fecha_reserva");
                   String dniCliente = rs.getString("dni_cliente");
                   int idSala = rs.getInt("id_sala");
                   
                   reservaSala = new ReservaSalaDTO(id, LocalDateTime.parse(horaEntrada), LocalDateTime.parse(horaSalida), LocalDate.parse(fechaReserva), dniCliente, idSala);
                }
                System.out.println("Reserva sala sin fallos");
                return reservaSala;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva sala: ", e);
			}
			return reservaSala;
		}
	}

	@Override
	public ArrayList<ReservaSalaDTO> getReservasSalaByUsuarioDTO(UsuarioDTO usuario) {
		//SELECT * FROM ReservaSala where dni_cliente = '00000000A';
		ArrayList<ReservaSalaDTO> reservasSala = new ArrayList<ReservaSalaDTO>();
		
		String insertSQL = "SELECT * FROM ReservaSala WHERE dni_cliente=?;";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, usuario.getDni());
			
	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                	ReservaSalaDTO reserva = null;
                	//int id, LocalDateTime horaEntrada, LocalDateTime horaSalida, LocalDate fechaReserva,String dniCliente, int idSala
                   int id = rs.getInt("id");
                   String horaEntrada = rs.getString("fecha_entrada");
                   String horaSalida = rs.getString("fecha_salida");
                   String fechaReserva = rs.getString("fecha_reserva");
                   String dniCliente = rs.getString("dni_cliente");
                   int idSala = rs.getInt("id_sala");
                   
                   reserva = new ReservaSalaDTO(id, LocalDateTime.parse(horaEntrada), LocalDateTime.parse(horaSalida), LocalDate.parse(fechaReserva), dniCliente, idSala);
                   reservasSala.add(reserva);
                }
                System.out.println("Reserva sala sin fallos");
                return reservasSala;
            }
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva sala: ", e);
			}
			return reservasSala;
		}
	}

	@Override
	public boolean deleteReservaSalaById(int idSala) {
		// DELETE FROM ReservaSala where id = 1;
		
		return false;
	}

	@Override
	public boolean isSalaReservable(ReservaSalaDTO reserva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReservaSala(ReservaSalaDTO reserva) {
		try {
            String insertSQL = "UPDATE ReservaSala SET fecha_entrada=?,fecha_salida=?,fecha_reserva=?,dni_cliente=?,id_sala=? WHERE id=?;";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1,reserva.getHoraEntrada().toString());
            preparedStmt.setString(2,reserva.getHoraSalida().toString());
            preparedStmt.setString(3,reserva.getFechaReserva().toString());
            preparedStmt.setString(4,reserva.getDniCliente());
            preparedStmt.setInt(5,reserva.getIdSala());
            preparedStmt.setInt(6,reserva.getId());
            
            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al actualizar la reserva de la sala: ", e);
            return false;
        }
	}

	@Override
	public ArrayList<Integer> getIdSalasDisponiblesEntreFechas(String fechaI, String fechaF) {
		ArrayList<Integer> salasDisponibles = new ArrayList<Integer>();
		
		String insertSQL = "SELECT s.*"
				+ " FROM Sala s"
				+ " LEFT JOIN ReservaSala r"
				+ " ON s.id = r.id_sala"
				+ " AND r.fecha_entrada <= ?" // Fecha de Fin
				+ " AND r.fecha_salida >= ?"  // Fecha de Inicio
				+ " WHERE r.id IS NULL"
				+ " AND s.tipo = 59;"; // TODO: necesito obtener el id del SalaDAO
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, fechaF);
	        preparedStmt.setString(2, fechaI);

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                    int idSala = rs.getInt("id");
                    
                    salasDisponibles.add(idSala);
                }
                System.out.println("Ids de salas recuperados correctamente");
            }
	        	        
		} catch (SQLException e) {
			if (logger != null)
				System.out.println(insertSQL);
                logger.log(Level.SEVERE, "Error al recuperar los Ids de salas: ", e);
            return salasDisponibles;
		}
		
		return salasDisponibles;
	}

	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = "DELETE FROM ReservaSala;";
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
	
	public void pruebas() {
//		System.out.println("****Borrar registros****");
//    	//borrarRegistros();
//    	
//        Cliente u = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDateTime.now(), "hola", new ArrayList<>(),
//              new ArrayList<>(), 3);
//        Admin a = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDateTime.now(), "aroa2003", new ArrayList<>());
//        
//        System.out.println("****Añadir usuarios****");
//        //addUsuario(u);
//        //addUsuario(a);
//        
//        System.out.println("****Recuperar usuarios****");
//        UsuarioDTO uDTO = getUsuario("00000000A", "hola");
//        System.out.println(uDTO);
//        System.out.println(getUsuario("11111111B", "aroa2003"));
//
//        System.out.println("****Recuperar usuario incorrecto****");
//        System.out.println(getUsuario("00000000A", "holas"));
//        
//        System.out.println("****Cambiar contraseña y recuperar usuario con la contraseña anterior****");
//        updatePassword(uDTO, "contraseña cambiada");
//        uDTO = getUsuario("00000000A", "hola");
//        System.out.println(uDTO);
//        
//        System.out.println("****Recuperar usuario con la contraseña nueva****");
//        uDTO = getUsuario("00000000A", "contraseña cambiada");
//        System.out.println(uDTO);
	
//		ArrayList<Integer> salas = getIdSalasDisponiblesEntreFechas("2024-12-05T21:48:00.492987900", "2024-12-08T21:48:00.492987900");
//		System.out.println(salas);
		
//		ReservaSalaDTO reserva = getReservaSalaById(1);
//		System.out.println(reserva);
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDni("00000000A");
		ArrayList<ReservaSalaDTO> reservaUsuario = getReservasSalaByUsuarioDTO(usuario);
		System.out.println(reservaUsuario);
		
		usuario.setDni("00000001A");
		ArrayList<ReservaSalaDTO> reservaUsuario1 = getReservasSalaByUsuarioDTO(usuario);
		System.out.println(reservaUsuario1);
	}
}
