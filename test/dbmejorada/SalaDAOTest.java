package dbmejorada;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Recurso;
import domain.Review;
import domain.SalaPrivada;
import main.Main;

class SalaDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private SalaDAOInterface salaDAO;

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
            salaDAO = new SalaDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		salaDAO.borrarRegistros();
	}
	
	@Test
	void testAddSala() {
		salaDAO.addRecursos();
		salaDAO.addTiposSala();
		ArrayList<Recurso> recursos = new ArrayList<>();
		recursos.add(Recurso.PIZARRA);
		SalaPrivada sala = new SalaPrivada(5, 2, 2, recursos, new ArrayList<>());
		assertTrue(salaDAO.addSala(sala));
	}
	
	@Test
	void testDeleteSala() {
		salaDAO.addRecursos();
		salaDAO.addTiposSala();
		ArrayList<Recurso> recursos = new ArrayList<>();
		recursos.add(Recurso.PIZARRA);
		SalaPrivada sala = new SalaPrivada(5, 2, 2, recursos, new ArrayList<>());
		salaDAO.addSala(sala);
		salaDAO.deleteSala(5);
		assertNull(salaDAO.getSala(5));
	}
	
	@Test
	void testBorrarRegistros() {
		salaDAO.addRecursos();
		salaDAO.addTiposSala();
		ArrayList<Recurso> recursos = new ArrayList<>();
		recursos.add(Recurso.PIZARRA);
		SalaPrivada sala = new SalaPrivada(5, 2, 2, recursos, new ArrayList<>());
		salaDAO.addSala(sala);
		salaDAO.borrarRegistros();
		assertNull(salaDAO.getSala(5));
	}
}
