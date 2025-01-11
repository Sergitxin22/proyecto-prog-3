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
import javax.swing.border.EmptyBorder;

import domain.Cliente;
import domain.Libro;
import domain.Seccion;
import domain.Usuario;
import gui.components.Header;
import main.Main;

public class VentanaConfirmacionDeReserva extends JFrame {
	
	private JPanel pOeste, pEste, pSur, pCentro, pHeader;
	private Usuario usuario = Main.getUsuario();

	private static final long serialVersionUID = -5490640345084381273L;
	
	public VentanaConfirmacionDeReserva(Libro libro) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Confirmación de reserva");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
		
		getContentPane().setBackground(Color.WHITE);
		
		pCentro = new JPanel();
	    pSur = new JPanel();
	    pEste = new JPanel();
	    pOeste = new JPanel();
	    pHeader = new Header(Seccion.BIBLIOTECA, usuario, this);
	  
	    pCentro.setBackground(Color.WHITE);
        pSur.setBackground(Color.WHITE);
        pHeader.setBackground(Color.WHITE);
        pOeste.setBackground(Color.WHITE);
        pEste.setBackground(Color.WHITE);
        
	    getContentPane().add(pCentro, BorderLayout.CENTER);
	    getContentPane().add(pHeader, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
		pCentro.setBackground(Color.WHITE);
		
		//panel header
		Header header = new Header(Seccion.BIBLIOTECA, new Cliente(), this);
		header.setBackground(Color.WHITE);
		
		//panel de imagen
		
		JPanel panelimagenLibro= new JPanel();
		panelimagenLibro.setBackground(Color.WHITE);
		panelimagenLibro.setAlignmentX(CENTER_ALIGNMENT);
		panelimagenLibro.setBorder(new EmptyBorder (0,65,0,0));
		JLabel imagenDelLibro = new JLabel();
		imagenDelLibro.setPreferredSize(new Dimension(275,500));
		Image imagen = libro.getFoto().getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imagen);
		imagenDelLibro.setIcon(imagenEscalada);
		imagenDelLibro.setAlignmentX(CENTER_ALIGNMENT);
		
		panelimagenLibro.add(imagenDelLibro);
		pOeste.add(panelimagenLibro);	
		
		//panel texto
		JPanel panelTexto = new JPanel();
		panelTexto.setBackground(Color.WHITE);
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
		JLabel labelTitulo = new JLabel(libro.getTitulo());
		Font fuente = new Font("ARIAL",Font.BOLD, 32);
		labelTitulo.setFont(fuente);
		labelTitulo.setAlignmentX(CENTER_ALIGNMENT);
		panelTexto.add(labelTitulo);
		panelTexto.setBorder(new EmptyBorder(60,0,0,85));
		
		
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
        
        pCentro.add(panelTexto);
        pCentro.add(taAutor);
        pCentro.add(taGenero);
        pCentro.add(taNumeroPaginas);
        pCentro.add(taRating);
        pCentro.add(taSinopsis);
        
        //panel boton
		JPanel botonesPanel = new JPanel();
		JButton botonConfirmar = new JButton("Confirmar reserva");
		JButton botonVolver = new JButton ("Volver");

		botonesPanel.setBackground(Color.WHITE);
        botonesPanel.add(botonVolver);
        botonesPanel.add(botonConfirmar);
		botonConfirmar.setFont(new Font("Arial", Font.BOLD, 17));
		botonVolver.setFont(new Font("Arial", Font.BOLD, 17));
		botonesPanel.setBorder(new EmptyBorder(0,0,50,0));
		
		pCentro.add(botonesPanel);
		
		botonConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				 
				VentanaReservaConfirmada nuevaVentana = new VentanaReservaConfirmada(libro);
				nuevaVentana.setVisible(true);
			}
		});
		
		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInformacionRecurso nuevaVentana = new VentanaInformacionRecurso(libro);
				nuevaVentana.setVisible(true);
				dispose();
			}
			
		});
		
		add(header,BorderLayout.NORTH);
		/*add(body, BorderLayout.CENTER);
		
		body.add(labelTitulo, BorderLayout.NORTH);
        body.add(labelTitulo);
        body.add(panelTexto);
		body.add(panelimagenLibro, BorderLayout.EAST);
		body.add(panelTexto, BorderLayout.WEST);
		
		panelimagenLibro.add(imagenDelLibro);
		
		panelTexto.add(labelTitulo);
		panelTexto.add(taAutor, BorderLayout.CENTER);
        panelTexto.add(taGenero, BorderLayout.CENTER);
        panelTexto.add(taNumeroPaginas, BorderLayout.CENTER);
        panelTexto.add(taRating, BorderLayout.CENTER);
		panelTexto.add(descripcionScrollPane);
		panelTexto.add(botonesPanel);
		*/
		setVisible(true);
	} 
	public static void main(String[] args) {
		new VentanaConfirmacionDeReserva(new Libro());
	}
}
