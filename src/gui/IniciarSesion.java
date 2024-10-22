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

public class IniciarSesion extends JFrame {
	
	// TODO: Añadir subrayado al texto de "¿No tienes cuenta?" cuando el cursor pase por el label	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IniciarSesion() {
		setTitle("Iniciar Sesión");
		setSize(650, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Texto superior
		JLabel topText = new JLabel("Iniciar sesión", SwingConstants.CENTER); // Label con texto centrado
		topText.setFont(new Font("Verdana", Font.BOLD, 32));
		topText.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		// Cuerpo de la ventana
		JPanel body = new JPanel(new GridLayout(4, 1, 0, 0));
		body.setBorder(new EmptyBorder(50, 100, 0, 100));
		
		
		JLabel textUsuarioEmail = new JLabel("Usuario/Email");
		textUsuarioEmail.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textContrasena = new JLabel("Contraseña");
		textContrasena.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JTextField tfUsuarioEmail = new JTextField();
		JTextField tfContrasena = new JTextField();
		
		tfUsuarioEmail.setPreferredSize(new Dimension(125, 25));
		tfContrasena.setPreferredSize(new Dimension(125, 25));
		
		JPanel tfUsuarioEmailPanel = new JPanel();
		tfUsuarioEmailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel tfContrasenaPanel = new JPanel();
		tfContrasenaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	
		tfUsuarioEmailPanel.add(tfUsuarioEmail);
		tfContrasenaPanel.add(tfContrasena);
		
		textContrasena.setForeground(Color.black);
		
		body.add(textUsuarioEmail);
		body.add(tfUsuarioEmailPanel);
		body.add(textContrasena);
		body.add(tfContrasenaPanel);
		
		body.setPreferredSize(new Dimension(20, 20));
		
		
		// Parte baja de la pantalla
		JButton iniciarSesionButton = new JButton("Iniciar sesión");
		JLabel noCuentaLabel = new JLabel("¿No tienes cuenta?");
		noCuentaLabel.setForeground(Color.blue);
		
		JPanel tail = new JPanel(new GridLayout(2, 1, 0, 0));
		tail.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		JPanel iniciarSesionButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		iniciarSesionButtonPanel.add(iniciarSesionButton);
		
		JPanel noCuentaLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		noCuentaLabelPanel.add(noCuentaLabel);
		
		tail.add(iniciarSesionButtonPanel);
		tail.add(noCuentaLabelPanel);
		
		
		add(topText, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		add(tail, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		IniciarSesion ventanaIniciarSesion= new IniciarSesion();
		
		
	}

}
