package gui;

import domain.Admin;
import domain.Seccion;
import domain.Usuario;
import gui.components.Header;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaCrearEvento extends JFrame{

	private static final long serialVersionUID = -3996159568028430335L;
	private Admin usuario = (Admin) Main.getUsuario();
	
	public VentanaCrearEvento() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Crear evento");
		setSize(1280,720);
		setLocationRelativeTo(null);
		
		
		Header header = new Header(Seccion.EVENTOS, usuario, this);
		JPanel panelContenido = new JPanel(new BorderLayout());
		JPanel tituloEvento = new JPanel();
		JTextField titulo = new JTextField("Titulo");
		JPanel body = new JPanel(new GridLayout(1,2));
		JPanel panelTexto = new JPanel();
//		panelTexto.setBackground(Color.WHITE);
		JTextArea descripcion = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec in risus sem. Suspendisse mauris magna, rutrum et risus id, aliquet hendrerit massa. Phasellus eu lobortis massa. Nullam scelerisque egestas mollis. Etiam in elit et ante dapibus dignissim ac ullamcorper ipsum. Proin finibus a est ac aliquet. Proin tincidunt nibh finibus pellentesque facilisis. Fusce ut sem elementum, suscipit risus sed, egestas eros.\r\n"
				+ "\r\n"
				+ "Fusce vitae turpis id nunc rhoncus sollicitudin cursus at nisl. Suspendisse tincidunt dapibus nunc a blandit. Aenean commodo tincidunt felis, vel finibus mi interdum eget. Proin scelerisque consequat odio, ut hendrerit erat facilisis a. Vestibulum viverra pretium dui, sed euismod ligula elementum sed. Etiam gravida ultricies ipsum, et maximus odio mollis ac. Etiam lacus turpis, pulvinar eu tincidunt sit amet, semper non magna. Mauris consequat mi nec mi aliquet efficitur. Mauris a dui lectus. Nam ipsum enim, dapibus interdum ornare nec, sollicitudin et elit. Mauris in lacus a lectus suscipit fermentum. Donec vitae dapibus massa. In ac volutpat nisi, eu porttitor dolor. Quisque pharetra odio eget urna vulputate vehicula. Sed consequat nibh et turpis ullamcorper, nec accumsan nibh fringilla. Nunc volutpat mi ipsum, a varius ex vestibulum in.\r\n"
				+ "\r\n"
				+ "Etiam ac tellus velit. Praesent nunc erat, bibendum sit amet turpis nec, blandit gravida nunc. Donec molestie nibh at leo consectetur sollicitudin. Integer tristique odio augue, laoreet tincidunt metus vehicula non. Curabitur eu ante faucibus, aliquet lectus iaculis, sagittis sapien. Vivamus velit mauris, pellentesque porttitor velit et, convallis tincidunt nisl. Nunc eu rutrum dolor, quis eleifend ligula.\r\n"
				+ "\r\n"
				+ "Etiam semper a dolor vel mollis. Ut faucibus finibus augue, egestas accumsan leo scelerisque eu. Sed ac ante id ante auctor convallis. Aenean euismod mattis justo. Proin mattis risus ac justo eleifend, nec varius dui elementum. Phasellus semper, magna quis vulputate hendrerit, justo nisi cursus dui, eget fermentum ex leo eu dolor. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\r\n"
				+ "\r\n"
				+ "Nunc pellentesque diam vel dolor tincidunt fermentum. Vivamus placerat diam scelerisque, lacinia sapien in, semper velit. Aliquam facilisis quam libero, vitae sodales sem congue a. Maecenas in rhoncus elit, maximus dapibus erat. Donec blandit ex enim, vitae ultricies elit tristique vel. In congue leo magna, egestas tristique justo sagittis ut. Nunc consectetur enim non libero ultricies, lobortis posuere nisi efficitur. Curabitur ut diam quis elit tempor ultrices ac non magna. Phasellus sed ex quis magna sagittis tincidunt at in metus. In eget hendrerit nunc. Morbi tristique consectetur mi, quis scelerisque lacus semper id. Duis lectus diam, dignissim et urna nec, commodo euismod elit. Quisque ultricies nisi dignissim aliquam eleifend. In euismod consectetur tincidunt.");
		JPanel panelBoton = new JPanel();
//		panelBoton.setBackground(Color.DARK_GRAY);
		JButton botonCrearEvento = new JButton("Crear evento");
		
		
		Font fuente = new Font("Arial", Font.BOLD, 40);
		titulo.setFont(fuente);
		Font fuenteBoton = new Font("Arial",Font.PLAIN,25);
		botonCrearEvento.setFont(fuenteBoton);
		
		descripcion.setFont(new Font("Arial", Font.PLAIN, 18));
		descripcion.setEditable(false);
		descripcion.setLineWrap(true);
		descripcion.setBorder(null);
		descripcion.setBorder(BorderFactory.createEmptyBorder());
		descripcion.setWrapStyleWord(true);
//		descripcion.setBackground(Color.WHITE);
		descripcion.setSize(620, 500);
        
		add(header,BorderLayout.NORTH);
		add(panelContenido);
		tituloEvento.add(titulo);
		panelContenido.add(tituloEvento, BorderLayout.NORTH);
		panelContenido.add(body);
		panelTexto.add(descripcion);
		body.add(panelTexto);
		panelBoton.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 1; // Columna
	        gbc.gridy = 1; // Fila
	        gbc.weightx = 1.0;
	        gbc.weighty = 1.0;
	        gbc.anchor = GridBagConstraints.SOUTHEAST; // Esquina inferior derecha
	        gbc.insets = new Insets(10, 10, 10, 10);
		panelBoton.add(botonCrearEvento,gbc);
		body.add(panelBoton);
		
		setVisible(true);
		
		if (usuario instanceof Admin){	
			System.out.println("usuarioAdmin");
		} else {
			System.out.println("OtroUsuario");
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new VentanaCrearEvento();
	}
}
