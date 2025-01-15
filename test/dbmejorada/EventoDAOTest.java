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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import domain.Evento;
import domain.SalaEventos;
import domain.TipoEvento;
import main.Main;

class EventoDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private EventoDAOInterface eventoDAO;

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
            eventoDAO = new EventoDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		eventoDAO.borrarRegistros();
	}
	
	@Test
	void testAddEvento() {
		Evento evento = new Evento(10, "Conferencia sobre empresas", TipoEvento.CONFERENCIA, new ArrayList<>(), new SalaEventos(50, 12, 3), LocalDateTime.now());
		assertTrue(eventoDAO.addEvento(evento));
	}
	
	@Test
	void testDeleteEvento() {
		Evento evento = new Evento(10, "Conferencia sobre empresas", TipoEvento.CONFERENCIA, new ArrayList<>(), new SalaEventos(50, 12, 3), LocalDateTime.now());
		eventoDAO.addEvento(evento);
		eventoDAO.deleteEvento(evento.getId());
		assertNull(eventoDAO.getEvento(evento.getId()));
	}
	
	@Test
	void testGetEvento() {
		LocalDateTime fecha = LocalDateTime.now();
		Evento evento = new Evento(10, "Conferencia sobre empresas", TipoEvento.CONFERENCIA, new ArrayList<>(), new SalaEventos(50, 12, 3), fecha);
		eventoDAO.addEvento(evento);
		EventoDTO eventoDTO = eventoDAO.getEvento(evento.getId());
		assertEquals(eventoDTO, new EventoDTO(10, "Conferencia sobre empresas", 6, 12, fecha));
	}
	
	@Test
	void testBorrarRegistros() {
		Evento evento = new Evento(10, "Conferencia sobre empresas", TipoEvento.CONFERENCIA, new ArrayList<>(), new SalaEventos(50, 12, 3), LocalDateTime.now());
		eventoDAO.addEvento(evento);
		eventoDAO.borrarRegistros();
		assertNull(eventoDAO.getEvento(evento.getId()));
	}
}
