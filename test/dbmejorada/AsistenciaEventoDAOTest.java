package dbmejorada;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Main;

class AsistenciaEventoDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private AsistenciaEventoDAOInterface asistenciaEventoDAO;

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
            asistenciaEventoDAO = new AsistenciaEventoDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		asistenciaEventoDAO.borrarRegistros();
	}
	
	@Test
	void testAddAsistenciaEvento() {
		AsistenciaEventoDTO asistenciaEventoDTO = new AsistenciaEventoDTO(0, "00000000A", 10);
		assertTrue(asistenciaEventoDAO.addAsistenciaEvento(asistenciaEventoDTO));
	}
	
	@Test
	void isUsuarioAsistente() {
		AsistenciaEventoDTO asistenciaEventoDTO = new AsistenciaEventoDTO(0, "00000000A", 10);
		asistenciaEventoDAO.addAsistenciaEvento(asistenciaEventoDTO);
		assertTrue(asistenciaEventoDAO.isUsuarioAsistente("00000000A", 10));
	}
	
	@Test
	void borrarRegistros() {
		AsistenciaEventoDTO asistenciaEventoDTO = new AsistenciaEventoDTO(0, "00000000A", 10);
		asistenciaEventoDAO.addAsistenciaEvento(asistenciaEventoDTO);
		asistenciaEventoDAO.borrarRegistros();
		assertFalse(asistenciaEventoDAO.isUsuarioAsistente("00000000A", 10));
		
		
	}
}
