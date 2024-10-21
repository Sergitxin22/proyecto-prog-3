package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utils {
	public static ImageIcon loadImage(String imageName, int length, int height) {
		
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
			e.printStackTrace();
		}
		
		result = new ImageIcon(bi.getScaledInstance(length, height, Image.SCALE_DEFAULT)); // La convierte en ImageIcon con un tamaño
		
		return result;
	}	
}
