package dbmejorada;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Main;

class ReservaLibroDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private ReservaLibroDAOInterface reservaLibroDAO;

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
            reservaLibroDAO = new ReservaLibroDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		reservaLibroDAO.borrarRegistros();
	}
	
	@Test
	void testAddReservaLibro() {
		ReservaLibroDTO reservaLibro = new ReservaLibroDTO(0, LocalDateTime.now(), LocalDateTime.now(), (long) 123, "00000000A");
		assertTrue(reservaLibroDAO.addReservaLibro(reservaLibro));
	}
	
	@Test
	void testGetHistorialByDniUsuarioDTO() {
		ReservaLibroDTO reservaLibro = new ReservaLibroDTO(0, LocalDateTime.now(), LocalDateTime.now(), (long) 123, "00000000A");
		reservaLibroDAO.addReservaLibro(reservaLibro);
		ArrayList<ReservaLibroDTO> esperado = new ArrayList<>();
		esperado.add(reservaLibro);
		
		assertEquals(esperado.get(0).getDniCliente(), reservaLibroDAO.getHistorialByDniUsuarioDTO("00000000A").get(0).getDniCliente());	
	}
	
	@Test
	void testIsLibroDisponible() {
		ReservaLibroDTO reservaLibro = new ReservaLibroDTO(0, LocalDateTime.now(), LocalDateTime.now(), (long) 123, "00000000A");
		assertTrue(reservaLibroDAO.isLibroDisponible(reservaLibro));
	}
	
	@Test
	void borrarRegistros() {
		ReservaLibroDTO reservaLibro = new ReservaLibroDTO(0, LocalDateTime.now(), LocalDateTime.now(), (long) 123, "00000000A");
		reservaLibroDAO.addReservaLibro(reservaLibro);
		reservaLibroDAO.borrarRegistros();
		assertNull(reservaLibroDAO.getReservaLibroById(0));
	}
}
