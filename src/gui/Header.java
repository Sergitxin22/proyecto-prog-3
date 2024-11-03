package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
//        setBackground(Color.GRAY);
//        setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
        
        // Primer panel (izquierdo)
        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setBackground(Color.PINK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 15); // Margen entre componentes (icono y texto)
        gbc.anchor = GridBagConstraints.CENTER; // Centrar verticalmente y horizontalmente        

        // Icono de la sección        
        String nombreIconoSeccion = obtenerNombreImagenSeccion(seccion);
        System.out.println(nombreIconoSeccion);
        
        ImageIcon iconoSeccion = tieneImagen ? Utils.loadImage(nombreIconoSeccion,48,48) : new ImageIcon();
        JLabel iconLabel = new JLabel(iconoSeccion);
        
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
        
        if (tieneImagen) {
            panelIzquierdo.add(iconLabel, gbc);
        } else {
        	gbc.insets = new Insets(0, 15, 0, 15);
        }
        gbc.gridx = 1; // Segunda columna
        panelIzquierdo.add(textLabel, gbc);
        
        // Segundo panel (derecho)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.DARK_GRAY);
        
        String nombreIconoUsuario = obtenerNombreImagenUsuario(usuario);        
        
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
			return "libros.png";
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
