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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Main;

class ReservaSalaPublicaDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private ReservaSalaPublicaDAOInterface reservaSalaPublicaDAO;

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
            reservaSalaPublicaDAO = new ReservaSalaPublicaDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		reservaSalaPublicaDAO.borrarRegistros();
	}
	
	@Test
	void testAddReservaSalaPublica() {
		ReservaSalaPublicaDTO reserva = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), "00000000A", 25);
		assertTrue(reservaSalaPublicaDAO.addReservaSalaPublica(reserva));
	}
	
	@Test
	void testGetReservasSalaPublicaByUsuarioDTO() {
		ArrayList<ReservaSalaPublicaDTO> esperado = new ArrayList<>();
		ReservaSalaPublicaDTO reserva = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), "00000000A", 25);
		esperado.add(reserva);
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDni("00000000A");
		reservaSalaPublicaDAO.addReservaSalaPublica(reserva);
		assertEquals(esperado.get(0).getDniCliente(), reservaSalaPublicaDAO.getReservasSalaPublicaByUsuarioDTO(usuario).get(0).getDniCliente());
	}
	
	@Test
	void testIsSalaPublicaReservable() {
		ReservaSalaPublicaDTO reserva = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), "00000000A", 25);
		assertTrue(reservaSalaPublicaDAO.isSalaPublicaReservable(reserva));
	}
	
	@Test
	void testGetAsientosDisponibles() {
		ArrayList<Integer> esperado = new ArrayList<>();
		
		for (int i = 1; i <= 250; i++) {
			esperado.add(i);
		}
		
		assertEquals(esperado, reservaSalaPublicaDAO.getAsientosDisponibles(LocalDate.now()));
	}
	
	@Test
	void testBorrarRegistros() {
		ReservaSalaPublicaDTO reserva = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), "00000000A", 25);
		reservaSalaPublicaDAO.addReservaSalaPublica(reserva);
		reservaSalaPublicaDAO.borrarRegistros();
		assertNull(reservaSalaPublicaDAO.getReservaSalaPublicaById(0));
	}
}
