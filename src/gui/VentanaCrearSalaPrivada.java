package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import domain.Recurso;
import domain.SalaPrivada;
import main.Main;

public class VentanaCrearSalaPrivada extends JFrame {

	private static final long serialVersionUID = -2023022345495844787L;
	
	private NumberFormat format = NumberFormat.getIntegerInstance();
    private NumberFormatter numberFormatter = new NumberFormatter(format);
    
	private int id;
	private int piso;
	private int capacidad;
	private ArrayList<Recurso> recursos = new ArrayList<>();
	
	private JFormattedTextField tfId = new JFormattedTextField(numberFormatter);
	private JFormattedTextField tfPiso = new JFormattedTextField(numberFormatter);
	private JFormattedTextField tfCapacidad = new JFormattedTextField(numberFormatter);
	
	private JCheckBox ordenadoresCB = new JCheckBox("Ordenadores");
	private JCheckBox proyectorCB = new JCheckBox("Proyector");
	private JCheckBox pizarraCB = new JCheckBox("Pizarra");
	
	public VentanaCrearSalaPrivada() {
		setTitle("Crear sala privada");
		setSize(650, 500);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	new VentanaSalasPrivadas();
	        	dispose();
	        	}
			});
		
		format.setGroupingUsed(false);
		numberFormatter.setAllowsInvalid(false);
	    numberFormatter.setMinimum(0);
		
		// Texto superior
		JLabel topText = new JLabel("Crear sala privada", SwingConstants.CENTER); // Label con texto centrado
		topText.setFont(new Font("Verdana", Font.BOLD, 32));
			
			// Cuerpo de la ventana
		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		JLabel textId = new JLabel("Número");
		textId.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textPiso = new JLabel("Piso");
		textPiso.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textCapacidad = new JLabel("Capacidad");
		textCapacidad.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		JLabel textRecursos = new JLabel("Recursos");
		textRecursos.setFont(topText.getFont().deriveFont(Font.PLAIN, 20));
		
		tfId.setPreferredSize(new Dimension(125, 25));
		tfPiso.setPreferredSize(new Dimension(125, 25));
		tfCapacidad.setPreferredSize(new Dimension(125, 25));
		
		JPanel tfIdPanel = new JPanel();
		JPanel tfPisoPanel = new JPanel();
		JPanel tfCapacidadPanel = new JPanel();
		JPanel tfRecursosPanel = new JPanel();
		
		textId.setAlignmentX(CENTER_ALIGNMENT);
		tfId.setAlignmentX(CENTER_ALIGNMENT);		
		textPiso.setAlignmentX(CENTER_ALIGNMENT);
		tfPiso.setAlignmentX(CENTER_ALIGNMENT);	
		textCapacidad.setAlignmentX(CENTER_ALIGNMENT);
		tfCapacidad.setAlignmentX(CENTER_ALIGNMENT);
		textRecursos.setAlignmentX(CENTER_ALIGNMENT);
		tfRecursosPanel.setAlignmentX(CENTER_ALIGNMENT);
	
		tfIdPanel.add(tfId);
		tfPisoPanel.add(tfPiso);
		tfCapacidadPanel.add(tfCapacidad);
		tfRecursosPanel.add(ordenadoresCB);
		tfRecursosPanel.add(proyectorCB);
		tfRecursosPanel.add(pizarraCB);
		
		body.add(textId);
		body.add(tfIdPanel);
		body.add(textPiso);
		body.add(tfPisoPanel);
		body.add(textCapacidad);
		body.add(tfCapacidadPanel);
		body.add(textRecursos);
		body.add(tfRecursosPanel);
		
	
	
		JButton crearSalaPrivadaButton = new JButton("Crear sala privada");
		crearSalaPrivadaButton.addActionListener(e -> {
			
			try {
				capacidad = Integer.parseInt(tfCapacidad.getText());
				id = Integer.parseInt(tfId.getText());
				piso = Integer.parseInt(tfPiso.getText());
				
				if (ordenadoresCB.isSelected()) {
					recursos.add(Recurso.ORDENADORES);
				}
				
				if (proyectorCB.isSelected()) {
					recursos.add(Recurso.PROYECTOR);
				}
				
				if (pizarraCB.isSelected()) {
					recursos.add(Recurso.PIZARRA);
				} 
				
				SalaPrivada salaPrivada = new SalaPrivada(capacidad, id, piso, recursos, new ArrayList<>());
				recursos = new ArrayList<Recurso>();
				
				if (Main.getSalaDAO().addSala(salaPrivada)) {
					// TODO: logAdmin
					JOptionPane.showMessageDialog(this, "Sala añadida correctamente.", "Sala añadida", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Error al añadir la sala. Comprueba los datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Error en la entrada de datos. Compruébalos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});
		
		JPanel tail = new JPanel(new GridLayout(2, 1, 0, 0));
		
		JPanel añadirLibroButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		añadirLibroButtonPanel.add(crearSalaPrivadaButton);
		
		tail.add(añadirLibroButtonPanel);
	
		add(topText, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		add(tail, BorderLayout.SOUTH);
	
		setVisible(true);
	}

	public static void main(String[] args) {
	new VentanaCrearSalaPrivada();
	
	}
}
