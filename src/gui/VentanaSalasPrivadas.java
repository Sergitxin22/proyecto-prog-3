package gui;

import domain.Admin;
import domain.Sala;
import domain.SalaPrivada;
import domain.Seccion;
import domain.Usuario;
import gui.components.AddPanel;
import gui.components.Header;
import io.InputUtils;
import main.Main;

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


// BASADO EN EL CÓDIGO DE VentanaBiblioteca


public class VentanaSalasPrivadas extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();

	private List<SalaPrivada> salas;

	public VentanaSalasPrivadas() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);

		ArrayList<Sala> salasTotales = InputUtils.cargarSalas();
		ArrayList<SalaPrivada> salasPrivadas = new ArrayList<>();

		for (Sala sala : salasTotales) {
			if (sala instanceof SalaPrivada) {
				salasPrivadas.add((SalaPrivada) sala);
			}
		}

		salas = salasPrivadas;
		
		if (usuario == null) {
			setTitle("Salas Privadas - No logueado");			
		} else {			
			setTitle("Salas Privadas - Logueado: " + usuario.getNombre());
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
			JPanel panelAddLibro = new AddPanel(this, Seccion.SALAS_DE_ESTUDIO, usuario);
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
				new VentanaInformacionRecurso(salas.get(j));
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
		new VentanaSalasPrivadas();
	}
}
