package dbmejorada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Admin;
import domain.Cliente;
import domain.LogAccion;
import main.Main;

class UsuarioDAOTest {
	private final static String PROPERTIES_FILE = "app.properties";
	private UsuarioDAOInterface usuarioDAO;

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
            usuarioDAO = new UsuarioDAO(conexion, logger);
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		usuarioDAO.borrarRegistros();
	}

	@Test
	void testAddUsuario() {
		Cliente cliente = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), "hola",
				new ArrayList<>(), new ArrayList<>(), 3);
		Admin admin = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDate.now(), "aroa2003", new ArrayList<>());

		assertTrue(usuarioDAO.addUsuario(cliente));
		assertTrue(usuarioDAO.addUsuario(admin));
	}

	@Test
	void testIsUsuarioCorrecto() {
		Cliente cliente = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), "hola",
				new ArrayList<>(), new ArrayList<>(), 3);
		usuarioDAO.addUsuario(cliente);

		assertTrue(usuarioDAO.isUsuarioCorrecto("00000000A", "hola"));
		assertFalse(usuarioDAO.isUsuarioCorrecto("00000000A", "incorrecta"));
	}

	@Test
	void testGetUsuario() {
		Cliente cliente = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), "hola", new ArrayList<>(), new ArrayList<>(), 3);
		usuarioDAO.addUsuario(cliente);

		UsuarioDTO usuarioDTO = usuarioDAO.getUsuario("00000000A");
		assertNotNull(usuarioDTO);
		assertEquals("Sergio", usuarioDTO.getNombre());
	}

	@Test
	void testGetDatosAdicionalesUsuario() {
		Cliente cliente = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), "hola",
				new ArrayList<>(), new ArrayList<>(), 3);
		usuarioDAO.addUsuario(cliente);

		UsuarioDTO usuarioDTO = usuarioDAO.getUsuario("00000000A");
		usuarioDAO.getDatosAdicionalesUsuario(usuarioDTO);

		assertFalse(usuarioDTO.isAdmin());
		assertEquals(3, usuarioDTO.getAmonestaciones());
	}

	@Test
	void testUpdatePassword() {
		Cliente cliente = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), "hola",
				new ArrayList<>(), new ArrayList<>(), 3);
		usuarioDAO.addUsuario(cliente);

		UsuarioDTO usuarioDTO = usuarioDAO.getUsuario("00000000A");
		assertTrue(usuarioDAO.updatePassword(usuarioDTO, "nuevaContrasena"));
		assertTrue(usuarioDAO.isUsuarioCorrecto("00000000A", "nuevaContrasena"));
	}

	@Test
	void testAddLogAccion() {
		Admin admin = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDate.now(), "aroa2003", new ArrayList<>());
		usuarioDAO.addUsuario(admin);

		LogAccion logAccion = new LogAccion(1, LocalDateTime.now(), "LogAccion1", "11111111B");
		assertTrue(usuarioDAO.addLogAccion(logAccion));
	}

	@Test
	void testGetLogAccionesByAdminDni() {
		Admin admin = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDate.now(), "aroa2003", new ArrayList<>());
		usuarioDAO.addUsuario(admin);

		LogAccion logAccion1 = new LogAccion(1, LocalDateTime.now(), "LogAccion1", "11111111B");
		LogAccion logAccion2 = new LogAccion(2, LocalDateTime.now(), "LogAccion2", "11111111B");
		usuarioDAO.addLogAccion(logAccion1);
		usuarioDAO.addLogAccion(logAccion2);

		ArrayList<LogAccion> logAcciones = usuarioDAO.getLogAccionesByAdminDni("11111111B");
		assertEquals(2, logAcciones.size());
	}

	@Test
	void testBorrarRegistros() {
		Cliente cliente = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), "hola",
				new ArrayList<>(), new ArrayList<>(), 3);
		usuarioDAO.addUsuario(cliente);

		usuarioDAO.borrarRegistros();
		assertNull(usuarioDAO.getUsuario("00000000A"));
	}
	
	// TODO: testGetUsuarios
}
