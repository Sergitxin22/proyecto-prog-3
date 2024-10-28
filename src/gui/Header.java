package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import BiblioTech.Admin;
import BiblioTech.Cliente;
import BiblioTech.Seccion;
import BiblioTech.Usuario;
import utils.Utils;

public class Header extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1102784202811827191L;
	private static boolean tieneImagen = true; 

	public Header(Seccion seccion, Usuario usuario) {
		setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        
        // Primer panel (izquierdo)
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIzquierdo.setBackground(Color.LIGHT_GRAY);
        
        // Crear el icono
        String nombreIconoSeccion = "";
        

        // Icono de la sección        
        String nombreIconoSeccion = obtenerNombreImagenSeccion(seccion);
        nombreIconoSeccion = "libros.png"; // TODO: quitar cuando tenga los iconos
        System.out.println(nombreIconoSeccion);
        
        ImageIcon iconoSeccion = tieneImagen ? Utils.loadImage(nombreIconoSeccion,48,48) : new ImageIcon();
        JLabel iconLabel = new JLabel(iconoSeccion);

        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel("BiblioTech"); // Texto al lado del icono
        
        // Añadir mouse listener para el ícono
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Ícono clickeado");
                // Aquí puedes agregar la lógica que necesites
            }
        });
        
        // Texto al lado del icono
        JLabel textLabel = new JLabel("BiblioTech");
        
        // Añadir mouse listener para el texto
        textLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Texto clickeado");
                // Aquí puedes agregar la lógica que necesites
            }
        });

        panelIzquierdo.add(iconLabel);
        panelIzquierdo.add(textLabel);
        
        // Segundo panel (derecho)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.DARK_GRAY);
        
        String nombreIconoUsuario = obtenerNombreImagenUsuario(usuario);        
        nombreIconoUsuario = "user.png"; // TODO: quitar cuando tenga los iconos
        
        ImageIcon icon2 = Utils.loadImage(nombreIconoUsuario, 48, 48);
        JLabel iconLabel2 = new JLabel(icon2);
        panelDerecho.add(iconLabel2);
        
        // Agregar los paneles izquierdo y derecho al Header
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.EAST);
	}
	
	private String obtenerNombreImagenSeccion(Seccion seccion) {
		if (seccion == null) {
			tieneImagen = false;
			return "";
		};
		
		switch (seccion) {
		case BIBLIOTECA:
			return "libro.png";
		case EVENTOS:
			return "eventos.png";
		case SALAS_DE_ESTUDIO:
			return "salasDeEstudio.png";
		default:
			return "salas.png";
		}
	}
	
	private String obtenerNombreImagenUsuario(Usuario usuario) {
		if (usuario instanceof Cliente) {
        	return "user.png";
		} else if (usuario instanceof Admin) {
			return "adminUser.png";
		} else {
			return "noUser.png";
		}
	}

}
