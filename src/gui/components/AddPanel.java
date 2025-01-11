package gui.components;

import domain.Evento;
import domain.Libro;
import domain.SalaPrivada;
import domain.Seccion;
import domain.Usuario;
import gui.VentanaCrearEditarLibro;
import gui.VentanaCrearEditarEvento;
import gui.VentanaCrearEditarSala;
import utils.Utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

		public AddPanel(JFrame window, Seccion seccion, Usuario usuario) {
		
		String entidad = "";
		
		if (seccion.equals(Seccion.BIBLIOTECA)) {
			entidad = "libro";
		} else if (seccion.equals(Seccion.SALAS_DE_ESTUDIO)) {
			entidad = "sala";
		} else {
			entidad = "evento";
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, -5, 0, 5); // Margen entre componentes (icono y texto)
	    gbc.anchor = GridBagConstraints.CENTER; // Centrar verticalmente y horizontalmente
	    
		ImageIcon iconoAddLibro = Utils.loadImage("add.png",36 ,36 );
	    JLabel iconLabel = new JLabel(iconoAddLibro);
	     
	    JLabel textLabel = new JLabel("Añadir " + entidad);
     
	    // Añadir mouse listener para el panel
	    addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
	        	if (seccion.equals(Seccion.BIBLIOTECA)) {
					new VentanaCrearEditarLibro(window, new Libro());
					window.dispose();
				}

				if (seccion.equals(Seccion.SALAS_DE_ESTUDIO)) {
					new VentanaCrearEditarSala(window, new SalaPrivada());
					window.dispose();
				}

				if (seccion.equals(Seccion.EVENTOS)) {
					new VentanaCrearEditarEvento(window, new Evento());
					window.dispose();
				}
        	}
	    });
	    
	    add(iconLabel, gbc);
	    gbc.gridx = 1; // Segunda columna
	    add(textLabel, gbc);
	}

}
