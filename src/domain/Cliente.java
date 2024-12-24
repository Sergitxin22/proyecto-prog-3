package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cliente extends Usuario {

	private ArrayList<Libro> historial;
	private ArrayList<Review> listaReviews;
	private int amonestaciones;
	
	public Cliente(String dni, String nombre, String email, LocalDateTime fechaCreacion, String contrasena,
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
