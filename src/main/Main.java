package main;

import BiblioTech.SalaPublica;
import gui.Portada;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new ArrayList<>());
    private static Connection connection;

    public static SalaPublica getSalaPublica() {
        return salaPublica;
    }

    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido cargar el driver de la base de datos");
            }

        try {
            connection = DriverManager.getConnection("jdbc:resources/db/bibliotech.db");
        } catch (SQLException ex) {
            System.err.println("ERROR: Base de datos no encontrada");
        }

        //consultas

        new Portada(null);
    }
}
