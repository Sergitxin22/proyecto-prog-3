package gui;

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
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.sqlite.SQLiteException;

import domain.Cliente;
import domain.Libro;
import domain.Review;
import main.Main;

public class VentanaRegistrarse extends JFrame {
	
	private static final long serialVersionUID = 2621741612069651140L;
	
	public VentanaRegistrarse(JFrame previousWindow) {
		setTitle("Regístrate");
		setSize (600, 600);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				previousWindow.setVisible(true);
				dispose();
			}
		});

		// Texto superior
		JLabel topText = new JLabel("Regístrate", SwingConstants.CENTER);
		topText.setFont(new Font("Verdana", Font.BOLD, 32));
		topText.setBorder(new EmptyBorder(20, 0, 20, 0));
				
		// Cuerpo de la ventana
		JPanel body = new JPanel(new GridLayout(8, 1, 0, 0));
		body.setBorder(new EmptyBorder(50, 100, 0, 100));
		
		JLabel textDNI= new JLabel("DNI", SwingConstants.CENTER);
		textDNI.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));

		JLabel textNombre= new JLabel("Nombre", SwingConstants.CENTER);
		textNombre.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel textEmail = new JLabel("Email", SwingConstants.CENTER);
		textEmail.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel textContrasena = new JLabel("Contraseña", SwingConstants.CENTER);
		textContrasena.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel textRepetirContrasenia = new JLabel("Repetir contraseña", SwingConstants.CENTER);
		textRepetirContrasenia.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
			
		JTextField tfDNI = new JTextField();
		JTextField tfNombre = new JTextField();
		JTextField tfUsuarioEmail = new JTextField();
		JPasswordField tfContrasena = new JPasswordField();
		JPasswordField tfRepetirContrasenia = new JPasswordField();
		
		tfDNI.setPreferredSize(new Dimension(125, 25));
		tfNombre.setPreferredSize(new Dimension(125, 25));
		tfUsuarioEmail.setPreferredSize(new Dimension(125, 25));
		tfContrasena.setPreferredSize(new Dimension(125, 25));
		tfRepetirContrasenia.setPreferredSize(new Dimension(125, 25));
		
		JPanel tfDNIPanel = new JPanel();
		tfDNIPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfNombrePanel = new JPanel();
		tfNombrePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfUsuarioEmailPanel = new JPanel();
		tfUsuarioEmailPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfContrasenaPanel = new JPanel();
		tfContrasenaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel tfRepetirContrasenaPanel = new JPanel();
		tfRepetirContrasenaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
		tfDNIPanel.add(tfDNI);
		tfNombrePanel.add(tfNombre);
		tfUsuarioEmailPanel.add(tfUsuarioEmail);
		tfContrasenaPanel.add(tfContrasena);
		tfRepetirContrasenaPanel.add(tfRepetirContrasenia);

		textContrasena.setForeground(Color.black);
		
		body.add(textDNI);
		body.add(tfDNIPanel);
		body.add(textNombre);
		body.add(tfNombrePanel);
		body.add(textEmail);
		body.add(tfUsuarioEmailPanel);
		body.add(textContrasena);
		body.add(tfContrasenaPanel);
		body.add(textRepetirContrasenia);
		body.add(tfRepetirContrasenaPanel);
		
				
		body.setPreferredSize(new Dimension(20, 20));
		
		
		// Parte baja de la pantalla
		JButton registrarseButton = new JButton("Registrarse");
		registrarseButton.addActionListener(e -> {
			if (!new String(tfContrasena.getPassword()).equals(new String(tfRepetirContrasenia.getPassword()))) {
				JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				tfContrasena.setText("");
				tfRepetirContrasenia.setText("");
				
			} else {
				Cliente nuevoUsuario = new Cliente(tfDNI.getText(), tfNombre.getText(), tfUsuarioEmail.getText(), LocalDate.now(), new String(tfContrasena.getPassword()), new ArrayList<Libro>(), new ArrayList<Review>(), 0);
				if (!Main.getUsuarioDAO().addUsuario(nuevoUsuario)) {
					JOptionPane.showMessageDialog(this, "Ya hay un usuario/a registrado con este DNI", "Error", JOptionPane.ERROR_MESSAGE);
					tfDNI.setText("");
					tfNombre.setText("");
					tfUsuarioEmail.setText("");
					tfContrasena.setText("");
					tfRepetirContrasenia.setText("");
				} else {
					Main.setUsuario(nuevoUsuario);
					try {						
						previousWindow.getClass().getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
						e1.printStackTrace();
					}
					
					dispose();
				}
			}		
		});

		JLabel yaCuentaLabel = new JLabel("¿Ya tienes cuenta?");
		yaCuentaLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new VentanaIniciarSesion(previousWindow);
						dispose();
                    }	
		});
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
		new VentanaRegistrarse(null);
	}
} 