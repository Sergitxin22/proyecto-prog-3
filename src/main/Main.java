package main;

import BiblioTech.Admin;
import BiblioTech.Cliente;
import BiblioTech.SalaPublica;
import BiblioTech.Usuario;
import db.GestorDB;
import dbmejorada.ClienteDAOBBDD;
import dbmejorada.ClienteDAOCSV;
import dbmejorada.ClienteDAOInterface;
import gui.Portada;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
	public static Usuario usuario = null;
    private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new ArrayList<>());

    public static SalaPublica getSalaPublica() {
    	return salaPublica;
    }

    public static void main(String[] args) {
    	// TODO quitar todos los usuarios como argumento de los constructores de las ventanas 
    	// y obtener/modificar el usuario mediante Main.usuario
        
        // Para probar cada vista en diferentes tipos de usuario, descomenta una de las tres líneas       
        new Portada(); // NO LOGUEADO
//      new Portada(new Cliente()); // CLIENTE
//      new Portada(new Admin()); // ADMIN
        
        
//        Pruebas de consultas con la nueva arquitectura
//        
//    	clienteDAO = new ClienteDAOBBDD("bibliotech-nueva-arquitectura");
//    	clienteDAO.borrarRegistros();
//        Cliente u = new Cliente("00000000A", "Sergio", "sergio@si.es", LocalDateTime.now(), "hola", new ArrayList<>(),
//              new ArrayList<>(), 0);
//        Admin a = new Admin("11111111B", "Aroa", "aroa@no.com", LocalDateTime.now(), "aroa2003", new ArrayList<>());
//        clienteDAO.guardarCliente(u);
//        clienteDAO.guardarCliente(a);
//        
//        clienteDAO = new ClienteDAOCSV("bibliotech-nueva-arquitectura");
//        clienteDAO.borrarRegistros();
//        clienteDAO.guardarCliente(u);
//        clienteDAO.guardarCliente(a);        
    }
}
