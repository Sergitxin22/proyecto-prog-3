package main;

import BiblioTech.Admin;
import BiblioTech.Cliente;
import BiblioTech.SalaPublica;
import BiblioTech.Usuario;
import db.GestorDB;
import dbmejorada.UsuarioDAOBBDD;
import dbmejorada.UsuarioDAOCSV;
import dbmejorada.UsuarioDAOInterface;
import dbmejorada.UsuarioDTO;
import gui.Portada;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
	public static Usuario usuario = null;

//	Declaración de los DAO
	private static UsuarioDAOInterface usuarioDAO = null;

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
        
        
//        Pruebas de consultas con la nueva arquitectura (ahora estarán dentro de cada DAO)    
//    	usuarioDAO = new UsuarioDAOBBDD("bibliotech");
    }
}
