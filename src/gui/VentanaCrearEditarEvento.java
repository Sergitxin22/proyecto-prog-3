package gui;

import domain.Evento;
import domain.SalaEventos;
import domain.TipoEvento;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class VentanaCrearEvento extends JFrame {

	private static final long serialVersionUID = -3996159568028430335L;
		
		private NumberFormat format = NumberFormat.getIntegerInstance();
	    private NumberFormatter numberFormatter = new NumberFormatter(format);
	    
		private int id;
		private String titulo;
		private TipoEvento tipoEvento = null;
		private int idSala;
		private LocalDateTime fechaHora;
		
		private JFormattedTextField tfId = new JFormattedTextField(numberFormatter);
		private JTextField tfTitulo = new JTextField();
		private JFormattedTextField tfSala = new JFormattedTextField(numberFormatter);
		private JFormattedTextField tfFecha;
		private JFormattedTextField tfHora = new JFormattedTextField(numberFormatter);
		
		private JRadioButton charlaRB = new JRadioButton();
		private JRadioButton debateRB = new JRadioButton();
		private JRadioButton seminarioRB = new JRadioButton();
		private JRadioButton cursilloRB = new JRadioButton();
		private JRadioButton tallerRB = new JRadioButton();
		private JRadioButton conferenciaRB = new JRadioButton();
		
		ButtonGroup bg = new ButtonGroup();
		
		public VentanaCrearEvento() {
			setTitle("Crear evento");
			setSize(650, 500);
			setLocationRelativeTo(null);
			
			addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) {
		        	new VentanaEventos();
		        	dispose();
		        	}
				});
			
			MaskFormatter fechaMask;
			try {
				fechaMask = new MaskFormatter("####-##-##");
				fechaMask.setPlaceholderCharacter('_');
				tfFecha = new JFormattedTextField(fechaMask);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			MaskFormatter horaMask;
			try {
				horaMask = new MaskFormatter("##:##");
				horaMask.setPlaceholderCharacter('_');
				tfHora = new JFormattedTextField(horaMask);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		
			
			format.setGroupingUsed(false);
			numberFormatter.setAllowsInvalid(false);
		    numberFormatter.setMinimum(0);
		    
		    bg.add(charlaRB);
		    bg.add(debateRB);
		    bg.add(seminarioRB);
		    bg.add(cursilloRB);
		    bg.add(tallerRB);
		    bg.add(conferenciaRB);
		    
		    charlaRB.setText("Charla");
		    debateRB.setText("Debate");
		    seminarioRB.setText("Seminario");
		    cursilloRB.setText("Cursillo");
		    tallerRB.setText("Taller");
		    conferenciaRB.setText("Conferencia");
			
			// Texto superior
			JLabel topText = new JLabel("Crear evento", SwingConstants.CENTER); // Label con texto centrado
			topText.setFont(new Font("Verdana", Font.BOLD, 32));
				
				// Cuerpo de la ventana
			JPanel body = new JPanel();
			body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
			
			JLabel textId = new JLabel("ID");
			textId.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
			JLabel textTitulo = new JLabel("Título");
			textTitulo.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
			JLabel textNumeroSala = new JLabel("Número de sala");
			textNumeroSala.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
			JLabel textTipoEvento = new JLabel("Tipo de evento");
			textTipoEvento.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
			JLabel textFechaHora = new JLabel("Fecha y hora");
			textFechaHora.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
			
			
			tfId.setPreferredSize(new Dimension(125, 25));
			tfTitulo.setPreferredSize(new Dimension(125, 25));
			tfSala.setPreferredSize(new Dimension(125, 25));
			tfFecha.setPreferredSize(new Dimension(125, 25));
			tfHora.setPreferredSize(new Dimension(125, 25));
			
			JPanel tfIdPanel = new JPanel();
			JPanel tfTituloPanel = new JPanel();
			JPanel tfSalaPanel = new JPanel();
			JPanel tfFechaPanel = new JPanel();
			JPanel tipoEventoPanel = new JPanel();
			JPanel tfHoraPanel = new JPanel();
			
			textId.setAlignmentX(CENTER_ALIGNMENT);
			tfId.setAlignmentX(CENTER_ALIGNMENT);		
			textTitulo.setAlignmentX(CENTER_ALIGNMENT);
			tfTitulo.setAlignmentX(CENTER_ALIGNMENT);
			textNumeroSala.setAlignmentX(CENTER_ALIGNMENT);
			tfSala.setAlignmentX(CENTER_ALIGNMENT);	
			textTipoEvento.setAlignmentX(CENTER_ALIGNMENT);
			textFechaHora.setAlignmentX(CENTER_ALIGNMENT);
			tfHoraPanel.setAlignmentX(CENTER_ALIGNMENT);
			
			tipoEventoPanel.add(charlaRB);
			tipoEventoPanel.add(debateRB);
			tipoEventoPanel.add(seminarioRB);
			tipoEventoPanel.add(cursilloRB);
			tipoEventoPanel.add(tallerRB);
			tipoEventoPanel.add(conferenciaRB);
			
			tfIdPanel.add(tfId);
			tfTituloPanel.add(tfTitulo);
			tfSalaPanel.add(tfSala);
			tfFechaPanel.add(tfFecha);
			tfHoraPanel.add(tfHora);
						
			body.add(textId);
			body.add(tfIdPanel);
			body.add(textTitulo);
			body.add(tfTituloPanel);
			body.add(textNumeroSala);
			body.add(tfSalaPanel);
			body.add(textFechaHora);
			body.add(tfFechaPanel);
			body.add(tfHoraPanel);
			body.add(textTipoEvento);
			body.add(tipoEventoPanel);
		
			JButton crearEventoButton = new JButton("Crear evento");
			crearEventoButton.addActionListener(e -> {
				try {
					id = Integer.parseInt(tfId.getText());
					titulo = tfTitulo.getText();
					idSala = Integer.parseInt(tfSala.getText());
					String fechaHoraString = tfFecha.getText() + "T" + tfHora.getText() + ":00.00";
					fechaHora = LocalDateTime.parse(fechaHoraString);
					
					if (charlaRB.isSelected()) {
						tipoEvento = TipoEvento.CHARLA;
					} else if (debateRB.isSelected()) {
						tipoEvento = TipoEvento.DEBATE;
					} else if (seminarioRB.isSelected()) {
						tipoEvento = TipoEvento.SEMINARIO;
					} else if (cursilloRB.isSelected()) {
						tipoEvento = TipoEvento.CURSILLO;
					} else if (tallerRB.isSelected()) {
						tipoEvento = TipoEvento.TALLER;
					} else if (conferenciaRB.isSelected()) {
						tipoEvento = TipoEvento.CONFERENCIA;
					} else {
						JOptionPane.showMessageDialog(this, "Selecciona un tipo de evento", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					if (tipoEvento != null) {
						Evento evento = new Evento(id, titulo, tipoEvento, new ArrayList<>(), new SalaEventos(Main.getSalaDAO().getSala(idSala)), fechaHora);
						System.out.println(evento);
						if (Main.getEventoDAO().addEvento(evento)) {
							// LogAdmin
							JOptionPane.showMessageDialog(null, "Evento creado correctamente.", "Evento creado", JOptionPane.INFORMATION_MESSAGE);
							new VentanaEventos();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos. Comprueba los datos.", "Evento creado", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Compruébalos.", "Error", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
				
			});
			
			JPanel tail = new JPanel(new GridLayout(2, 1, 0, 0));
			
			JPanel añadirLibroButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			añadirLibroButtonPanel.add(crearEventoButton);
			
			tail.add(añadirLibroButtonPanel);
		
			add(topText, BorderLayout.NORTH);
			add(body, BorderLayout.CENTER);
			add(tail, BorderLayout.SOUTH);
		
			setVisible(true);
	}
		
	public static void main(String[] args) {
		new VentanaCrearEvento();
	}
}
