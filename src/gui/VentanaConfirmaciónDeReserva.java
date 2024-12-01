package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BiblioTech.Cliente;
import BiblioTech.Seccion;
import utils.Utils;

public class VentanaConfirmaciónDeReserva extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5490640345084381273L;
	public VentanaConfirmaciónDeReserva(Libro libro) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("BiblioTech - Confirmación de reserva");
		setSize(640,480);
		setLocationRelativeTo(null);
		
		//panel header
		Header header = new Header(Seccion.BIBLIOTECA, new Cliente());
		
		//panel body
		JPanel body = new JPanel(new GridLayout(0,2));
		
		//panel imagen
		JPanel panelImagen = new JPanel();
		ImageIcon imagenLibro = Utils.loadImage("books/9780006545866.jpg", 150, 216);
		JLabel labelImagen = new JLabel(imagenLibro);
		
		//panel texto
		JPanel panelTexto = new JPanel();
		JLabel labelTitulo = new JLabel(libro.getTitulo());
		Font fuente = new Font("ARIAL",Font.BOLD, 32);
		labelTitulo.setFont(fuente);
		JTextArea descripcionLibro = new JTextArea(libro.getSinopsis());
				+ "A second row of text with a web link<br>"
				+ "An icon inline with text.<br>"
				+ "A paragraph of text with an unassigned link.<br>"
				+ "A second row of text with a web link<br>"
				+ "An icon inline with text.<br><br></html>");
		JButton botonConfirmar = new JButton("Confirmar reservar");
		
		botonConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				 
				ReservaConfirmada nuevaVentana = new ReservaConfirmada(libro, new Cliente());
				nuevaVentana.setVisible(true);
			}
		});
		
		add(header,BorderLayout.NORTH);
		add(body);
		
		body.add(panelImagen, BorderLayout.EAST);
		body.add(panelTexto, BorderLayout.WEST);
		
		panelImagen.setLayout(new GridBagLayout());
		panelImagen.add(labelImagen);
		
		panelTexto.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE; // Alinea los elementos uno debajo del otro
		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(5, 0, 5, 0);
		panelTexto.add(labelTitulo,gbc);
		panelTexto.add(labelDescripcionLibro,gbc);
		panelTexto.add(botonConfirmar,gbc);
		
		setVisible(true);
	} 
	public static void main(String[] args) {
		VentanaConfirmaciónDeReserva ventana = new VentanaConfirmaciónDeReserva(new Libro());
	}
}
