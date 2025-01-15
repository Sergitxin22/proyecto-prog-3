package dbmejorada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Main;

public class ReservaLibroDAO implements ReservaLibroDAOInterface {

	private Connection conexionBD;
	private Logger logger;

	public ReservaLibroDAO() {
		conexionBD = Main.getConexionBD();
		logger = Main.getLogger();
		pruebas();
	}
	
	public ReservaLibroDAO(Connection conexionBD, Logger logger) {
		this.conexionBD = conexionBD;
		this.logger = logger;
		pruebas();
	}

	@Override
	public boolean addReservaLibro(ReservaLibroDTO reservaLibro) {
		String insertSQLReservaLibro = "INSERT INTO ReservaLibro(id, fecha_inicio, fecha_fin, isbn_libro, dni_cliente) VALUES (null, ?, ?, ?, ?)";

		PreparedStatement preparedStmtReservaLibro;
		try {
			preparedStmtReservaLibro = conexionBD.prepareStatement(insertSQLReservaLibro);
			preparedStmtReservaLibro.setString(1, reservaLibro.getFechaInicio().toString());
			preparedStmtReservaLibro.setString(2, reservaLibro.getFechaFin().toString());
			preparedStmtReservaLibro.setLong(3, reservaLibro.getIsbn());
			preparedStmtReservaLibro.setString(4, reservaLibro.getDniCliente());

			preparedStmtReservaLibro.executeUpdate();
			preparedStmtReservaLibro.close();
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al añadir la reserva del libro: ", e);
			return false;
		}
		return true;
	}

	@Override
	public ReservaLibroDTO getReservaLibroById(int idReservaLibro) {
		ReservaLibroDTO reservaLibro = null;

		String insertSQL = "SELECT * FROM ReservaLibro WHERE id=?;";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setInt(1, idReservaLibro);

			try (ResultSet rs = preparedStmt.executeQuery()) {

				while (rs.next()) {
					int id = rs.getInt("id");
					String fechaInicio = rs.getString("fecha_inicio");
					String fechaFin = rs.getString("fecha_fin");
					Long isbn = rs.getLong("isbn_libro");
					String dniCliente = rs.getString("dni_cliente");

					reservaLibro = new ReservaLibroDTO(id, LocalDateTime.parse(fechaInicio), LocalDateTime.parse(fechaFin), isbn, dniCliente);
				}
				System.out.println("Reserva libro sin fallos");
				preparedStmt.close();
				return reservaLibro;
			}

		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva del libro: ", e);
			}
			return reservaLibro;
		}
	}

	@Override
	public ArrayList<ReservaLibroDTO> getHistorialByDniUsuarioDTO(String dniUsuario) {
		ArrayList<ReservaLibroDTO> reservasLibro = new ArrayList<ReservaLibroDTO>();

		String insertSQL = "SELECT * FROM ReservaLibro WHERE dni_cliente=?;";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, dniUsuario);

			try (ResultSet rs = preparedStmt.executeQuery()) {

				while (rs.next()) {
					ReservaLibroDTO reserva = null;
					// int id, LocalDateTime horaEntrada, LocalDateTime horaSalida, LocalDate
					// fechaReserva,String dniCliente, int idSala
					int id = rs.getInt("id");
					String fechaInicio = rs.getString("fecha_inicio");
					String fechaFin = rs.getString("fecha_fin");
					Long isbn = rs.getLong("isbn_libro");
					String dniCliente = rs.getString("dni_cliente");

					reserva = new ReservaLibroDTO(id, LocalDateTime.parse(fechaInicio), LocalDateTime.parse(fechaFin),
							isbn, dniCliente);
					reservasLibro.add(reserva);
				}
				System.out.println("Reserva libro sin fallos");
				return reservasLibro;
			}

		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar la reserva del libro: ", e);
			}
			return reservasLibro;
		}
	}

	@Override
	public boolean deleteReservaLibroById(int idReservaLibro) {
		try {
			String insertSQL = "DELETE FROM ReservaLibro where id = ?;";
			PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setInt(1, idReservaLibro);

			int filas = preparedStmt.executeUpdate();
			System.out.println("Filas modificadas: " + filas);

			return (filas > 0) ? true : false;
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al eliminar la reserva del libro: ", e);
			return false;
		}
	}

	@Override
	public boolean isLibroDisponible(ReservaLibroDTO reservaLibro) {
		try {
			String insertSQL = "SELECT count(*) AS numero_reservas FROM ReservaLibro WHERE isbn_libro = ? AND ? BETWEEN fecha_inicio AND fecha_fin OR ? BETWEEN fecha_inicio AND fecha_fin; ";
			PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setLong(1, reservaLibro.getIsbn());
			preparedStmt.setString(2, reservaLibro.getFechaInicio().toString());
			preparedStmt.setString(3, reservaLibro.getFechaFin().toString());

			try (ResultSet rs = preparedStmt.executeQuery()) {
				int numeroReservas = 0;
				while (rs.next()) {
					numeroReservas = rs.getInt("numero_reservas");
				}

				if (numeroReservas == 0) { // Libro disponible
					return true;
				}
				System.out.println("Reserva libro sin fallos");
				return false;
			} catch (SQLException e) {
				if (logger != null)
					logger.log(Level.SEVERE, "Error al actualizar la reserva del libro: ", e);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean libroLeidoByUsuarioDni(String dniCliente, Long isbnLibro) {
		try {
			String insertSQL = "SELECT Count(*) AS veces_leido FROM ReservaLibro WHERE dni_cliente = ? AND isbn_libro = ?;";
			PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, dniCliente);
			preparedStmt.setLong(2, isbnLibro);

			try (ResultSet rs = preparedStmt.executeQuery()) {
				int vecesleido = 0;
				while (rs.next()) {
					vecesleido = rs.getInt("veces_leido");
				}

				if (vecesleido == 0) { // El usuario lo puede reservar
					return false;
				}

				System.out.println("Reserva libro sin fallos");
				return true;
			} catch (SQLException e) {
				if (logger != null)
					logger.log(Level.SEVERE, "Error al actualizar la reserva del libro: ", e);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateReservaLibro(ReservaLibroDTO reservaLibro) {
		try {
			String insertSQL = "UPDATE ReservaLibro SET fecha_inicio=?,fecha_fin=?,isbn_libro=?,dni_cliente=? WHERE id=?;";
			PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, reservaLibro.getFechaInicio().toString());
			preparedStmt.setString(2, reservaLibro.getFechaFin().toString());
			preparedStmt.setLong(3, reservaLibro.getIsbn());
			preparedStmt.setString(4, reservaLibro.getDniCliente());
			preparedStmt.setInt(5, reservaLibro.getId());

			int filas = preparedStmt.executeUpdate();
			System.out.println("Filas modificadas: " + filas);

			return (filas > 0) ? true : false;
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al actualizar la reserva del libro: ", e);
			return false;
		}
	}

	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = "DELETE FROM ReservaLibro;";

			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
				logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}

	public void pruebas() {
		ReservaLibroDTO reservaLibro = new ReservaLibroDTO(1, LocalDateTime.now(), LocalDateTime.now(), 9780006480099l,
				"00000000A");
//		addReservaLibro(reservaLibro);
//		System.out.println(getReservaLibroById(1));
//		System.out.println(getHistorialByDniUsuarioDTO("00000000A"));
//		System.out.println(deleteReservaLibroById(9));
//		System.out.println(isLibroDisponible(reservaLibro)); 
//		System.out.println(libroLeidoByUsuarioDni("11111111B", 9780006480099l));
//		reservaLibro.setId(6);
//		reservaLibro.setIsbn(9780002261982l);
//		reservaLibro.setDniCliente("11111111B");
//		System.out.println(updateReservaLibro(reservaLibro));
//		borrarRegistros();
		
//		boolean addReservaLibro(ReservaLibroDTO reserva); ✅
//		ReservaLibroDTO getReservaLibroById(int idReservaSala); ✅
//		ArrayList<ReservaLibroDTO> getHistorialByDniUsuarioDTO(String dniUsuario); ✅
//		boolean deleteReservaLibroById(int idReservaSala); ✅
//		boolean isLibroDisponible(ReservaLibroDTO reserva); ✅
//		boolean libroLeidoByUsuarioDni(String dniCliente, Long isbnLibro); ✅
//		boolean updateReservaLibro(ReservaLibroDTO reserva); ✅
//		void borrarRegistros(); ✅
	}

}
