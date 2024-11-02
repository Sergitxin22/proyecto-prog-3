package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
        JPanel panelFecha = new JPanel();
        JLabel fecha = new JLabel("Fecha:");
        JTextField textoFecha = new JTextField();
        textoFecha.setColumns(10);
        panelFecha.add(fecha);
        panelFecha.add(textoFecha);
        
        //panel hora de entrada
      	JPanel panelHoraEntrada = new JPanel();
        JLabel horaEntrada = new JLabel("Hora de Entrada:");
        JTextField textoHoraEntrada = new JTextField();
        textoHoraEntrada.setColumns(10);
        panelHoraEntrada.add(horaEntrada);
        panelHoraEntrada.add(textoHoraEntrada);
        
      	//panel hora de salida
      	JPanel panelHoraSalida = new JPanel();
        JLabel horaSalida = new JLabel("Hora de Salida:");
        JTextField textoHoraSalida = new JTextField();
        textoHoraSalida.setColumns(10);
        panelHoraSalida.add(horaSalida);
        panelHoraSalida.add(textoHoraSalida);
        
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
		
	public static void main(String[] args) {
		VentanaConfirmacionReservaSalaPrivada ventana = new VentanaConfirmacionReservaSalaPrivada();
	}

}
