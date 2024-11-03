 package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
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

		
		// Cuerpo de la ventana
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
			
		JLabel textUsuarioEmail = new JLabel("Usuario/Email");
		textUsuarioEmail.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textContrasena = new JLabel("Contraseña");
		textContrasena.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JTextField tfUsuarioEmail = new JTextField();
		JTextField tfContrasena = new JTextField();
		
		tfUsuarioEmail.setPreferredSize(new Dimension(125, 25));
		tfContrasena.setPreferredSize(new Dimension(125, 25));
		
		JPanel tfUsuarioEmailPanel = new JPanel();
		JPanel tfContrasenaPanel = new JPanel();
	
		tfUsuarioEmailPanel.add(tfUsuarioEmail);
		tfContrasenaPanel.add(tfContrasena);

		
		textUsuarioEmail.setAlignmentX(CENTER_ALIGNMENT);
		textContrasena.setAlignmentX(CENTER_ALIGNMENT);
		tfUsuarioEmail.setAlignmentX(CENTER_ALIGNMENT);
		tfContrasena.setAlignmentX(CENTER_ALIGNMENT);
		
		body.add(textUsuarioEmail);
		body.add(tfUsuarioEmailPanel);
		body.add(textContrasena);
		body.add(tfContrasenaPanel);
		
		// Parte baja de la pantalla
		JButton iniciarSesionButton = new JButton("Iniciar sesión");
		JLabel noCuentaLabel = new JLabel("¿No tienes cuenta?", SwingConstants.CENTER);
		noCuentaLabel.setForeground(Color.blue);
		
		JPanel tail = new JPanel(new GridLayout(2, 1, 0, 0));
		
		
		JPanel iniciarSesionButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		iniciarSesionButtonPanel.add(iniciarSesionButton);
		
		JPanel noCuentaLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		noCuentaLabelPanel.add(noCuentaLabel);
		
		tail.add(iniciarSesionButtonPanel);
		tail.add(noCuentaLabelPanel);
		
		topText.setBorder(new EmptyBorder(20, 0, 20, 0));
		body.setBorder(new EmptyBorder(50, 100, 0, 100));
		tail.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		add(topText, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		add(tail, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new IniciarSesion();	
	}

}
