package main;

import domain.SalaPublica;
import domain.Usuario;

import gui.VentanaPortada;

import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import db.AsistenciaEventoDAO;
import db.AsistenciaEventoDAOInterface;
import db.EventoDAO;
import db.EventoDAOInterface;
import db.LibroDAO;
import db.LibroDAOInterface;
import db.ReservaLibroDAO;
import db.ReservaLibroDAOInterface;
import db.ReservaSalaPrivadaDAO;
import db.ReservaSalaPrivadaDAOInterface;
import db.ReservaSalaPublicaDAO;
import db.ReservaSalaPublicaDAOInterface;
import db.ReviewDAO;
import db.ReviewDAOInterface;
import db.SalaDAO;
import db.SalaDAOInterface;
import db.UsuarioDAO;
import db.UsuarioDAOInterface;

public class Main {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private static String driver;
	private static String connection;
	private static String nombreBD;
	
	private static Usuario usuario;
	private static SalaPublica salaPublica;
	
	private static Connection conexionBD;
	private static Logger logger;
	
	private static ArrayList<Thread> threads;
	
	private static UsuarioDAOInterface usuarioDAO;
	private static SalaDAOInterface salaDAO;
	private static ReservaSalaPrivadaDAOInterface reservaSalaPrivadaDAO;
	private static ReservaSalaPublicaDAOInterface reservaSalaPublicaDAO;
	private static ReviewDAOInterface reviewDAO;
	private static LibroDAOInterface libroDAO;
	private static ReservaLibroDAOInterface reservaLibroDAO;
	private static EventoDAOInterface eventoDAO;
	private static AsistenciaEventoDAOInterface asistenciaEventoDAO;
	
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
	
	public static ReservaSalaPublicaDAOInterface getReservaSalaPublicaDAO() {
		return reservaSalaPublicaDAO;
	}

	public static void setReservaSalaPublicaDAO(ReservaSalaPublicaDAOInterface reservaSalaPublicaDAO) {
		Main.reservaSalaPublicaDAO = reservaSalaPublicaDAO;
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
	
	public static ReservaLibroDAOInterface getReservaLibroDAO() {
		return reservaLibroDAO;
	}

	public static void setReservaLibroDAO(ReservaLibroDAOInterface reservaLibroDAO) {
		Main.reservaLibroDAO = reservaLibroDAO;
	}

	public static EventoDAOInterface getEventoDAO() {
		return eventoDAO;
	}

	public static void setEventoDAO(EventoDAOInterface eventoDAO) {
		Main.eventoDAO = eventoDAO;
	}
	
	public static AsistenciaEventoDAOInterface getAsistenciaEventoDAO() {
		return asistenciaEventoDAO;
	}
	
	public static void setAsistenciaEventoDAO(AsistenciaEventoDAOInterface asistenciaEventoDAO) {
		Main.asistenciaEventoDAO = asistenciaEventoDAO;
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
	
	public static ArrayList<Thread> getThreads() {
		return threads;
	}
	
	public static void setThreads(ArrayList<Thread> threads) {
		Main.threads = threads;
	}
	

    public static void main(String[] args) {
    	
    	// Carga de propiedades
    	Properties properties = new Properties();
		try {
			properties.load(new FileReader(PROPERTIES_FILE));
		} catch (IOException e) {
			System.err.println("Error al abrir el fichero de propiedades:" + e);;
		}
		
		driver = properties.getProperty("driver");
		connection = properties.getProperty("connection");
		nombreBD = properties.getProperty("dbName");
    	 	
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
        reservaSalaPublicaDAO = new ReservaSalaPublicaDAO();
        reviewDAO = new ReviewDAO();
        libroDAO = new LibroDAO();
        reservaLibroDAO = new ReservaLibroDAO();
        eventoDAO = new EventoDAO();
        asistenciaEventoDAO = new AsistenciaEventoDAO();
        
        // Inicialización de la lista de threads
        threads = new ArrayList<>();
        
        // Carga de datos del .csv a la BD (Si esto está comentado es que ya está creada)
        //new CrearBBDD();
        //new CargarDatosEnBBDD();
    	    	
    	// Inicialización de usuario y sala pública
    	usuario = null;
    	salaPublica = Main.getSalaDAO().getSalaPublica();
             
        // Inicio de la interfaz gráfica
    	SwingUtilities.invokeLater(() -> new VentanaPortada());
    }
}
