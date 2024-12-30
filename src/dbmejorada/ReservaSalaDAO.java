package dbmejorada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public boolean addReservaSala(Reserva reserva) {
		String insertSQLReservaSalaPrivada = "INSERT INTO ReservaSala(fecha_entrada, fecha_salida, fecha_reserva, dni_cliente, id_sala) VALUES (?, ?, ?, ?, ?)";

    	PreparedStatement preparedStmtReservaSalaPrivada;
		try {
			preparedStmtReservaSalaPrivada = conexionBD.prepareStatement(insertSQLReservaSalaPrivada);
			preparedStmtReservaSalaPrivada.setString(1, reserva.getHoraEntrada().toString());
	   		preparedStmtReservaSalaPrivada.setString(2, reserva.getHoraSalida().toString());
	   		preparedStmtReservaSalaPrivada.setString(3, reserva.getFechaReserva().toString());
	    	preparedStmtReservaSalaPrivada.setString(4, reserva.getClienteReserva().getDni());
	    	preparedStmtReservaSalaPrivada.setInt(5, reserva.getSala().getId());
	    		
	    	preparedStmtReservaSalaPrivada.executeUpdate();
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la reserva: ", e);
            return false;
		}
		
		return true;
	}
    

	@Override
	public Reserva getReservaSalaById(int idSala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva getReservaSalaByUsuarioDTO(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReservaSalaById(int idSala) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSalaReservable(Reserva reserva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReservaSala(Reserva reserva) {
		// TODO Auto-generated method stub
		return false;
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
	
		ArrayList<Integer> salas = getIdSalasDisponiblesEntreFechas("2024-12-05T21:48:00.492987900", "2024-12-08T21:48:00.492987900");
		System.out.println(salas);
	}
}
