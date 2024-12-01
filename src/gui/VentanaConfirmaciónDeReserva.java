package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import BiblioTech.Cliente;
import BiblioTech.Libro;
import BiblioTech.Seccion;

public class VentanaConfirmaciónDeReserva extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5490640345084381273L;
	public VentanaConfirmaciónDeReserva(Libro libro) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("BiblioTech - Confirmación de reserva");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
		//panel header
		Header header = new Header(Seccion.BIBLIOTECA, new Cliente(), this);
		header.setBackground(Color.WHITE);
		//panel body
		JPanel body = new JPanel(new GridLayout(0,2));
		body.setBackground(Color.WHITE);
		
		//panel imagen
		JPanel panelimagenLibro= new JPanel();
		panelimagenLibro.setBackground(Color.WHITE);
		JLabel imagenDelLibro = new JLabel();
		imagenDelLibro.setPreferredSize(new Dimension(550,700));
		Image imagen = libro.getFoto().getImage().getScaledInstance(250, 400, Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imagen);
		imagenDelLibro.setIcon(imagenEscalada);
		panelimagenLibro.add(imagenDelLibro);
		panelimagenLibro.setBackground(Color.WHITE);
		body.add(panelimagenLibro);
		
		//panel texto
		JPanel panelTexto = new JPanel();
		panelTexto.setBackground(Color.WHITE);
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
		JLabel labelTitulo = new JLabel(libro.getTitulo());
		Font fuente = new Font("ARIAL",Font.BOLD, 32);
		labelTitulo.setFont(fuente);
		
		
		List<JTextArea> areas = new ArrayList<>();

		JTextArea taAutor = new JTextArea("Autor(a): " + libro.getAutor());
		JTextArea taGenero = new JTextArea("Género: " + libro.getGenero());
		JTextArea taNumeroPaginas = new JTextArea("Número de páginas: " + libro.getNumeroDePaginas());
		JTextArea taRating = new JTextArea("Rating: " + libro.getRating() + "/10");
		JTextArea taSinopsis = new JTextArea("Sinopsis: " + libro.getSinopsis());
		
		
		areas.add(taAutor);
		areas.add(taGenero);
		areas.add(taNumeroPaginas);
		areas.add(taRating); // TODO: ?
		areas.add(taSinopsis);

		for (JTextArea ta : areas) {
			ta.setFont(new Font("Arial", Font.PLAIN, 18));
        	ta.setEditable(false);
 	       	ta.setLineWrap(true);
    	    ta.setBorder(null);
        	ta.setBorder(BorderFactory.createEmptyBorder());
     	   	ta.setWrapStyleWord(true);
        	ta.setBackground(Color.WHITE);
		}
		

		
        JScrollPane descripcionScrollPane = new JScrollPane(taSinopsis);
        descripcionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descripcionScrollPane.setPreferredSize(new Dimension(600, 500)); // Tamaño más grande para descripción
        descripcionScrollPane.setBorder(null);
        
        
        
        //panel boton
		JPanel botonesPanel = new JPanel(new GridBagLayout());
		JButton botonConfirmar = new JButton("Confirmar reservar");
		botonesPanel.setPreferredSize(new Dimension(100,100));
		botonesPanel.setBackground(Color.WHITE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
        
		botonConfirmar.setFont(new Font("Arial", Font.BOLD, 17));
		botonConfirmar.setPreferredSize(new Dimension(200, 50));
		
		botonConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				 
				ReservaConfirmada nuevaVentana = new ReservaConfirmada(libro, new Cliente());
				nuevaVentana.setVisible(true);
			}
		});
		
		add(header,BorderLayout.NORTH);
		add(body);
		
		body.add(labelTitulo, BorderLayout.NORTH);
        body.add(labelTitulo);
        body.add(panelTexto);
		body.add(panelimagenLibro, BorderLayout.EAST);
		body.add(panelTexto, BorderLayout.WEST);
		body.add(botonConfirmar);
		
		panelimagenLibro.setLayout(new GridBagLayout());
		panelimagenLibro.add(imagenDelLibro,gbc);
		
		panelTexto.add(labelTitulo,gbc);
		panelTexto.add(taAutor, BorderLayout.CENTER);
        panelTexto.add(taGenero, BorderLayout.CENTER);
        panelTexto.add(taNumeroPaginas, BorderLayout.CENTER);
        panelTexto.add(taRating, BorderLayout.CENTER);
		panelTexto.add(descripcionScrollPane,gbc);
		panelTexto.add(botonConfirmar, gbc);
		
		setVisible(true);
	} 
	public static void main(String[] args) {
		new VentanaConfirmaciónDeReserva(new Libro());
	}
}
