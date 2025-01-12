package io;

import java.util.ArrayList;

import domain.Evento;
import domain.Libro;
import domain.Review;
import domain.Sala;
import domain.Usuario;
import main.Main;

public class CargarDatosEnBBDD {
	public CargarDatosEnBBDD() {
		ArrayList<Libro> listaLibros = InputUtils.cargarLibros();
		addLibros(listaLibros);
		
		addRecursos();
		addTiposSala();
		
		ArrayList<Sala> listaSalas = InputUtils.cargarSalas();
		addSalas(listaSalas);
		
		addTiposEvento();
		ArrayList<Evento> listaEventos = InputUtils.cargarEventos(); // TODO: Falta en el InputUtils
		addEventos(listaEventos);
		
		ArrayList<Usuario> listaUsuarios = InputUtils.cargarUsuarios();
		System.out.println(listaUsuarios);
		addUsuarios(listaUsuarios);
		
		ArrayList<Review> listaReviews = InputUtils.cargarReviews(listaLibros, listaUsuarios);
		addReviews(listaReviews);		
	}

	private void addLibros(ArrayList<Libro> listaLibros) {
		for (Libro libro : listaLibros) {
			Main.getLibroDAO().addLibro(libro);
		}		
	}
	
	private void addRecursos() {
		Main.getSalaDAO().addRecursos();
	}
	
	private void addTiposSala() {
		Main.getSalaDAO().addTiposSala();	
	}
	
	private void addSalas(ArrayList<Sala> listaSalas) {
		for (Sala sala : listaSalas) {
			Main.getSalaDAO().addSala(sala);
		}		
	}
	
	private void addTiposEvento() {
		Main.getEventoDAO().addTiposEvento();
	}
	
	private void addEventos(ArrayList<Evento> listaEventos) {
		for (Evento evento : listaEventos) {
			Main.getEventoDAO().addEvento(evento);
		}		
	}

	private void addUsuarios(ArrayList<Usuario> listaUsuarios) {
		for (Usuario usuario : listaUsuarios) {
			Main.getUsuarioDAO().addUsuario(usuario);
		}
		
	}
	
	private void addReviews(ArrayList<Review> listaReviews) {
		for (Review review : listaReviews) {
			Main.getReviewDAO().addReview(review);
		}		
	}
}
