package gui;

import domain.Cliente;
import domain.Seccion;
import domain.Usuario;
import gui.components.Header;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Utils;

public class VentanaInformacionUsuario extends JFrame {

	
	/*
	 *  TODO: en esta ventana falta:
	 *  - Cambiar el icono de modificar a uno que pegue más (una herramienta, lo que sea). Ahora mismo está el del admin, el alien
	 *  - En los textfields, en vez del placeholder que salgan los datos del usuario (el usuario, el email...)
	 *  - Para cambiar la contraseña crear un popup que te pida la contraseña anterior y la nueva
	 *  - Añadir la lógica para modificar en la base de datos los datos del usuario una vez que los modificas
	 *  - Añadir funcionalidad al botón de cerrar sesion
	 */

	private static final long serialVersionUID = 5069329725320750186L;
	private static boolean editarNombre = false;
	private static int contadorClicksNombre = 0;
	private static boolean editarEmail = false;
	private static int contadorClicksEmail = 0;
	private static boolean editarPassword = false;
	private static int contadorClicksPassword = 0;
	
	private Usuario usuario = Main.getUsuario();

	public VentanaInformacionUsuario(JFrame ventanaPrevia) {
		setTitle(usuario.getNombre() + ": Información");
		setSize(640,480);
		setLocationRelativeTo(null);

		JPanel contenido = new JPanel();
		contenido.setLayout(new GridLayout(0, 3));
		JPanel l1 = new JPanel();
		JPanel l2 = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE; // Alinea los elementos uno debajo del otro
		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(5, 0, 5, 0);

		JPanel l3 = new JPanel();
		contenido.add(l1);
		contenido.add(l2);
		contenido.add(l3);
		add(contenido);
		
		JLabel titulo = new JLabel("Usuario");
		Font fuenteTitulo = new Font("Arial", Font.PLAIN, 32);
		titulo.setFont(fuenteTitulo);
		
        JPanel panelNombre = getPanel("Nombre", "Usuario");
        JPanel panelEmail = getPanel("Email", "Email");
        JPanel panelPassword = getPanel("Contraseña", "Contraseña");
	    
	    JButton botonHistorial = new JButton("Ver historial de lectura");
	    JButton botonCerrarSesion = new JButton("Cerrar Sesión");
	    botonCerrarSesion.setBackground(Color.RED);
	    botonCerrarSesion.setForeground(Color.WHITE);
	    
		l2.add(titulo, gbc);
		l2.add(panelNombre, gbc);
		l2.add(panelEmail, gbc);
		l2.add(panelPassword, gbc);
		l2.add(botonHistorial, gbc);
		l2.add(botonCerrarSesion, gbc);
		
		botonHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaHistorialUsuario();
                dispose();
            }
        });
		
		botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Main.setUsuario(null);
            	
            	try {
					ventanaPrevia.getClass().getConstructor().newInstance();
					ventanaPrevia.dispose();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
            }
        });
		
		setVisible(true);
	}

	private JPanel getPanel(String labelText, String placeholder) {
		// Crear un panel con FlowLayout alineado a la izquierda
        JPanel panelContenidoCentrado = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Panel principal que contiene el nombre y se organiza en Y_AXIS
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        // Crear el JLabel "Nombre" con alineación a la izquierda
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel para el JLabel, también alineado a la izquierda
        JPanel panelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLabel.add(label);

        // Panel para el JTextField y el icono, alineados a la izquierda
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField input = new JTextField(placeholder, 10);
        input.setEnabled(false);
        ImageIcon iconoEditar = Utils.loadImage("edit.png", 20, 20);
        JLabel iconLabel = new JLabel(iconoEditar);
        if (labelText.equalsIgnoreCase("Nombre")) {
        	iconLabel.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				super.mouseClicked(e);
    				contadorClicksNombre++;
    				editarNombre = !editarNombre;
    				input.setEnabled(editarNombre);
    				
    				if (contadorClicksNombre == 2) {
    					System.out.println("cambiado: " + input.getText());
    					contadorClicksNombre = 0;
    				}
    			}        	
            });
		} else if (labelText.equalsIgnoreCase("Email")) {
        	iconLabel.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				super.mouseClicked(e);
    				contadorClicksEmail++;
    				editarEmail = !editarEmail;
    				input.setEnabled(editarEmail);
    				
    				if (contadorClicksEmail == 2) {
    					System.out.println("cambiado: " + input.getText());
    					contadorClicksEmail = 0;
    				}
    			}        	
            });
		} else {
			iconLabel.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mouseClicked(MouseEvent e) {
    				super.mouseClicked(e);
    				contadorClicksPassword++;
    				editarPassword = !editarPassword;
    				input.setEnabled(editarPassword);
    				
    				if (contadorClicksPassword == 2) {
    					System.out.println("cambiado: " + input.getText());
    					contadorClicksPassword = 0;
    				}
    			}        	
            });
		}
        

        panelInput.add(input);
        panelInput.add(iconLabel);

        // Agregar los subpaneles al panel principal
        panelContenido.add(panelLabel);
        panelContenido.add(panelInput);

        // Agregar el panel principal al panel central con alineación a la izquierda
        panelContenidoCentrado.add(panelContenido);
        
        return panelContenidoCentrado;
	}

	public static void main(String[] args) {
		VentanaInformacionUsuario viu = new VentanaInformacionUsuario(null);
	}

}
