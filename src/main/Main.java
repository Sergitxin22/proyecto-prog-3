package main;

import BiblioTech.Admin;
import BiblioTech.Cliente;
import BiblioTech.SalaPublica;
import BiblioTech.Usuario;
import db.GestorDB;
import gui.Portada;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    private static GestorDB bd = new GestorDB();
    // private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new
    // ArrayList<>());

    // public static SalaPublica getSalaPublica() {
    // return salaPublica;
    // }

    public static void main(String[] args) {

        // try {
        // Class.forName("org.sqlite.JDBC");
        // } catch (ClassNotFoundException e) {
        // System.out.println("No se ha podido cargar el driver de la base de datos");
        // }

        // try {
        // connection = DriverManager.getConnection("jdbc:resources/db/bibliotech.db");
        // } catch (SQLException ex) {
        // System.err.println("ERROR: Base de datos no encontrada");
        // }

        // //consultas
        bd.init("bibliotech");
        System.out.println(bd.existeBD("bibliotech"));
        Cliente u = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDateTime.now(), "hola", new ArrayList<>(),
                new ArrayList<>(), 0);
        Admin a = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDateTime.now(), "aroa2003", new ArrayList<>());

        bd.addUsuario(u);
        bd.addUsuario(a);

        try {
            bd.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // new Portada(null);
    }
}
