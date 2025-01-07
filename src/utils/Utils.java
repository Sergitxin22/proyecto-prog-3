package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import domain.Libro;
import domain.MetodosDeOrdenamiento;

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
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Libro> sortArrayMetodoDeOrdenamiento(ArrayList<Libro> lista, MetodosDeOrdenamiento ordenar, int n) {
	    // Caso base: Si la longitud de la lista es 1, ya está ordenada
	    if (n == 1) {
	        return lista;
	    }

	    // Variables para almacenar los elementos a comparar
	    Object primerElemento = null;
	    Object segundoElemento = null;

	    // Realizar una pasada de Bubble Sort
	    for (int i = 0; i < n - 1; i++) {
	        switch (ordenar) {
	            case TITULO:
	                primerElemento = lista.get(i).getTitulo();
	                segundoElemento = lista.get(i + 1).getTitulo();
	                break;
	            case AUTOR:
	                primerElemento = lista.get(i).getAutor();
	                segundoElemento = lista.get(i + 1).getAutor();
	                break;
	            case FECHA:
	                primerElemento = lista.get(i).getFechaPublicacion();
	                segundoElemento = lista.get(i + 1).getFechaPublicacion();
	                break;
	            default:
	                // Si no se pasa un metodo de ordenamiento, no se realiza ningún cambio
	                return lista;  // Retornar la lista sin modificaciones
	        }

	        // Comparar los elementos y realizar el intercambio
	        if (((Comparable<Object>) primerElemento).compareTo(segundoElemento) > 0) {
	            // Intercambiar los Libros
	            Libro temp = lista.get(i);
	            lista.set(i, lista.get(i + 1));
	            lista.set(i + 1, temp);
	        }
	    }

	    // Llamada recursiva sin los ultimos elementos (ya están ordenados)
	    return sortArrayMetodoDeOrdenamiento(lista, ordenar, n - 1);  // Retornar la lista después de la llamada recursiva
	}

}
