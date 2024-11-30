package gui;

import BiblioTech.Admin;
import BiblioTech.Evento;
import BiblioTech.SalaEventos;
import BiblioTech.Seccion;
import BiblioTech.TipoEvento;
import BiblioTech.Usuario;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import utils.AddPanel;

// BASADO EN EL CÓDIGO DE VentanaBiblioteca

public class VentanaEventos extends JFrame { // TODO: Falta la funcionalidad de añadir evento

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public VentanaEventos(Usuario usuario, List<Evento> eventos) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
		if (usuario == null) {
			setTitle("Bibliotech - No logueado");			
		} else {			
			setTitle("Bibliotech - logueado" + usuario.getClass().toString());
		}
		
		// Header
        JPanel panelSuperior = new Header(Seccion.EVENTOS, usuario);
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel central
		JPanel panelContenido = new JPanel(new BorderLayout());
		JPanel subPanelContenido1 = new JPanel(new BorderLayout());
		panelContenido.add(subPanelContenido1, BorderLayout.NORTH);
	
		// Añadir evento (solo para Admins)
		if (usuario instanceof Admin) {
			JPanel panelAddLibro = new AddPanel(Seccion.EVENTOS);
	        subPanelContenido1.add(panelAddLibro, BorderLayout.WEST);
		}		
		
		Font buttonFont = new JButton().getFont();
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(0, 4, 5, 5));
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
				new InformacionRecurso(eventos.get(j));
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

		// Datos de prueba
		
		List<Evento> eventos = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			Evento evento = new Evento("Evento sobre literatura", TipoEvento.CHARLA, new ArrayList<>(), null);
			SalaEventos sala = new SalaEventos(evento);

			eventos.add(new Evento("Evento sobre IA", TipoEvento.CHARLA, new ArrayList<>(), sala));
		}

		for (int i = 1; i <= 20; i++) {
			Evento evento = new Evento("Evento sobre literatura", TipoEvento.CHARLA, new ArrayList<>(), null);
			SalaEventos sala = new SalaEventos(evento);

			evento.setSala(sala);
			

			eventos.add(evento);
			
		}
		new VentanaEventos(new Admin(), eventos);
	}
}

