package main;

import dbmejorada.AsistenciaEventoDAO;
import dbmejorada.AsistenciaEventoDAOInterface;
import dbmejorada.EventoDAO;
import dbmejorada.EventoDAOInterface;
import dbmejorada.LibroDAO;
import dbmejorada.LibroDAOInterface;
import dbmejorada.ReservaSalaPrivadaDAO;
import dbmejorada.ReservaSalaPrivadaDAOInterface;
import dbmejorada.ReservaSalaPublicaDAO;
import dbmejorada.ReservaSalaPublicaDAOInterface;
import dbmejorada.ReviewDAO;
import dbmejorada.ReviewDAOInterface;
import dbmejorada.SalaDAO;
import dbmejorada.SalaDAOInterface;
import dbmejorada.UsuarioDAO;
import dbmejorada.UsuarioDAOInterface;

import domain.SalaPublica;
import domain.Usuario;

import gui.VentanaPortada;

import io.CargarDatosEnBBDD;

import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

public class Main {
	private final static String PROPERTIES_FILE = "app.properties";
	private static String driver;
	private static String connection;
	private static String nombreBD;
	
	private static Usuario usuario;
	private static SalaPublica salaPublica;
	
	private static Connection conexionBD;
	private static Logger logger;
	
	private static UsuarioDAOInterface usuarioDAO;
	private static SalaDAOInterface salaDAO;
	private static ReservaSalaPrivadaDAOInterface reservaSalaPrivadaDAO;
	private static ReservaSalaPublicaDAOInterface reservaSalaPublicaDAO;
	private static ReviewDAOInterface reviewDAO;
	private static LibroDAOInterface libroDAO;
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
        eventoDAO = new EventoDAO();
        asistenciaEventoDAO= new AsistenciaEventoDAO();
        
        // Carga de datos del .csv a la BD
        new CargarDatosEnBBDD();
    	    	
    	// Inicialización de usuario y sala pública
    	usuario = null;
    	salaPublica = new SalaPublica(Main.getSalaDAO().getSala(0));
             
        // Inicio de la interfaz gráfica
    	SwingUtilities.invokeLater(() -> new VentanaPortada());
    }
}
