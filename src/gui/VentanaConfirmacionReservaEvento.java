package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BiblioTech.Cliente;
import BiblioTech.Evento;
//import BiblioTech.Genero;
import BiblioTech.Libro;
//import BiblioTech.LibroLectura;
//import BiblioTech.LibroLectura;
import BiblioTech.Sala;
import BiblioTech.SalaPrivada;
import BiblioTech.Seccion;
//import BiblioTech.SalaPrivada;
import BiblioTech.TipoEvento;
import utils.Utils;
//import utils.Utils;

public class VentanaConfirmacionReservaEvento extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1647556562163809896L;
	private JPanel pOeste, pEste, pCentro, pSur, pHeader;
	
public void setMainWindowProperties() {
		
		
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(Color.WHITE);
		
		pCentro = new JPanel();
	    pSur = new JPanel();
	    pEste = new JPanel();
	    pOeste = new JPanel();
	    pHeader = new Header(Seccion.EVENTOS, new Cliente(), this);
	    
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
		
		
	}
	
	public VentanaConfirmacionReservaEvento (Evento evento) {
		
		setMainWindowProperties();
    	setTitle("BiblioTech - Confirmar reserva");
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BorderLayout());
	    panelPrincipal.setBackground(Color.WHITE);
	    
	    JLabel tituloEvento = new JLabel(evento.getTitulo(), SwingConstants.CENTER);
	    tituloEvento.setFont(new Font("Arial", Font.BOLD, 40));
	    tituloEvento.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
	    tituloEvento.setVerticalAlignment(SwingConstants.CENTER);
	    tituloEvento.setBackground(Color.WHITE);
	    panelPrincipal.add(tituloEvento, BorderLayout.NORTH);

	    
	    String textoOriginal = "Las Jornadas de Innovación Educativa tienen como objetivo reunir a docentes, investigadores y profesionales del ámbito educativo para compartir experiencias y buenas prácticas en la enseñanza. "
	    		+ "Este evento es una oportunidad para reflexionar sobre las metodologías educativas contemporáneas y su aplicación en el aula.";

        JTextArea descripcionEvento = new JTextArea(textoOriginal);
        descripcionEvento.setFont(new Font("Arial", Font.PLAIN, 18));
        descripcionEvento.setLineWrap(true); 
        descripcionEvento.setWrapStyleWord(true); 
        descripcionEvento.setEditable(false); 
        descripcionEvento.setPreferredSize(new Dimension(700, 700));

        JScrollPane scrollPane = new JScrollPane(descripcionEvento);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        // Añadir el JScrollPane al panel principal
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Añadir el panel principal al JFrame
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        
        pOeste.setBorder(new EmptyBorder(0, 15, 10, 0));
	    pOeste.add(panelPrincipal);
	    BotonConfirmarReservar();
    
		setVisible (true);
	}
	private void BotonConfirmarReservar() {
    	
 	    pEste.setLayout(new BorderLayout());
 	    
 	    JButton confirmarReservaButton = new JButton("Confirmar reserva");
 	   confirmarReservaButton.setFont(new Font("Arial", Font.BOLD, 20));
 	  confirmarReservaButton.setPreferredSize(new Dimension(250, 50));

 	    pEste.add(confirmarReservaButton, BorderLayout.SOUTH);
 	    pEste.setBorder(new EmptyBorder(0, 0, 10, 15));

 	    // Asegúrate de que pEste esté agregado al JFrame
 	    getContentPane().add(pEste, BorderLayout.EAST);
 	    
 	   confirmarReservaButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Cerrar la ventana actual
	        dispose();
	        // Abrir Venatana de ConfirmacionReserva
	        Evento evento = new Evento("Charla sobre la Innovación Educativa", TipoEvento.CHARLA, null, null); 
            new ReservaConfirmada(evento);
		}   
 	   });
    }
	public static void main(String[] args) {	
		Evento evento = new Evento("Charla sobre la Comunicación", TipoEvento.CHARLA, null, null);
		
		new VentanaConfirmacionReservaEvento(evento);

		
	}
}
