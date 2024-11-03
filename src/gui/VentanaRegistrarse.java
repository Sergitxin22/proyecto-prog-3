package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaRegistrarse extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2621741612069651140L;
	
	public VentanaRegistrarse() {
		setTitle("Regístrate");
		setSize (600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Texto superior
		JLabel topText = new JLabel("Regístrate", SwingConstants.CENTER);
		topText.setFont(new Font("Verdana", Font.BOLD, 32));
		topText.setBorder(new EmptyBorder(20, 0, 20, 0));
				
		// Cuerpo de la ventana
		JPanel body = new JPanel(new GridLayout(8, 1, 0, 0));
		body.setBorder(new EmptyBorder(50, 100, 0, 100));
				
		JLabel textNombre= new JLabel("Nombre", SwingConstants.CENTER);
		textNombre.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel textEmail = new JLabel("Email", SwingConstants.CENTER);
		textEmail.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel textContrasena = new JLabel("Contraseña", SwingConstants.CENTER);
		textContrasena.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel textRepetirContrasenia = new JLabel("Repetir contraseña", SwingConstants.CENTER);
		textRepetirContrasenia.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
			
		
		JTextField tfNombre = new JTextField();
		JTextField tfUsuarioEmail = new JTextField();
		JTextField tfContrasena = new JTextField();
		JTextField tfRepetirContrasenia = new JTextField();
		
		tfNombre.setPreferredSize(new Dimension(125, 25));
		tfUsuarioEmail.setPreferredSize(new Dimension(125, 25));
		tfContrasena.setPreferredSize(new Dimension(125, 25));
		tfRepetirContrasenia.setPreferredSize(new Dimension(125, 25));
		
		
		JPanel tfNombrePanel = new JPanel();
		tfNombrePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfUsuarioEmailPanel = new JPanel();
		tfUsuarioEmailPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfContrasenaPanel = new JPanel();
		tfContrasenaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfRepetirContraseniaPanel = new JPanel();
		tfRepetirContraseniaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
		tfNombrePanel.add(tfNombre);
		tfUsuarioEmailPanel.add(tfUsuarioEmail);
		tfContrasenaPanel.add(tfContrasena);
		tfRepetirContraseniaPanel.add(tfRepetirContrasenia);

		textContrasena.setForeground(Color.black);
		
		body.add(textNombre);
		body.add(tfNombrePanel);
		body.add(textEmail);
		body.add(tfUsuarioEmailPanel);
		body.add(textContrasena);
		body.add(tfContrasenaPanel);
		body.add(textRepetirContrasenia);
		body.add(tfRepetirContraseniaPanel);
		
				
		body.setPreferredSize(new Dimension(20, 20));
		
		
		// Parte baja de la pantalla
		JButton registrarseButton = new JButton("Registrarse");
		JLabel yaCuentaLabel = new JLabel("¿Ya tienes cuenta?");
		yaCuentaLabel.setForeground(Color.blue);
		
		JPanel tail = new JPanel(new GridLayout(2, 1, 0, 0));
		tail.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		JPanel registrarseButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		registrarseButtonPanel.add(registrarseButton);
		
		JPanel yaCuentaLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		yaCuentaLabelPanel.add(yaCuentaLabel);
		
		tail.add(registrarseButtonPanel);
		tail.add(yaCuentaLabelPanel);		
		
		
		add(topText, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		add(tail, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaRegistrarse();
	}
}

	        
	        
	        
	        
	      
	     