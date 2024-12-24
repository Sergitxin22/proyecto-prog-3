package main;

import db.GestorDB;
import dbmejorada.UsuarioDAOBBDD;
import dbmejorada.UsuarioDAOCSV;
import dbmejorada.UsuarioDAOInterface;
import dbmejorada.UsuarioDTO;
import domain.Admin;
import domain.Cliente;
import domain.SalaPublica;
import domain.Usuario;
import gui.VentanaPortada;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
	private static Usuario usuario = null;

	//	Declaración de los DAO
	private static UsuarioDAOInterface usuarioDAO = new UsuarioDAOBBDD("bibliotech");
	private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new ArrayList<>());

	 public static UsuarioDAOInterface getUsuarioDAO() {
			return usuarioDAO;
		}

	public static void setUsuarioDAO(UsuarioDAOInterface usuarioDAO) {
		Main.usuarioDAO = usuarioDAO;
	}

	public static void setSalaPublica(SalaPublica salaPublica) {
		Main.salaPublica = salaPublica;
	}
	
    public static SalaPublica getSalaPublica() {
    	return salaPublica;
    }
    
    public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		Main.usuario = usuario;
	}

    public static void main(String[] args) {
    	// TODO quitar todos los usuarios como argumento de los constructores de las ventanas 
    	// y obtener/modificar el usuario mediante Main.usuario
        
        // Para probar cada vista en diferentes tipos de usuario, descomenta una de las tres líneas       
        new VentanaPortada(); // NO LOGUEADO
//      new Portada(new Cliente()); // CLIENTE
//      new Portada(new Admin()); // ADMIN
        
        
//        Pruebas de consultas con la nueva arquitectura (ahora estarán dentro de cada DAO)    
    }
}
