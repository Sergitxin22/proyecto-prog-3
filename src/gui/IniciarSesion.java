 package gui;

import BiblioTech.Cliente;
import BiblioTech.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class IniciarSesion extends JFrame {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IniciarSesion(JFrame previousWindow) {
		setTitle("Iniciar Sesión");
		setSize(650, 500);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        previousWindow.setVisible(true);
						dispose();
                    }
			
		});
		
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
		JPasswordField tfContrasena = new JPasswordField();
		
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
		iniciarSesionButton.addActionListener(e -> {
			// TODO: COMPROBACIÓN DE QUE EL USUARIO EXISTE
			previousWindow.dispose();
			Portada portada = new Portada((Usuario) new Cliente());
			dispose();

		});

		JLabel noCuentaLabel = new JLabel("¿No tienes cuenta?", SwingConstants.CENTER);
		noCuentaLabel.setForeground(Color.blue);
		noCuentaLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new VentanaRegistrarse();
						dispose();
                    }
			
		});
		
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
		new IniciarSesion(null);	
	}

}
