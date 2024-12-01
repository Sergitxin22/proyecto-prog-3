package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyPermission;
import java.util.logging.Level;
import java.util.logging.Logger;

import BiblioTech.Cliente;
import BiblioTech.Usuario;

public class GestorDB {

    private Connection conexionBD = null;
    private Statement sentenciaBD = null;
    private Logger logger = null;

    public Connection init(String nombreBD) {
        nombreBD = "resources/db/" + nombreBD;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD + ".db");
            logger = Logger.getLogger("GestorPersistencia-" + nombreBD);
            conexionBD = con;
            return con;
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            conexionBD = null;
            if (logger != null)
                logger.log(Level.SEVERE, "Error en conexión de base de datos " + nombreBD + ".db", e);
            return null;
        }
    }

    public boolean existeBD(String nombreBD) {
        nombreBD = "resources/db/" + nombreBD;
        File fichero = new File(nombreBD + ".db");
        return fichero.exists() && fichero.length() > 0;
    }

    public Connection getConnection() {
        return conexionBD;
    }

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
                logger.log(Level.SEVERE, "Error en añadido de usuario: ", e);
            return false;
        }
    }
}
