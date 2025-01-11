package gui;

import domain.SalaPrivada;
import domain.Usuario;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dbmejorada.ReservaSalaPrivadaDTO;


public class VentanaConfirmacionReservaSalaPrivada extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();
	private JFormattedTextField tfFecha;
	private JFormattedTextField tfHoraEntrada;
	private JFormattedTextField tfHoraSalida;
	public VentanaConfirmacionReservaSalaPrivada(SalaPrivada sala) {
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
			tfHoraEntrada = new JFormattedTextField(horaMask);
			tfHoraSalida = new JFormattedTextField(horaMask);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		setTitle("Reserva de Sala " + sala.getId());
		setSize(600, 600);
		setLocationRelativeTo(null);

		JPanel titleLabel = new JPanel();
		JLabel titulo = new JLabel("Confirmación de reserva");
		titulo.setFont(new Font("Verdana", Font.BOLD, 24));
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.add(titulo);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

		JPanel fechaPanel = new JPanel();
		fechaPanel.setLayout(new BoxLayout(fechaPanel, BoxLayout.Y_AXIS));
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel tfFechaPanel = new JPanel();
		
		tfFecha.setColumns(15);
		
		tfFechaPanel.add(tfFecha);

		JPanel horaEntradaPanel = new JPanel();
		horaEntradaPanel.setLayout(new BoxLayout(horaEntradaPanel, BoxLayout.Y_AXIS));
		JLabel horaEntradaLabel = new JLabel("Hora de entrada:");
		horaEntradaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel tfHoraEntradaPanel = new JPanel();
		
		tfHoraEntrada.setColumns(15);

		tfHoraEntradaPanel.add(tfHoraEntrada);


		JPanel horaSalidaPanel = new JPanel();
		horaSalidaPanel.setLayout(new BoxLayout(horaSalidaPanel, BoxLayout.Y_AXIS));
		JLabel horaSalidaLabel = new JLabel("Hora de salida:");
		horaSalidaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel tfHoraSalidaPanel = new JPanel();
		
		tfHoraSalida.setColumns(15);

		tfHoraSalidaPanel.add(tfHoraSalida);


		fechaPanel.add(fechaLabel);
		fechaPanel.add(tfFechaPanel);

		horaEntradaPanel.add(horaEntradaLabel);
		horaEntradaPanel.add(tfHoraEntradaPanel);

		horaSalidaPanel.add(horaSalidaLabel);
		horaSalidaPanel.add(tfHoraSalidaPanel);

		panelPrincipal.add(fechaPanel);
		panelPrincipal.add(horaEntradaPanel);
		panelPrincipal.add(horaSalidaPanel);

		JPanel buttonPanel = new JPanel();
		JButton confirmarButton = new JButton("Confirmar");
		buttonPanel.add(confirmarButton);
		confirmarButton.addActionListener(e -> {
			
			String fechaReserva = tfFecha.getText();
			ReservaSalaPrivadaDTO reserva = new ReservaSalaPrivadaDTO(0, LocalDateTime.parse(fechaReserva + "T" + tfHoraEntrada.getText()), LocalDateTime.parse(fechaReserva + "T" + tfHoraSalida.getText()), LocalDate.now(),usuario.getDni(), sala.getId());
			
			if(Main.getReservaSalaPrivadaDAO().isSalaPrivadaReservable(reserva)) {
				if(Main.getReservaSalaPrivadaDAO().addReservaSalaPrivada(reserva)) {
					JOptionPane.showMessageDialog(this, "Reserva realizada correctamente", "Reserva realizada", JOptionPane.INFORMATION_MESSAGE);
					new VentanaInformacionRecurso (sala);
					dispose();
				}else {
					JOptionPane.showMessageDialog(this, "Error al realizar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Esta sala se encuentra ocupada en este intervalo de horas", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});

		panelPrincipal.setBorder(new EmptyBorder(50, 0, 50, 0));
		add(titleLabel, BorderLayout.NORTH);
		add(panelPrincipal);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaConfirmacionReservaSalaPrivada(new SalaPrivada(5, 14, 2, new ArrayList<>(), new ArrayList<>()));
	}
}
