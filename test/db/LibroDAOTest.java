package db;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Cliente;
import domain.Libro;
import main.Main;

class LibroDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private LibroDAOInterface libroDAO;

	@BeforeEach
	void setUp() {
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
            Connection conexion = DriverManager.getConnection(connection);
            Logger logger = Logger.getLogger("GestorPersistencia-" + nombreBD);
            libroDAO = new LibroDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		libroDAO.borrarRegistros();
	}
	
	@Test
	void testAddLibro() {
		Libro libro = new Libro(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004, null, new ArrayList<>());
		assertTrue(libroDAO.addLibro(libro));
	}
	
	@Test
	void testGetLibro() {
		Libro libro = new Libro(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004, null, new ArrayList<>());
		libroDAO.addLibro(libro);
		assertEquals(new LibroDTO(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004), libroDAO.getLibro(123));
	}
	
	void testAñadirReserva() {
		Cliente cliente = new Cliente();
		assertTrue(libroDAO.añadirReserva(123, 5, cliente));
	}
	
	@Test
	void testGetHistorialByCliente() {
		
	}
	
	@Test
	void testUpdateLibro() {
		Libro libro = new Libro(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004, null, new ArrayList<>());
		libroDAO.addLibro(libro);
		LibroDTO libroEditado = new LibroDTO(124, "Libro 2", "George Lucas", 432, "Sinópsis 2", "Fantasía", 3, 1634);
		libroDAO.updateLibro(libroEditado, 123);
		assertEquals(libroEditado, libroDAO.getLibro(124));
	}
	
	@Test
	void testDeleteLibroByIsbn() {
		Libro libro = new Libro(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004, null, new ArrayList<>());
		libroDAO.addLibro(libro);
		libroDAO.deleteLibroByIsbn(123);
		assertNull(libroDAO.getLibro(123));
	}
	
	@Test
	void testLibroLeidoByDniCliente() {
		Libro libro = new Libro(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004, null, new ArrayList<>());
		libroDAO.addLibro(libro);
		Cliente cliente = new Cliente();
		libroDAO.añadirReserva(123, 5, cliente);
		assertTrue(libroDAO.libroLeidoByDniCliente(cliente.getDni(), libro.getIsbn()));
	}
	
	@Test
	void testBorrarRegistros() {
		Libro libro = new Libro(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004, null, new ArrayList<>());
		libroDAO.addLibro(libro);
		libroDAO.borrarRegistros();
		assertNull(libroDAO.getLibro(123));
	}
}
