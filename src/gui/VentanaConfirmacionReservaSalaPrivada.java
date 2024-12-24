package gui;

import domain.Cliente;
import domain.SalaPrivada;
import domain.Usuario;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaConfirmacionReservaSalaPrivada extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();

	public VentanaConfirmacionReservaSalaPrivada(SalaPrivada sala) {
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
		JTextField tfFecha = new JTextField();
		tfFecha.setColumns(15);
		
		tfFechaPanel.add(tfFecha);

		JPanel horaEntradaPanel = new JPanel();
		horaEntradaPanel.setLayout(new BoxLayout(horaEntradaPanel, BoxLayout.Y_AXIS));
		JLabel horaEntradaLabel = new JLabel("Hora de entrada:");
		horaEntradaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel tfHoraEntradaPanel = new JPanel();
		JTextField tfHoraEntrada = new JTextField();
		tfHoraEntrada.setColumns(15);

		tfHoraEntradaPanel.add(tfHoraEntrada);


		JPanel horaSalidaPanel = new JPanel();
		horaSalidaPanel.setLayout(new BoxLayout(horaSalidaPanel, BoxLayout.Y_AXIS));
		JLabel horaSalidaLabel = new JLabel("Hora de salida:");
		horaSalidaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel tfHoraSalidaPanel = new JPanel();
		JTextField tfHoraSalida = new JTextField();
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
//			LocalDate fecha = LocalDate.parse(tfFecha.getText()); // TODO: cambiar el formato del textfield para fechas
//
//			int horaEntrada = Integer.parseInt(tfHoraEntrada.getText());
//			int horaSalida = Integer.parseInt(tfHoraSalida.getText());

			// TODO: Añadir la reserva a la base de datos
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
