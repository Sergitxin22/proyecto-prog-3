package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class VentanaConfirmacionReservaSalaPrivada extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaConfirmacionReservaSalaPrivada() {
		setTitle("BiblioTech - Confirmar reserva");
		setSize(640, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//panel contenedor
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new GridLayout(1,3));
		
		//panelGeneral
		JPanel general = new JPanel();
		general.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE; // Alinea los elementos uno debajo del otro
		gbc.fill = GridBagConstraints.CENTER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(5, 0, 5, 0);
		
		
		// Texto superior
		JPanel titulo = new JPanel();
		JLabel topText = new JLabel("Definir reserva"); // Label con texto centrado
		titulo.setLayout(new FlowLayout(FlowLayout.CENTER));
		topText.setFont(new Font("Verdana", Font.BOLD, 32));
		titulo.add(topText);
		
		//panel fecha
        JPanel panelFecha = getPanel("Fecha:", "");
        
        //panel hora de entrada
      	JPanel panelHoraEntrada = getPanel("Hora de Entrada:", "");
        
      	//panel hora de salida
      	JPanel panelHoraSalida = getPanel("Hora de Salida:", "");
        
        //panel boton
        JPanel panelBoton = new JPanel();
        JButton botonConfirmar = new JButton("Confirmar reserva");  
        panelBoton.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(botonConfirmar);
		
        add(titulo,BorderLayout.NORTH);
        add(contenedor);
        
        contenedor.add(new JPanel());//l1
        contenedor.add(general);//l2
        contenedor.add(new JPanel());//l3
        
        general.add(panelFecha,gbc);
        general.add(panelHoraEntrada,gbc);
        general.add(panelHoraSalida,gbc);
        general.add(panelBoton,gbc);
      
		setVisible(true);
	}
	
	private JPanel getPanel(String labelText, String placeholder) {
		// Crear un panel con FlowLayout alineado a la izquierda
        JPanel panelContenidoCentrado = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Panel principal que contiene el nombre y se organiza en Y_AXIS
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        // Crear el JLabel "Nombre" con alineación a la izquierda
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel para el JLabel, también alineado a la izquierda
        JPanel panelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLabel.add(label);

        // Panel para el JTextField y el icono, alineados a la izquierda
        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField input = (JTextField) getJTextField(labelText);
        //input.setEnabled(false);
        
        panelInput.add(input);
        
        // Agregar los subpaneles al panel principal
        panelContenido.add(panelLabel);
        panelContenido.add(panelInput);

        // Agregar el panel principal al panel central con alineación a la izquierda
        panelContenidoCentrado.add(panelContenido);
        
        return panelContenidoCentrado;
	}
	
	private JComponent getJTextField(String labelText) {
		if (labelText.equalsIgnoreCase("Fecha:")) {
			MaskFormatter formateadorMascara = null;
	        try {
	           formateadorMascara = new MaskFormatter("##/##/####") {

	                /**
				 * 
				 */
				private static final long serialVersionUID = 4241922993006748419L;

					// este método convierte la cadena de texto que introduce el usuario
	                // en un objeto Date que será devuelto por el campo de texto cuando
	                // se llame al método getValue
	                @Override
	                public Object stringToValue(String text) throws ParseException {

	                    if (text == null || text.length() == 0) {
	                        return null;
	                    }
	                    // Validar que el texto que introduce el usuario tenga 10 caracteres
	                    if (text.length() != 10) {
	                        throw new ParseException("Formato dd/MM/yyyy", 0);
	                    }

	                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                    sdf.setLenient(false); // no admite fechas inválidas
	                    return sdf.parse(text);
	                }

	                // este método convierte el objeto Date que maneja el campo de texto
	                // en una cadena con el formato dd/MM/yyyy
	                @Override
	                public String valueToString(Object value) throws ParseException {
	                    if (value == null || !(value instanceof Date)) {
	                        return super.valueToString(value);
	                    }
	                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                    return sdf.format(value);
	                }

	            };
	        } catch (ParseException e) {
	            System.out.println("Error en el formateador de fecha");
	            e.printStackTrace();
	        }

	        formateadorMascara.setPlaceholderCharacter('_'); // caracter de relleno
	        formateadorMascara.setAllowsInvalid(false); // no admite caracteres inválidos
	        formateadorMascara.setOverwriteMode(true); // sobreescribe el texto si es inválido

	        JFormattedTextField txtFieldFecha = new JFormattedTextField(formateadorMascara);
	        txtFieldFecha.setColumns(15);
	        
	        return txtFieldFecha;
			
		} else {
			return new JTextField("", 15);
		}
		
	}
	
	public static void main(String[] args) {
		new VentanaConfirmacionReservaSalaPrivada();
	}

}
