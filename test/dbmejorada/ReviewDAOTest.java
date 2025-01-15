package dbmejorada;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Review;
import main.Main;

class ReviewDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private ReviewDAOInterface reviewDAO;

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
            reviewDAO = new ReviewDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		reviewDAO.borrarRegistros();
	}
	
	@Test
	void testAddReview() {
		LibroDTO libro = new LibroDTO(123, "Libro 1", "George Orwell", 465, "Sinópsis", "Drama", 6, 2004);
		UsuarioDTO usuario = new UsuarioDTO("00000000A", "Sergio", "sergio@si.es", LocalDate.now(), 2, false);
		Review review = new Review(libro, usuario, "Texto", 5);
		
		assertTrue(reviewDAO.addReview(review));
	}
}
