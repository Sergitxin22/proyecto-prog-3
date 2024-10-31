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

	public void setMainWindowProperties() {
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
		
		JPanel bookPanel = new JPanel();
		bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
		JLabel bookImageLabel = new JLabel();
		bookImageLabel.setIcon(new ImageIcon(libro.getFoto()));
		JLabel bookLabel = new JLabel(libro.getTitulo(), SwingConstants.CENTER);
		JPanel titleCenterLabel = new JPanel();
		bookLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		titleCenterLabel.add(bookLabel);
		
		int codigo = (int) (Math.random() * 1000) + 1;
		JPanel reservaPanel = new JPanel();
		reservaPanel.setLayout(new BoxLayout(reservaPanel, BoxLayout.Y_AXIS));
		JLabel codigoReservaLabel = new JLabel("Código de reserva");
		codigoReservaLabel.setBorder(new EmptyBorder(0, 0, 50, 0));
		JLabel codigoLabel = new JLabel(Integer.toString(codigo), SwingConstants.CENTER);
		
		JPanel codigoCenterLabel = new JPanel();
		codigoCenterLabel.add(codigoLabel);
		
		
		reservaPanel.add(codigoReservaLabel);
		reservaPanel.add(codigoCenterLabel);
		
		bookPanel.add(bookImageLabel);
		bookPanel.add(titleCenterLabel);
		
		reservaPanel.setBorder(new EmptyBorder(100, 30, 30, 30));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton("Volver"));
		
		add(bookPanel, BorderLayout.WEST);
		add(reservaPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
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
		Libro libro = new LibroLectura("Harry Potter I", "J.K. Rowling", 443, Utils.loadImage("ejemploLibro.jpg", 225, 364).getImage(), 1, "Harry va a Hogwarts y tal",
				"Esto está mal", Genero.FANTASIA, 2);
		Evento evento = new Evento("Charla sobre la Comunicación", TipoEvento.CHARLA, null, null);
		SalaPrivada sala = new SalaPrivada(2, 110, 2, null, null);		
				
		new ReservaConfirmada(sala);
		
	}
}
