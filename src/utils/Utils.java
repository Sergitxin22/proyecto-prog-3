package utils;

import BiblioTech.Cliente;
import BiblioTech.Evento;
import BiblioTech.Libro;
import BiblioTech.Recurso;
import BiblioTech.Review;
import BiblioTech.Sala;
import BiblioTech.SalaEventos;
import BiblioTech.SalaPrivada;
import BiblioTech.SalaPublica;
import BiblioTech.TipoEvento;
import BiblioTech.Usuario;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	


}
