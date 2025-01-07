package main;

import db.GestorDB;
import dbmejorada.EventoDAO;
import dbmejorada.EventoDAOInterface;
import dbmejorada.LibroDAO;
import dbmejorada.LibroDAOInterface;
import dbmejorada.ReservaSalaPrivadaDAO;
import dbmejorada.ReservaSalaPrivadaDAOInterface;
import dbmejorada.ReviewDAO;
import dbmejorada.ReviewDAOInterface;
import dbmejorada.SalaDAO;
import dbmejorada.SalaDAOInterface;
import dbmejorada.UsuarioDAO;
import dbmejorada.UsuarioDAOInterface;
import dbmejorada.UsuarioDTO;
import domain.Admin;
import domain.Cliente;
import domain.Libro;
import domain.Recurso;
import domain.Reserva;
import domain.Review;
import domain.SalaPrivada;
import domain.SalaPublica;
import domain.Usuario;
import gui.VentanaPortada;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private final static String PROPERTIES_FILE = "src/dbmejorada/app.properties";
	
	private static Usuario usuario;
	private static SalaPublica salaPublica;
	private static Connection conexionBD;
	private static Logger logger;
	private static Libro libro;
	
	//	Declaración de los DAO
	private static UsuarioDAOInterface usuarioDAO;
	private static SalaDAOInterface salaDAO;
	private static ReservaSalaPrivadaDAOInterface reservaSalaPrivadaDAO;
	private static ReviewDAOInterface reviewDAO;
	private static LibroDAOInterface libroDAO;
	private static EventoDAOInterface eventoDAO;

	public static UsuarioDAOInterface getUsuarioDAO() {
		return usuarioDAO;
	}

	public static void setUsuarioDAO(UsuarioDAOInterface usuarioDAO) {
		Main.usuarioDAO = usuarioDAO;
	}
	
	public static SalaDAOInterface getSalaDAO() {
		return salaDAO;
	}

	public static void setSalaDAO(SalaDAOInterface salaDAO) {
		Main.salaDAO = salaDAO;
	}

	public static ReservaSalaPrivadaDAOInterface getReservaSalaPrivadaDAO() {
		return reservaSalaPrivadaDAO;
	}

	public static void setReservaSalaPrivadaDAO(ReservaSalaPrivadaDAOInterface reservaSalaPrivadaDAO) {
		Main.reservaSalaPrivadaDAO = reservaSalaPrivadaDAO;
	}
	
	public static ReviewDAOInterface getReviewDAO() {
		return reviewDAO;
	}

	public static void setReviewDAO(ReviewDAOInterface reviewDAO) {
		Main.reviewDAO = reviewDAO;
	}

	public static void setSalaPublica(SalaPublica salaPublica) {
		Main.salaPublica = salaPublica;
	}
	
    public static SalaPublica getSalaPublica() {
    	return salaPublica;
    }
    
    public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		Main.usuario = usuario;
	}
	
	public static LibroDAOInterface getLibroDAO() {
		return libroDAO;
	}
	
	public static void setLibroDAO(LibroDAOInterface libroDAO) {
		Main.libroDAO = libroDAO;
	}
	
	public static EventoDAOInterface getEventoDAO() {
		return eventoDAO;
	}

	public static void setEventoDAO(EventoDAOInterface eventoDAO) {
		Main.eventoDAO = eventoDAO;
	}

	public static Connection getConexionBD() {
		return conexionBD;
	}

	public static void setConexionBD(Connection conexionBD) {
		Main.conexionBD = conexionBD;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Main.logger = logger;
	}

    public static void main(String[] args) {
    	
    	usuario = null;
    	salaPublica = new SalaPublica(250, 0, 1);
    	
    	Properties properties = new Properties();
		try {
			properties.load(new FileReader(PROPERTIES_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String driver = properties.getProperty("driver");
		String connection = properties.getProperty("connection");
		String nombreBD = properties.getProperty("dbName");
    	

    	// Comprobación del .jar e inicialización de Conexión y Logger
        try {
            Class.forName(driver);
            conexionBD = DriverManager.getConnection(connection);
            logger = Logger.getLogger("GestorPersistencia-" + nombreBD);
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            conexionBD = null;
            if (logger != null)
                logger.log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }
    	
        // Inicialización de DAOs
        usuarioDAO = new UsuarioDAO();
        salaDAO = new SalaDAO();
        reservaSalaPrivadaDAO = new ReservaSalaPrivadaDAO();
        reviewDAO = new ReviewDAO();
        libroDAO = new LibroDAO();
        eventoDAO = new EventoDAO();
        
        SalaPrivada sala = new SalaPrivada(5, 12, 2, null, null);
        
        ArrayList<Recurso> recursos = new ArrayList<>();
        ArrayList<Reserva> reservas = new ArrayList<>();
        
        recursos.add(Recurso.ORDENADORES);
        recursos.add(Recurso.PROYECTOR);
        reservas.add(new Reserva(sala, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), new Cliente("7a", "Markel", "markel", LocalDateTime.now(), "hola", new ArrayList<>(), new ArrayList<>(), 2)));
        
        sala.setRecursos(recursos);
        sala.setReservas(reservas);
        
//        salaDAO.addSala(sala);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        
        usuarioDTO.setAdmin(false);
        usuarioDTO.setAmonestaciones(2);
        usuarioDTO.setDni("aaaaa");
        usuarioDTO.setNombre("Ander");
        
        Review review = new Review(new Libro(129, "Libro 2", "Yo",  12, "Sinopsis", "Fantasia",  5, 2003, null, new ArrayList<>()), usuarioDTO, "Comentarioo", 5);
//    	reviewDAO.addReview(review);
        
        
    	new VentanaPortada();
    }
}
