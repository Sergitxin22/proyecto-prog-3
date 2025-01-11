package gui;

import domain.Cliente;
import domain.Evento;
import domain.Libro;
import domain.SalaEventos;
import domain.SalaPrivada;
import domain.TipoEvento;
import domain.Usuario;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//import BiblioTech.TipoEvento;
//import utils.Utils;

import dbmejorada.SalaDTO;

public class VentanaReservaConfirmada extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();

	public void setMainWindowProperties() {
		// Método que recoge las propiedades en común de los constructores de esta ventana
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel confirmadaLabel = new JLabel("Reserva confirmada", SwingConstants.CENTER);
		confirmadaLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		
		confirmadaLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
		add(confirmadaLabel, BorderLayout.NORTH);
	}
	
	public VentanaReservaConfirmada(Libro libro) {
		setMainWindowProperties();
		setTitle(libro.getTitulo() + ": Reserva confirmada");
		
		// Panel donde se muestra la información del libro
		JPanel bookPanel = new JPanel();
		bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
		
		JLabel bookImageLabel = new JLabel();
		bookImageLabel.setIcon(libro.getFoto());
		
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
		
		JButton volverButton = new JButton("Volver");
		volverButton.addActionListener(e -> {
			new VentanaInformacionRecurso(libro);
			dispose();
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(volverButton);

		add(bookPanel, BorderLayout.WEST);
		add(reservaPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public VentanaReservaConfirmada(Evento evento) {
		setMainWindowProperties();
		setTitle(evento.getTitulo() + ": Reserva confirmada");
		
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
		JButton volverButton = new JButton("Volver");
		volverButton.addActionListener(e -> {
			new VentanaInformacionRecurso(evento);
			dispose();
		});
		
		buttonPanel.add(volverButton);

		add(reservaPanel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public VentanaReservaConfirmada(SalaPrivada sala) {
		setMainWindowProperties();
		setTitle("Sala " + sala.getId() + ": Reserva confirmada");
		
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
		JButton volverButton = new JButton("Volver");
		volverButton.addActionListener(e -> {
			new VentanaInformacionRecurso(sala);
			dispose();
		});

		buttonPanel.add(volverButton);
		
		add(reservaPanel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// RECURSOS DE PRUEBA
//		Libro libro = new Libro(0000000000000, "Libro 1", "Autor 1", 300, "Sinopsis", "Genero 1", 30, 2003, null, new ArrayList<Review>());	
		SalaDTO salaDTO = new SalaDTO();
		salaDTO.setCapacidad(100);
		salaDTO.setEvento(null);
		salaDTO.setId(124);
		salaDTO.setPiso(3);
		salaDTO.setRecursos(null);
		salaDTO.setTipo("Cursillo");
		Evento evento = new Evento(13, "Evento sobre agricultura", TipoEvento.CURSILLO, new ArrayList<Cliente> (),salaDTO, LocalDateTime.now());
//		SalaPrivada sala = new SalaPrivada(2, 110, 2, null, null);		
		
//		int id, String titulo, TipoEvento tipoEvento, ArrayList<Cliente> asistentes, SalaEventos sala, LocalDate fecha, int hora
//		new ReservaConfirmada(libro, new Cliente());
//		new ReservaConfirmada(sala, new Cliente());
		new VentanaReservaConfirmada(evento);
	}
}
