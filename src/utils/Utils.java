package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import BiblioTech.Libro;
import BiblioTech.Review;

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
		
		Scanner sc;
		try {
			sc = new Scanner(f);
			
			int contador = 0;
			while (sc.hasNextLine() && contador<35) {
				String linea = sc.nextLine();
				String[] datos = linea.split(";");
				
//				System.out.println(id);
				System.out.println(datos.length);
				
				try {
					long isbn = Long.parseLong(datos[0]);
					String titulo = datos[2];
	                String autor = datos[4];
	                String genero = datos[5];
	                String sinopsis = datos[7];
	                int fecha_publicacion = Integer.parseInt(datos[8]);
	                int rating = Integer.parseInt(datos[9]);
	                int numeroDePaginas = Integer.parseInt(datos[10]);
	                System.out.println(isbn);

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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaLibros;
	}	
}
