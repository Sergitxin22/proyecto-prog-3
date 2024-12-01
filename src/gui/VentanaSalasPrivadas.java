package gui;

import BiblioTech.Admin;
import BiblioTech.Sala;
import BiblioTech.SalaPrivada;
import BiblioTech.Seccion;
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
import utils.Utils;


// BASADO EN EL CÓDIGO DE VentanaBiblioteca


public class VentanaSalasPrivadas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SalaPrivada> salas;

	public VentanaSalasPrivadas(Usuario usuario) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);

		ArrayList<Sala> salasTotales = Utils.cargarSalas();
		ArrayList<SalaPrivada> salasPrivadas = new ArrayList<>();

		for (Sala sala : salasTotales) {
			if (sala instanceof SalaPrivada) {
				salasPrivadas.add((SalaPrivada) sala);
			}
		}

		salas = salasPrivadas;
		
		if (usuario == null) {
			setTitle("Bibliotech - No logueado");			
		} else {			
			setTitle("Bibliotech - logueado" + usuario.getClass().toString());
		}
		
		// Header
        JPanel panelSuperior = new Header(Seccion.SALAS_DE_ESTUDIO, usuario, this);
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel central
		JPanel panelContenido = new JPanel(new BorderLayout());
		JPanel subPanelContenido1 = new JPanel(new BorderLayout());
		panelContenido.add(subPanelContenido1, BorderLayout.NORTH);
	
		// Añadir sala (solo para Admins)
		if (usuario instanceof Admin) {
			JPanel panelAddLibro = new AddPanel(Seccion.SALAS_DE_ESTUDIO);
	        subPanelContenido1.add(panelAddLibro, BorderLayout.WEST);
		}		
		
		Font buttonFont = new JButton().getFont();
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(0, 2, 5, 5));
		for (int i = 0; i < salas.size(); i++) {
			// Labels para el botón
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(2, 1));
			JLabel salaLabel = new JLabel("Sala privada " + salas.get(i).getId(), SwingConstants.CENTER);
			salaLabel.setFont(buttonFont.deriveFont(Font.BOLD, 25));
			JLabel recursosLabel = new JLabel("Recursos: " + ((SalaPrivada)salas.get(i)).getRecursos().toString(), SwingConstants.CENTER);
		
			
			buttonPanel.add(salaLabel);
			buttonPanel.add(recursosLabel);
			buttonPanel.setOpaque(false);
			
			JButton jButton = new JButton();
			final int j = i;
			jButton.addActionListener(e -> {
				new InformacionRecurso(salas.get(j));
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
		new VentanaSalasPrivadas(new Admin());
	}
}
