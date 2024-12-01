package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import BiblioTech.Cliente;
import BiblioTech.Libro;
import BiblioTech.Reserva;
import BiblioTech.Review;
import BiblioTech.Sala;
import BiblioTech.SalaPrivada;
import BiblioTech.SalaPublica;
import BiblioTech.Usuario;

public class GestorDB {

    private Connection conexionBD = null;
    private Logger logger = null;

    public Connection init(String nombreBD) {
        nombreBD = "resources/db/" + nombreBD;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD + ".db");
            logger = Logger.getLogger("GestorPersistencia-" + nombreBD);
            conexionBD = con;
            return con;
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            conexionBD = null;
            if (logger != null)
                logger.log(Level.SEVERE, "Error en conexión de base de datos " + nombreBD + ".db", e);
            return null;
        }
    }

    public boolean existeBD(String nombreBD) {
        nombreBD = "resources/db/" + nombreBD;
        File fichero = new File(nombreBD + ".db");
        return fichero.exists() && fichero.length() > 0;
    }

    public Connection getConnection() {
        return conexionBD;
    }

    public boolean addUsuario(Usuario usuario) {
        try {
            String insertSQL = "INSERT INTO Usuario VALUES (?,?,?,?,?)";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1, usuario.getDni());
            preparedStmt.setString(2, usuario.getNombre());
            preparedStmt.setString(3, usuario.getEmail());
            preparedStmt.setString(4, usuario.getFechaCreacion().toString());
            preparedStmt.setString(5, usuario.getContrasena());

            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            PreparedStatement preparedStmt2 = null;
            if (usuario instanceof Cliente) {
                String insertSQL2 = "INSERT INTO Cliente VALUES (?,?)";
                preparedStmt2 = conexionBD.prepareStatement(insertSQL2);
                preparedStmt2.setString(1, usuario.getDni());
                preparedStmt2.setInt(2, ((Cliente) usuario).getAmonestaciones());
            } else {
                String insertSQL2 = "INSERT INTO Admin VALUES (?)";
                preparedStmt2 = conexionBD.prepareStatement(insertSQL2);
                preparedStmt2.setString(1, usuario.getDni());
            }

            int filas2 = preparedStmt2.executeUpdate();

            preparedStmt.close();
            preparedStmt2.close();
            return (filas > 0 && filas2 > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error en añadido de usuario: ", e);
            return false;
        }
    }
    
    public boolean addLibro(Libro libro) {
    	try {
    		 String insertSQL = "INSERT INTO Libro VALUES (?,?,?,?,?,?,?,?)";
             PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
             preparedStmt.setLong(1, libro.getIsbn());
             preparedStmt.setString(2, libro.getTitulo());
             preparedStmt.setString(3, libro.getAutor());
             preparedStmt.setInt(4, libro.getNumeroDePaginas());
             preparedStmt.setString(5, libro.getSinopsis());
             preparedStmt.setString(6, libro.getGenero());
             preparedStmt.setInt(7, libro.getRating());
             preparedStmt.setInt(8, libro.getFechaPublicacion());

             int filas = preparedStmt.executeUpdate();
             System.out.println("Filas modificadas: " + filas);
             return (filas > 0) ? true : false;
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el libro: ", e);
		}
    	return false;
    }
    
    public void addTipoEvento() {
    	try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = (
					"INSERT INTO TipoEvento (descripcion) VALUES ('CHARLA');"
					+ "INSERT INTO TipoEvento (descripcion) VALUES ('DEBATE');"
					+ "INSERT INTO TipoEvento (descripcion) VALUES ('SEMINARIO');"
					+ "INSERT INTO TipoEvento (descripcion) VALUES ('CURSILLO');"
					+ "INSERT INTO TipoEvento (descripcion) VALUES ('TALLER');"
					+ "INSERT INTO TipoEvento (descripcion) VALUES ('CONFERENCIA');"
			);
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el tipoEvento: ", e);
		}
	 }
    
    public void addTipoSala() {
    	try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = (
					"INSERT INTO TipoSala (tipo) VALUES ('PUBLICA');"
					+ "INSERT INTO TipoSala (tipo) VALUES ('PRIVADA');"
					+ "INSERT INTO TipoSala (tipo) VALUES ('EVENTOS');");
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el tipoSala: ", e);
		}
	 }
    
    public void Recurso() {
    	try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = (
					"INSERT INTO Recurso (descripcion) VALUES ('ORDENADORES');"
					+ "INSERT INTO Recurso (descripcion) VALUES ('PROYECTOR');"
					+ "INSERT INTO Recurso (descripcion) VALUES ('PIZARRA');");
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el recurso: ", e);
		}
	 }
    
    public boolean addSala(Sala sala) {
        String tipoSala = "";
        if (sala instanceof SalaPublica) {
			tipoSala = "PUBLICA";
		} else if (sala instanceof SalaPrivada) {
			tipoSala = "PRIVADA";
		} else {
			tipoSala = "EVENTOS";
		}
        int idTipoSala = getIdTipoSala(tipoSala);
    	try {
    		String insertSQL = "INSERT INTO Sala VALUES (null,?,?,?)";
           	PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
           	preparedStmt.setInt(1, sala.getPiso());
           	preparedStmt.setInt(2, sala.getCapacidad());
           	preparedStmt.setInt(3, idTipoSala);

            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);
            return (filas > 0) ? true : false;
            
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la sala: ", e);
		}
    	return false;
    }
    
    public int getIdTipoSala(String tipoSala) {
    	try {
    		String insertSQL = "select id from TipoSala where tipo = ?;";
			PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, tipoSala);
			
			ResultSet rs = preparedStmt.executeQuery();
			int id = rs.getInt("id");
			
			return id;
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el tipoSala: ", e);
		}
    	return 0;
	 }
    
//    public boolean addReservaLibro(Reserva libro) {
//    	try {
//    		 String insertSQL = "INSERT INTO Libro VALUES (?,?,?,?,?,?,?,?)";
//             PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
//             preparedStmt.setLong(1, libro.getIsbn());
//             preparedStmt.setString(2, libro.getTitulo());
//             preparedStmt.setString(3, libro.getAutor());
//             preparedStmt.setInt(4, libro.getNumeroDePaginas());
//             preparedStmt.setString(5, libro.getSinopsis());
//             preparedStmt.setString(6, libro.getGenero());
//             preparedStmt.setInt(7, libro.getRating());
//             preparedStmt.setInt(8, libro.getFechaPublicacion());
//
//             int filas = preparedStmt.executeUpdate();
//             System.out.println("Filas modificadas: " + filas);
//             return (filas > 0) ? true : false;
//		} catch (SQLException e) {
//			if (logger != null)
//                logger.log(Level.SEVERE, "Error al añadir el libro: ", e);
//		}
//    	return false;
//    }
    
    public boolean addReview(Review review) {
    	try {
    		 String insertSQL = "INSERT INTO Review VALUES (null,?,?,?,?)";
             PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
             preparedStmt.setString(1, review.getComentario());
             preparedStmt.setInt(2, review.getRating());
             preparedStmt.setLong(3, review.getLibro().getIsbn());
             preparedStmt.setString(4, review.getCliente().getDni());

             int filas = preparedStmt.executeUpdate();
             System.out.println("Filas modificadas: " + filas);
             return (filas > 0) ? true : false;
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la review: ", e);
		}
    	return false;
    }
    
    public boolean addReservaSala(Reserva reservaSala) {
    	try {
    		String insertSQL = "INSERT INTO ReservaSala VALUES (null,?,?,?,?,?)";
           	PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
           	preparedStmt.setString(1, reservaSala.getHoraEntrada().toString());
           	preparedStmt.setString(2, reservaSala.getHoraSalida().toString());
           	preparedStmt.setString(3, reservaSala.getFechaReserva().toString());
           	preparedStmt.setString(4, reservaSala.getClientesReserva().get(0).getDni()); //TODO iterar los clientes
           	preparedStmt.setInt(5, reservaSala.getSala().getId());

            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);
            return (filas > 0) ? true : false;
            
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir la sala: ", e);
		}
    	return false;
    }
    
    public void deleteDatosTablas() {
    	try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = ("DELETE FROM Usuario;"
					+ "DELETE FROM Libro;"
					+ "DELETE FROM TipoEvento;"
					+ "DELETE FROM TipoSala;"
					+ "DELETE FROM Recurso;"
					+ "DELETE FROM Sala;");
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el libro: ", e);
		}
	 }
    
  }
