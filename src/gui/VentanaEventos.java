package gui;

import domain.Admin;
import domain.Evento;
import domain.Seccion;
import domain.Usuario;
import gui.components.AddPanel;
import gui.components.Header;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

// BASADO EN EL CÓDIGO DE VentanaBiblioteca

public class VentanaEventos extends JFrame {

	private static final long serialVersionUID = 1L;
	private List<Evento> eventos;
	private Usuario usuario = Main.getUsuario();

	public VentanaEventos() {

		eventos = Main.getEventoDAO().getEventos();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
		if (usuario == null) {
			setTitle("Eventos - No logueado");			
		} else {			
			setTitle("Eventos - Logueado: " + usuario.getNombre());
		}
		
		// Header
        JPanel panelSuperior = new Header(Seccion.EVENTOS, usuario, this);
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel central
		JPanel panelContenido = new JPanel(new BorderLayout());
		JPanel subPanelContenido1 = new JPanel(new BorderLayout());
		panelContenido.add(subPanelContenido1, BorderLayout.NORTH);
	
		// Añadir evento (solo para Admins)
		if (usuario instanceof Admin) {
			JPanel panelAddLibro = new AddPanel(this, Seccion.EVENTOS, usuario);
	        subPanelContenido1.add(panelAddLibro, BorderLayout.WEST);
		}		
		
		Font buttonFont = new JButton().getFont();
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(0, 2, 5, 5));
		for (int i = 0; i < eventos.size(); i++) {
			// Labels para el botón
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(2, 1));
			JLabel eventoLabel = new JLabel(eventos.get(i).getTitulo(), SwingConstants.CENTER);
			eventoLabel.setFont(buttonFont.deriveFont(Font.BOLD, 25));
		
			
			buttonPanel.add(eventoLabel);
			buttonPanel.setOpaque(false);
			
			JButton jButton = new JButton();
			final int j = i;
			jButton.addActionListener(e -> {
				new VentanaInformacionRecurso(eventos.get(j));
				dispose();
			});

			jButton.add(buttonPanel);
			subPanelContenido2.add(jButton);
		}
		
		subPanelContenido2.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margen exterior
		
		JScrollPane scrollBar = new JScrollPane(subPanelContenido2);
		panelContenido.add(scrollBar, BorderLayout.CENTER);

		add(panelContenido, BorderLayout.CENTER);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaEventos();
	}
}

