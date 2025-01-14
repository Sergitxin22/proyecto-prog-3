package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dbmejorada.AsistenciaEventoDTO;
import dbmejorada.SalaDTO;
import dbmejorada.UsuarioDTO;
import domain.Cliente;
import domain.Evento;
import domain.SalaEventos;
import domain.Seccion;
import domain.TipoEvento;
import domain.Usuario;
import gui.components.Header;
import main.Main;

import java.time.LocalDate;
//import utils.Utils;
import java.time.LocalDateTime;

public class VentanaConfirmacionReservaEvento extends JFrame{
	
	private static final long serialVersionUID = 1647556562163809896L;
	private JPanel pOeste, pEste, pCentro, pSur, pHeader;
	private Usuario usuario = Main.getUsuario();
	
public void setMainWindowProperties() {
		
		
		setSize(1280, 720);
		setLocationRelativeTo(null);
		
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
	
	public VentanaConfirmacionReservaEvento(Evento evento) {
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		       	new VentanaInformacionRecurso(evento);
		       	dispose();
		       	}
			});
		
		setMainWindowProperties();
    	setTitle("Confirmar reserva del " + evento.getTitulo());
	    
	    /*JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    panelPrincipal.setBackground(Color.WHITE);
	    
	    JPanel panelTitulo = new JPanel();
	    panelTitulo.setBackground(Color.WHITE);
	    
	    JLabel tituloEvento = new JLabel(evento.getTitulo(), SwingConstants.CENTER);
	    tituloEvento.setFont(new Font("Arial", Font.BOLD, 40));
	 
	    tituloEvento.setBackground(Color.WHITE);
	    panelTitulo.add(tituloEvento, BorderLayout.NORTH);

	    
	    List<JTextArea> areas = new ArrayList<>();
	    
	    JTextArea taTipoEvento = new JTextArea("Tipo del Evento: " + (evento.getTipoEvento()));
		JTextArea taSala = new JTextArea("Sala: " + (evento.getSala().getId()));
		JTextArea taFecha = new JTextArea("Fecha: " + (evento.getFechaHora()).toString());

		areas.add(taTipoEvento);
		areas.add(taSala);
		areas.add(taFecha);

		for (JTextArea area : areas) {
			area.setFont(new Font("Arial", Font.BOLD, 38));
 	        area.setLineWrap(true); 
    	    area.setWrapStyleWord(true); 
        	area.setEditable(false); 
		}
		
		panelPrincipal.add(panelTitulo);
        panelPrincipal.add(taTipoEvento, BorderLayout.CENTER);
		panelPrincipal.add(taSala, BorderLayout.CENTER);
		panelPrincipal.add(taFecha, BorderLayout.CENTER);

	    

        // Añadir el panel principal al JFrame
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        
        pOeste.setBorder(new EmptyBorder(0, 0, 10, 0));
	    pOeste.add(panelPrincipal);
	    BotonConfirmarReservar(evento);
    
		setVisible (true);
	}
	
	private void BotonConfirmarReservar(Evento evento) {
    	
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
	        
            new VentanaReservaConfirmada(evento);
		}   
 	   });
 	   */
    	JPanel panelPrincipal = new JPanel();
    	panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
 	    panelPrincipal.setBackground(Color.WHITE);
 	    
 	    JLabel tituloEvento = new JLabel( evento.getTitulo(), SwingConstants.CENTER);
 	    tituloEvento.setFont(new Font("Arial", Font.BOLD, 40));
 	    tituloEvento.setAlignmentX(CENTER_ALIGNMENT);
 	    tituloEvento.setBackground(Color.WHITE);
 	    panelPrincipal.add(tituloEvento, BorderLayout.NORTH);
 	    
 	    
 	    List<JTextArea> areas = new ArrayList<>();
 		//int id, String titulo, TipoEvento tipoEvento, ArrayList<Cliente> asistentes, SalaDTO sala, LocalDateTime fechaHora


         JTextArea taTipoEvento = new JTextArea("Tipo: " + evento.getTipoEvento());
         JTextArea taSala = new JTextArea("Numero de sala: " + Integer.toString(evento.getSala().getId()));
         JTextArea taFecha = new JTextArea("Fecha: " + evento.getFechaHora().getDayOfMonth() + "/" + evento.getFechaHora().getMonthValue() + "/" + evento.getFechaHora().getYear());
         JTextArea taHora = new JTextArea("Hora: " + evento.getFechaHora().getHour() + ":" + evento.getFechaHora().getMinute());
 		


 		areas.add(taTipoEvento);
 		areas.add(taSala);
 		areas.add(taFecha);
 		areas.add(taHora);
 		

 		for (JTextArea area : areas) {
 			area.setFont(new Font("Arial", Font.BOLD, 16));
  	        area.setLineWrap(true); 
     	    area.setWrapStyleWord(true); 
         	area.setEditable(false); 
 		}

 		panelPrincipal.add(taTipoEvento, BorderLayout.CENTER);
 		panelPrincipal.add(taSala, BorderLayout.CENTER);
 		panelPrincipal.add(taFecha, BorderLayout.CENTER);
 		panelPrincipal.add(taHora, BorderLayout.CENTER);
 		
 	    pOeste.add(panelPrincipal);
 	    
 	   pCentro.setLayout(new BorderLayout());
	    //Panel Boton
	    JPanel panelboton = new JPanel();
	    panelboton.setBackground(Color.WHITE);
	    JButton reservarButton = new JButton("Confirmar reserva");
		if (usuario == null) {
			reservarButton.setEnabled(false);
		}
	    reservarButton.setFont(new Font("Arial", Font.BOLD, 20));
	    panelboton.add(reservarButton);
	    
       
       pCentro.setBorder(new EmptyBorder(160, 100, 30, 15));
       pOeste.setBorder(new EmptyBorder(140, 15, 10, 0));
	    pCentro.add(panelboton, BorderLayout.SOUTH);

	    // Asegúrate de que pCentrpesté agregado al JFrame
	    
	    if(Main.getAsistenciaEventoDAO().isUsuarioAsistente(usuario.getDni())) {
	    	reservarButton.setEnabled(false);
	    	reservarButton.setToolTipText("Ya estás asistiendo a este evento");
	    }
	    reservarButton.addActionListener(e -> {
	    

	    	if(evento.getAsistentes().size() < evento.getSala().getCapacidad()) {
	    		evento.getAsistentes().add(new UsuarioDTO(usuario.getDni(), usuario.getNombre(), usuario.getEmail(), usuario.getFechaCreacion(), ((Cliente) usuario).getAmonestaciones(), false));
	    		AsistenciaEventoDTO asistenciaEventoDTO = new AsistenciaEventoDTO(0,usuario.getDni(), evento.getId());
	    		if(Main.getAsistenciaEventoDAO().addAsistenciaEvento(asistenciaEventoDTO)) {
	    			JOptionPane.showMessageDialog(this, "Asitencia confirmada correctamente.",  "Asistencia confirmada", JOptionPane.INFORMATION_MESSAGE);
	    	        dispose();
	    	        new VentanaInformacionRecurso(evento);
	    		}
	    	} else {
	    		JOptionPane.showMessageDialog(this, "No quedan asientos disponibles para este evento.", "Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    	
	
	    }); 
	    
	    setVisible(true);
	}
}
