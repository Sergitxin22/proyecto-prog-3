package domain;

import java.time.LocalDate;
import java.util.ArrayList;

import db.UsuarioDTO;
import main.Main;

public class Cliente extends Usuario {

	private ArrayList<Libro> historial;
	private ArrayList<Review> listaReviews;
	private int amonestaciones;
	
	public Cliente(String dni, String nombre, String email, LocalDate fechaCreacion, String contrasena,
			ArrayList<Libro> historial, ArrayList<Review> listaReviews, int amonestaciones) {
		super(dni, nombre, email, fechaCreacion, contrasena);
		this.historial = historial;
		this.listaReviews = listaReviews;
		this.amonestaciones = amonestaciones;
	}
	
	public Cliente() {
		super();
		this.historial = new ArrayList<Libro>();
		this.listaReviews = new ArrayList<Review>();
		this.amonestaciones = 0;
	}
	
	public Cliente(UsuarioDTO usuarioDTO) {
		super(usuarioDTO);
		this.historial = new ArrayList<>();
		this.listaReviews = Main.getReviewDAO().getReviewsByUsuarioDni(usuarioDTO.getDni());
		this.amonestaciones = usuarioDTO.getAmonestaciones();
	}

	public ArrayList<Libro> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Libro> historial) {
		this.historial = historial;
	}

	public ArrayList<Review> getListaReviews() {
		return listaReviews;
	}

	public void setListaReviews(ArrayList<Review> listaReviews) {
		this.listaReviews = listaReviews;
	}

	public int getAmonestaciones() {
		return amonestaciones;
	}

	public void setAmonestaciones(int amonestaciones) {
		this.amonestaciones = amonestaciones;
	}

	@Override
	public String toString() {
		return "Cliente [historial=" + historial + ", listaReviews=" + listaReviews + ", amonestaciones="
				+ amonestaciones + ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getEmail()="
				+ getEmail() + ", getFechaCreacion()=" + getFechaCreacion() + ", getContrasena()=" + getContrasena()
				+ "]";
	}	
}
