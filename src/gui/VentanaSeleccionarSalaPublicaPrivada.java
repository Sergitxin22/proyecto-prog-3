package gui;

import domain.Seccion;
import domain.Usuario;
import gui.components.Header;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Utils;

public class VentanaSeleccionarSalaPublicaPrivada extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();

	public VentanaSeleccionarSalaPublicaPrivada() {
		setTitle("Salas de estudio");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// PANEL SUPERIOR
		JPanel top = new Header(Seccion.SALAS_DE_ESTUDIO, usuario, this);
			
		// Imagen del libro
		JLabel salaPublicaIcon = new JLabel();
		salaPublicaIcon.setIcon(Utils.loadImage("salaPublica.png", 150, 150));
		
		// Imagen de los eventos
		JLabel salasPrivadasIcon = new JLabel();
		salasPrivadasIcon.setIcon(Utils.loadImage("salasPrivadas.png", 150, 150));	
		
		
		// PANEL CENTRAL
		Font buttonFont = new Font("Verdana", Font.BOLD, 15);
		Font titleFont = new Font("verdana", Font.BOLD, 24);
		
		JPanel mid = new JPanel();
		JButton salaPublicaButton = new JButton("Saber más");
		salaPublicaButton.addActionListener(e -> {
			new VentanaSalaPublica();
			dispose();
		});

		JButton salasPrivadasButton = new JButton("Reservar");
		salasPrivadasButton.addActionListener(e -> {
			new VentanaSalasPrivadas();
			dispose();
		});
		
		JPanel salaPublicaPanel = new JPanel();
		JPanel salasPrivadasPanel = new JPanel();

		// Asignación a GridLayout para poder poner los iconos encima de los botones
		salaPublicaPanel.setLayout(new BoxLayout(salaPublicaPanel, BoxLayout.Y_AXIS));
		salasPrivadasPanel.setLayout(new BoxLayout(salasPrivadasPanel, BoxLayout.Y_AXIS));
		
		JLabel salaPublicaTitle = new JLabel("Sala Pública");
		JLabel salaPublicaSubtitle = new JLabel("Macrosala disponible sin reserva previa");
		
		JLabel salasPrivadasTitle = new JLabel("Salas privadas");
		JLabel salasPrivadasSubtitle = new JLabel("Salas privadas para grupos de trabajo");
		
		salaPublicaTitle.setFont(titleFont);
		salaPublicaSubtitle.setFont(titleFont.deriveFont(Font.PLAIN, 16));
		
		salasPrivadasTitle.setFont(titleFont);
		salasPrivadasSubtitle.setFont(titleFont.deriveFont(Font.PLAIN, 16));
		
		
		salaPublicaButton.setFont(buttonFont);
		salasPrivadasButton.setFont(buttonFont);
			
		salaPublicaPanel.add(salaPublicaIcon);
		salaPublicaPanel.add(salaPublicaTitle);
		salaPublicaPanel.add(salaPublicaSubtitle);
		salaPublicaPanel.add(salaPublicaButton);
		
		salasPrivadasPanel.add(salasPrivadasIcon);
		salasPrivadasPanel.add(salasPrivadasTitle);
		salasPrivadasPanel.add(salasPrivadasSubtitle);
		salasPrivadasPanel.add(salasPrivadasButton);
		
		salaPublicaIcon.setAlignmentX(CENTER_ALIGNMENT);
		salasPrivadasIcon.setAlignmentX(CENTER_ALIGNMENT);
		salaPublicaTitle.setAlignmentX(CENTER_ALIGNMENT);
		salaPublicaSubtitle.setAlignmentX(CENTER_ALIGNMENT);
		salasPrivadasTitle.setAlignmentX(CENTER_ALIGNMENT);
		salasPrivadasSubtitle.setAlignmentX(CENTER_ALIGNMENT);
		salaPublicaButton.setAlignmentX(CENTER_ALIGNMENT);
		salasPrivadasButton.setAlignmentX(CENTER_ALIGNMENT);
		
		mid.add(salaPublicaPanel);
		mid.add(salasPrivadasPanel);
		
		// Bordes para añadir espaciado entre paneles
		salaPublicaPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		salasPrivadasPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		mid.setBorder(new EmptyBorder(150, 0, 100, 0));
		salaPublicaSubtitle.setBorder(new EmptyBorder(0, 0, 10, 0));
		salasPrivadasSubtitle.setBorder(new EmptyBorder(0, 0, 10, 0));
		salaPublicaIcon.setBorder(new EmptyBorder(0, 0, 10, 0));
		salasPrivadasIcon.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaSeleccionarSalaPublicaPrivada();
	}

}
