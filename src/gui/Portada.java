package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Utils;

public class Portada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Portada() {
		
		setTitle("BiblioTech - Portada");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel top = new JPanel();
		JPanel topUsuario = new JPanel();
		JPanel topTitulo = new JPanel();
		JLabel bibliotechLabel = new JLabel("BiblioTech");
		
		
		// PROCESO DE ABRIR LAS IMAGENES Y ASIGNARLAS A SUS LABELS:
		// Imagen del usuario
		ImageIcon usuarioIcon = Utils.loadImage("usuario.png", 80, 80);
		JLabel usuarioLabel = new JLabel();
		usuarioLabel.setIcon(usuarioIcon);
	
		// Imagen del libro
		ImageIcon libroIcon = Utils.loadImage("libro.png", 150, 150);
		JLabel libroLabel = new JLabel();
		libroLabel.setIcon(libroIcon);
		
		// Imagen de las salas
		ImageIcon salasIcon = Utils.loadImage("salas.png", 150, 150);
		JLabel salasLabel = new JLabel();
		salasLabel.setIcon(salasIcon);
		
		// Imagen de los eventos
		ImageIcon eventosIcon = Utils.loadImage("eventos.png", 150, 150);
		JLabel eventosLabel = new JLabel();
		eventosLabel.setIcon(eventosIcon);	
		
		
		// PANEL SUPERIOR
		bibliotechLabel.setFont(new Font("Verdana", Font.PLAIN, 72));
		
		topUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT));
		topUsuario.add(usuarioLabel);
		topTitulo.add(bibliotechLabel);
		topTitulo.setBorder(new EmptyBorder(0, 500, 0, 450));
		
		top.add(topTitulo);
		top.add(topUsuario, BorderLayout.EAST);
		add(top, BorderLayout.NORTH);
		
		
		// PANEL CENTRAL
		Font buttonFont = new Font("Verdana", Font.BOLD, 15);
		
		JPanel mid = new JPanel();
		JButton bibliotecaButton = new JButton("Biblioteca");
		JButton salasButton = new JButton("Salas");
		JButton eventosButton = new JButton("Eventos");
		
		JPanel bibliotecaButtonPanel = new JPanel();
		JPanel salasButtonPanel = new JPanel();
		JPanel eventosButtonPanel = new JPanel();

		// Asignación a GridLayout para poder poner los iconos encima de los botones
		bibliotecaButtonPanel.setLayout(new GridLayout(2, 1));
		salasButtonPanel.setLayout(new GridLayout(2, 1));
		eventosButtonPanel.setLayout(new GridLayout(2, 1));
		
		
		bibliotecaButton.setFont(buttonFont);
		salasButton.setFont(buttonFont);
		eventosButton.setFont(buttonFont);
		
		
		// Paneles para poder ajustar los botones al tamaño de las imagenes
		JPanel bibliotecaAlignPanel = new JPanel();
		bibliotecaAlignPanel.add(bibliotecaButton);
		
		JPanel salasAlignPanel = new JPanel();
		salasAlignPanel.add(salasButton);
		
		JPanel eventosAlignPanel = new JPanel();
		eventosAlignPanel.add(eventosButton);
		
		bibliotecaButtonPanel.add(libroLabel);
		bibliotecaButtonPanel.add(bibliotecaAlignPanel);
		
		salasButtonPanel.add(salasLabel);
		salasButtonPanel.add(salasAlignPanel);
		
		eventosButtonPanel.add(eventosLabel);
		eventosButtonPanel.add(eventosAlignPanel);
		
		mid.add(bibliotecaButtonPanel);
		mid.add(salasButtonPanel);
		mid.add(eventosButtonPanel);
		
		
		// Bordes para añadir espaciado entre paneles
		bibliotecaButtonPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		salasButtonPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		eventosButtonPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		top.setBorder(new EmptyBorder(50, 50, 0, 0));
		mid.setBorder(new EmptyBorder(250, 0, 0, 0));
		add(mid, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
}
