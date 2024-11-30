package utils;

import BiblioTech.Evento;
import BiblioTech.Libro;
import BiblioTech.Recurso;
import BiblioTech.Review;
import BiblioTech.Sala;
import BiblioTech.SalaEventos;
import BiblioTech.SalaPrivada;
import BiblioTech.SalaPublica;
import BiblioTech.TipoEvento;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.internal.ArrayComparisonFailure;

public class Utils {

	public static ImageIcon loadImage(String imageName, int width, int height) {
		
		/*
		 * Método que recibe una dirección de una imagen dentro de resources/images/
		 * y la carga en una variable de tipo ImageIcon,
		 * recibiendo como parámetros sus dimensiones.
		 */
		
		ImageIcon result;
		BufferedImage bi = null; // Inicialización del buffer
		try {
			bi = ImageIO.read(new File("resources/images/" + imageName)); // Abre la imagen
		} catch (IOException e) {
//			e.printStackTrace();
		}
		
		result = new ImageIcon(bi.getScaledInstance(width, height, Image.SCALE_DEFAULT)); // La convierte en ImageIcon con un tamaño
		
		return result;
	}
	
	public static ArrayList<Libro> cargarLibros() {
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		File f = new File("resources/data/books.csv");
		
		try {
			Scanner sc = new Scanner(f);
			
			int contador = 0;
			while (sc.hasNextLine() && contador<35) {
				String linea = sc.nextLine();
				String[] datos = linea.split(";");
				
				try {
					long isbn = Long.parseLong(datos[0]);
					String titulo = datos[2];
	                String autor = datos[4];
	                String genero = datos[5];
	                String sinopsis = datos[7];
	                int fecha_publicacion = Integer.parseInt(datos[8]);
	                int rating = Integer.parseInt(datos[9]);
	                int numeroDePaginas = Integer.parseInt(datos[10]);

	                ImageIcon foto = Utils.loadImage("books/" + isbn + ".jpg", 98, 151);
	                ArrayList<Review> reviews = new ArrayList<>();

	                Libro libro = new Libro(isbn, titulo, autor, numeroDePaginas, sinopsis, genero, rating, fecha_publicacion, foto, reviews);
	                listaLibros.add(libro);
	                contador++;

				} catch (Exception e) {
				    System.err.println("Error al convertir un número en la línea: " + linea);
				    e.printStackTrace();
				}
				
//				String titulo, String autor, int numeroDePaginas, Image foto, int id, String sinopsis,
//				ArrayList<Review> reviews, Genero genero, int rating
				
				
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Archivo no encontrado");
		}

	
		
		return listaLibros;
	}	

	public static ArrayList<Sala> cargarSalas() {
		ArrayList<Sala> result = new ArrayList<>();

		File file = new File("resources/data/salas.csv");
		try {
			Scanner scanner = new Scanner(file);

			scanner.nextLine();
			while(scanner.hasNextLine()) {
				Sala sala = null;
				String line = scanner.nextLine();
				String[] fields = line.split(";");

				int id = Integer.parseInt(fields[0]);
				int piso = Integer.parseInt(fields[1]);
				int capacidad = Integer.parseInt(fields[2]);
				
					
				if (fields[3].equals("PUBLICA")) {
					sala = new SalaPublica(capacidad, id, piso, new ArrayList<>(), new ArrayList<>());
				} 

				if (fields[3].equals("PRIVADA")) {
					ArrayList<Recurso> recursos = new ArrayList<>();
					String[] recursosArray = fields[4].split(",");

					for (int i = 0; i < recursosArray.length; i++) {
						switch (recursosArray[i]) {
							case "PIZARRA":
							recursos.add(Recurso.PIZARRA);
							break;

							case "PROYECTOR":
							recursos.add(Recurso.PROYECTOR);
							break;

							case "ORDENADORES":
							recursos.add(Recurso.ORDENADORES);
							break;
						}
					}

					sala = new SalaPrivada(capacidad, id, piso, new ArrayList<>(), recursos);
				}

				if (fields[3].equals("EVENTOS")) {
					sala = new SalaEventos(capacidad, id, piso, new ArrayList<>(), new Evento());
				}
				
				result.add(sala);
				
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se ha encontrado el archivo");
		}

		return result;
	}

	public static ArrayList<Evento> cargarEventos(ArrayList<SalaEventos> salasEventos) {
		ArrayList<Evento> result = new ArrayList<>();

		File file = new File("resources/data/eventos.csv");
		try {
			Scanner scanner = new Scanner(file);

			System.out.println(scanner.nextLine());
			while(scanner.hasNextLine()) {
				Evento evento;
				String line = scanner.nextLine();
				String[] fields = line.split(";");

				int idEvento = Integer.parseInt(fields[0]);
				String tipoString = fields[1];
				String titulo = fields[2];
				LocalDate fecha = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				int hora = Integer.parseInt(fields[4]);
				int idSala = Integer.parseInt(fields[5]);

				TipoEvento tipoEvento = null;
				switch (tipoString) {
					case "CHARLA":
						tipoEvento = TipoEvento.CHARLA;
						break;

					case "DEBATE":
						tipoEvento = TipoEvento.DEBATE;
						break;

					case "SEMINARIO":
						tipoEvento = TipoEvento.SEMINARIO;
						break;

					case "TALLER":
						tipoEvento = TipoEvento.TALLER;
						break;
				}

				SalaEventos salaEvento = null;
				for (SalaEventos sala : salasEventos) {
					if (sala.getId() == idSala) {
						salaEvento = sala;
						break;
					}
				}
				evento = new Evento(idEvento, titulo, tipoEvento, new ArrayList<>(), salaEvento, fecha, hora);

				result.add(evento);
				
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se ha encontrado el archivo");
		}

		return result;
	}

	public static void main(String[] args) {
		cargarEventos(new ArrayList<>());
	}

}
