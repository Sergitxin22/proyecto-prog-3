package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import BiblioTech.Evento;
import BiblioTech.Genero;
import BiblioTech.Libro;
import BiblioTech.LibroLectura;
import BiblioTech.SalaPrivada;
import BiblioTech.TipoEvento;
import utils.Utils;

public class ReservaConfirmada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void setMainWindowProperties() {
		
		// Método que recoge las propiedades en común de los constructores de esta ventana
		
		setTitle("Reserva confirmada");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel confirmadaLabel = new JLabel("Reserva confirmada", SwingConstants.CENTER);
		confirmadaLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		
		confirmadaLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
		add(confirmadaLabel, BorderLayout.NORTH);
	}
	
	
	public ReservaConfirmada(Libro libro) {
		
		setMainWindowProperties();
		
		// Panel donde se muestra la información del libro
		JPanel bookPanel = new JPanel();
		bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
		
		JLabel bookImageLabel = new JLabel();
		bookImageLabel.setIcon(new ImageIcon(libro.getFoto()));
		
		JLabel bookTitleLabel = new JLabel(libro.getTitulo());
		bookTitleLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		// Panel donde se muestra la información de la reserva
		JPanel reservaPanel = new JPanel();
		reservaPanel.setLayout(new BoxLayout(reservaPanel, BoxLayout.Y_AXIS));
		
		int codigo = (int) (Math.random() * 1000) + 1;
		
		JLabel codigoReservaLabel = new JLabel("Código de reserva");
		JLabel codigoLabel = new JLabel(Integer.toString(codigo));
		
		reservaPanel.add(codigoReservaLabel);
		reservaPanel.add(codigoLabel);
		
		// Alineación al centro de los labels
		bookImageLabel.setAlignmentX(CENTER_ALIGNMENT);
		bookTitleLabel.setAlignmentX(CENTER_ALIGNMENT);
		codigoReservaLabel.setAlignmentX(CENTER_ALIGNMENT);
		codigoLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		bookPanel.add(bookImageLabel);
		bookPanel.add(bookTitleLabel);
		
		// Espaciado entre paneles
		codigoReservaLabel.setBorder(new EmptyBorder(0, 0, 70, 0));
		reservaPanel.setBorder(new EmptyBorder(70, 0, 0, 0));
		bookPanel.setBorder(new EmptyBorder(30, 100, 0, 0));
		
		add(bookPanel, BorderLayout.WEST);
		add(reservaPanel, BorderLayout.CENTER);
		add(new JButton("Volver"), BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public ReservaConfirmada(Evento evento) {
		
		setMainWindowProperties();
		
		JPanel reservaPanel = new JPanel();
		reservaPanel.setLayout(new BoxLayout(reservaPanel, BoxLayout.Y_AXIS));
		
		int codigo = (int) (Math.random() * 1000) + 1;
		JLabel eventTitleLabel = new JLabel(evento.getTitulo(), SwingConstants.CENTER);
		eventTitleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		JLabel codigoLabel = new JLabel("Código de reserva: " + Integer.toString(codigo), SwingConstants.CENTER);
		
		JPanel eventTitleLabelCenterPanel = new JPanel();
		JPanel codigoLabelCenterPanel = new JPanel();
		
		eventTitleLabelCenterPanel.add(eventTitleLabel);
		codigoLabelCenterPanel.add(codigoLabel);
		
		codigoLabel.setFont(eventTitleLabel.getFont());
		eventTitleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

		
		reservaPanel.add(eventTitleLabelCenterPanel);
		reservaPanel.add(codigoLabelCenterPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("Volver"));
		
		add(reservaPanel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public ReservaConfirmada(SalaPrivada sala) {
		setMainWindowProperties();
		JPanel reservaPanel = new JPanel();
		reservaPanel.setLayout(new BoxLayout(reservaPanel, BoxLayout.Y_AXIS));
		
		int codigo = (int) (Math.random() * 1000) + 1;
		JLabel eventTitleLabel = new JLabel("Número de sala: " + Integer.toString(sala.getId()), SwingConstants.CENTER);
		eventTitleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		JLabel codigoLabel = new JLabel("Código de reserva: " + Integer.toString(codigo), SwingConstants.CENTER);
		
		JPanel eventTitleLabelCenterPanel = new JPanel();
		JPanel codigoLabelCenterPanel = new JPanel();
		
		eventTitleLabelCenterPanel.add(eventTitleLabel);
		codigoLabelCenterPanel.add(codigoLabel);
		
		codigoLabel.setFont(eventTitleLabel.getFont());
		eventTitleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		
		reservaPanel.add(eventTitleLabelCenterPanel);
		reservaPanel.add(codigoLabelCenterPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("Volver"));
		
		add(reservaPanel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	
	public static void main(String[] args) {
		
		// RECURSOS DE PRUEBA
		
		Libro libro = new LibroLectura("Harry Potter I", "J.K. Rowling", 443, Utils.loadImage("ejemploLibro.jpg", 112, 182).getImage(), 1, "Harry va a Hogwarts y tal",
				null, Genero.FANTASIA, 2);
		Evento evento = new Evento("Charla sobre la Comunicación", TipoEvento.CHARLA, null, null);
		SalaPrivada sala = new SalaPrivada(2, 110, 2, null, null);		
				
		new ReservaConfirmada(sala);
		
	}
}
