package dbmejorada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.SQLiteException;

import domain.Admin;
import domain.Cliente;
import domain.Usuario;
import main.Main;

public class UsuarioDAO implements UsuarioDAOInterface {

	private Connection conexionBD;
    private Logger logger = null;

    public UsuarioDAO() {
       	conexionBD = Main.getConexionBD();
      	logger = Main.getLogger();
//        pruebas();
    }
    
    @Override
	public boolean addUsuario(Usuario usuario) {
    	try {
            String insertSQL = "INSERT INTO Usuario VALUES (?,?,?,?,?)";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1, usuario.getDni());
            preparedStmt.setString(2, usuario.getNombre());
            preparedStmt.setString(3, usuario.getEmail());
            preparedStmt.setString(4, usuario.getFechaCreacion().toString());
            preparedStmt.setString(5, usuario.getContrasena());

            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            PreparedStatement preparedStmt2 = null;
            if (usuario instanceof Cliente) {
                String insertSQL2 = "INSERT INTO Cliente VALUES (?,?)";
                preparedStmt2 = conexionBD.prepareStatement(insertSQL2);
                preparedStmt2.setString(1, usuario.getDni());
                preparedStmt2.setInt(2, ((Cliente) usuario).getAmonestaciones());
            } else {
                String insertSQL2 = "INSERT INTO Admin VALUES (?)";
                preparedStmt2 = conexionBD.prepareStatement(insertSQL2);
                preparedStmt2.setString(1, usuario.getDni());
            }

            int filas2 = preparedStmt2.executeUpdate();

            preparedStmt.close();
            preparedStmt2.close();
            return (filas > 0 && filas2 > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al añadir el usuario: ", e);
            return false;
        }
	}

	@Override
	public boolean isUsuarioCorrecto(String dni, String password) { // Se usa para el inicio de sesiones
		UsuarioDTO usuario = null;
		
		String insertSQL = "SELECT nombre FROM Usuario WHERE dni = ? AND contrasena = ?";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, dni);
	        preparedStmt.setString(2, password);

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    
                    usuario = new UsuarioDTO();
                    usuario.setDni(dni);
                    usuario.setNombre(nombre);
                }
                System.out.println("Usuario recuperado correctamente");
                return (usuario != null) ? true : false;  
            }
	        catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	        	        
		} catch (SQLException e) {
			if (logger != null) {
				logger.log(Level.SEVERE, "Error al recuperar el usuario: ", e);
			}
			return false;
		}
	}
		
		@Override
		public UsuarioDTO getUsuario(String dni) { // Se usa para el inicio de sesiones
			UsuarioDTO usuario = null;
			
			String insertSQL = "SELECT nombre FROM Usuario WHERE dni = ?";
	        PreparedStatement preparedStmt;
			try {
				preparedStmt = conexionBD.prepareStatement(insertSQL);
				preparedStmt.setString(1, dni);

		        try (ResultSet rs = preparedStmt.executeQuery()) {
	                
	                while (rs.next()) {
	                    String nombre = rs.getString("nombre");
	                    
	                    usuario = new UsuarioDTO();
	                    usuario.setDni(dni);
	                    usuario.setNombre(nombre);
	                }
	                System.out.println("Usuario recuperado correctamente");
	            }
		        	        
			} catch (SQLException e) {
				if (logger != null)
	                logger.log(Level.SEVERE, "Error al recuperar el usuario: ", e);
	            return usuario;
			}
        
			if (usuario != null) {
				getDatosAdicionalesUsuario(usuario);
			}
			return usuario;
	}
	
	@Override
	public void getDatosAdicionalesUsuario(UsuarioDTO usuario) {
		String insertSQL = "SELECT COUNT(1) AS contador, amonestaciones FROM Cliente WHERE dni = ?";
        PreparedStatement preparedStmt;
		try {
			preparedStmt = conexionBD.prepareStatement(insertSQL);
			preparedStmt.setString(1, usuario.getDni());

	        try (ResultSet rs = preparedStmt.executeQuery()) {
                while (rs.next()) {
                	int contador = rs.getInt("contador");
                	if (contador > 0) {
                		int amonestaciones = rs.getInt("amonestaciones");
                		usuario.setAmonestaciones(amonestaciones);
                		usuario.setAdmin(false);
					} else {
                		usuario.setAdmin(true);
					}
                }
                System.out.println("Datos adicionales del usuario obtenidos correctamente");
            }
	        	        
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al obtener datos adicionales del usuario: ", e);
			return;
		}
	}

	@Override
	public boolean updatePassword(UsuarioDTO usuario, String password) {
		try {
            String insertSQL = "UPDATE Usuario SET contrasena = ? WHERE dni = ?";
            PreparedStatement preparedStmt = conexionBD.prepareStatement(insertSQL);
            preparedStmt.setString(1, password);
            preparedStmt.setString(2, usuario.getDni());

            int filas = preparedStmt.executeUpdate();
            System.out.println("Filas modificadas: " + filas);

            return (filas > 0) ? true : false;
        } catch (SQLException e) {
            if (logger != null)
                logger.log(Level.SEVERE, "Error al cambiar la contraseña: ", e);
            return false;
        }
	}

	@Override
	public void borrarRegistros() {
		try {
			Statement stmt = conexionBD.createStatement();
			String instruccion = (
					"DELETE FROM Usuario;"
					+ "DELETE FROM Cliente;"
					+ "DELETE FROM Admin;"
			);
			
			int filas = stmt.executeUpdate(instruccion);
			stmt.close();
			System.out.println("Filas modificadas: " + filas);
		} catch (SQLException e) {
			if (logger != null)
                logger.log(Level.SEVERE, "Error al borrar los registros: ", e);
		}
	}
	
	public void pruebas() {
		System.out.println("****Borrar registros****");
    	//borrarRegistros();
    	
        Cliente u = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDateTime.now(), "hola", new ArrayList<>(),
              new ArrayList<>(), 3);
        Admin a = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDateTime.now(), "aroa2003", new ArrayList<>());
        
        System.out.println("****Añadir usuarios****");
        //addUsuario(u);
        //addUsuario(a);
        
        System.out.println("****Recuperar usuarios****");
        boolean existeUsuario = getUsuario("00000000A", "hola");
        
        UsuarioDTO uDTO = new UsuarioDTO(); 
        if (existeUsuario) {
			uDTO = getUsuario("00000000A");
		}
        
        System.out.println(uDTO);
        System.out.println(getUsuario("11111111B", "aroa2003"));

        System.out.println("****Recuperar usuario incorrecto****");
        System.out.println(getUsuario("00000000A", "holas"));
        
        System.out.println("****Cambiar contraseña y recuperar usuario con la contraseña anterior****");
        updatePassword(uDTO, "contraseña cambiada");
//        uDTO = getUsuario("00000000A", "hola");
        System.out.println(uDTO);
        
        System.out.println("****Recuperar usuario con la contraseña nueva****");
//        uDTO = getUsuario("00000000A", "contraseña cambiada");
        System.out.println(uDTO);
	}
}
