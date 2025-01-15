package db;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import main.Main;

class ReservaSalaPrivadaDAOTest {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	private ReservaSalaPrivadaDAOInterface reservaSalaPrivadaDAO;

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
            reservaSalaPrivadaDAO = new ReservaSalaPrivadaDAO(conexion, logger);
            
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
        	Main.setConexionBD(null);
            if (Main.getLogger() != null)
            	Main.getLogger().log(Level.SEVERE, "Error en el .jar o en la conexión de base de datos " + nombreBD + ".db", e);
        }        
		
		reservaSalaPrivadaDAO.borrarRegistros();
	}
	
	@Test
	void testAddReservaSalaPrivada() {
		ReservaSalaPrivadaDTO reserva = new ReservaSalaPrivadaDTO(0, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(), "00000000A", 4);
		assertTrue(reservaSalaPrivadaDAO.addReservaSalaPrivada(reserva));
	}
	
	@Test
	void testGetReservasSalaPrivadaByUsuarioDTO() {
		ReservaSalaPrivadaDTO reserva = new ReservaSalaPrivadaDTO(0, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(), "00000000A", 4);
		reservaSalaPrivadaDAO.addReservaSalaPrivada(reserva);
		ArrayList<ReservaSalaPrivadaDTO> esperado = new ArrayList<>();
		esperado.add(reserva);
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setDni("00000000A");
		
		assertEquals(esperado.get(0).getDniCliente(), reservaSalaPrivadaDAO.getReservasSalaPrivadaByUsuarioDTO(usuario).get(0).getDniCliente());
	}
	
	@Test
	void testGetIdSalasPrivadasNoDisponiblesEntreFechas() {
		assertEquals(new ArrayList<>(), reservaSalaPrivadaDAO.getIdSalasPrivadasNoDisponiblesEntreFechas(0, LocalDateTime.now(), LocalDateTime.now()));
	}
	
	@Test
	void testGetReservasSalaPrivadaByIdSala() {
		ReservaSalaPrivadaDTO reserva = new ReservaSalaPrivadaDTO(0, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(), "00000000A", 4);
		reservaSalaPrivadaDAO.addReservaSalaPrivada(reserva);
		ArrayList<ReservaSalaPrivadaDTO> esperado = new ArrayList<>();
		esperado.add(reserva);
		
		assertEquals(esperado.get(0).getIdSala(), reservaSalaPrivadaDAO.getReservasSalaPrivadaByIdSala(4).get(0).getIdSala());
	}
	
	@Test
	void testBorrarRegistros() {
		ReservaSalaPrivadaDTO reserva = new ReservaSalaPrivadaDTO(0, LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(), "00000000A", 4);
		reservaSalaPrivadaDAO.addReservaSalaPrivada(reserva);
		reservaSalaPrivadaDAO.borrarRegistros();
		
		assertNull(reservaSalaPrivadaDAO.getReservaSalaPrivadaById(0));
	}
}
