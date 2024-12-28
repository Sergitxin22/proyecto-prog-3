package main;

import dbmejorada.ReservaSalaDAOBBDD;
import dbmejorada.ReservaSalaDAOInterface;
import dbmejorada.UsuarioDAOBBDD;
import dbmejorada.UsuarioDAOInterface;
import domain.SalaPublica;
import domain.Usuario;
import gui.VentanaPortada;

import java.util.ArrayList;

public class Main {
	private static Usuario usuario = null;

	//	Declaración de los DAO
	private static UsuarioDAOInterface usuarioDAO = new UsuarioDAOBBDD("bibliotech");
	private static ReservaSalaDAOInterface reservaSalaDAO = new ReservaSalaDAOBBDD("bibliotech");
	private static SalaPublica salaPublica = new SalaPublica(250, 0, 1, new ArrayList<>());

	public static UsuarioDAOInterface getUsuarioDAO() {
		return usuarioDAO;
	}

	public static void setUsuarioDAO(UsuarioDAOInterface usuarioDAO) {
		Main.usuarioDAO = usuarioDAO;
	}
	
	public static ReservaSalaDAOInterface getReservaSalaDAO() {
		return reservaSalaDAO;
	}

	public static void setReservaSalaDAO(ReservaSalaDAOInterface reservaSalaDAO) {
		Main.reservaSalaDAO = reservaSalaDAO;
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
