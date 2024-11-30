package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BiblioTech.Admin;
import BiblioTech.Cliente;
import BiblioTech.Evento;
import BiblioTech.Libro;
import BiblioTech.Review;
import BiblioTech.Sala;
import BiblioTech.SalaEventos;
import BiblioTech.SalaPrivada;
import BiblioTech.Seccion;
//import BiblioTech.SalaPrivada;
import BiblioTech.TipoEvento;
import BiblioTech.Usuario;
import utils.Utils;

public class InformacionRecurso extends JFrame {
	private JFrame vInformacionRecurso;
	private Evento evento;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1647556562163809896L;
	private JPanel pOeste, pEste, pSur, pCentro, pHeader;
	public void setMainWindowProperties(Seccion seccion, Usuario usuario) {
		
		vInformacionRecurso = this;
		
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(Color.WHITE);
		
		pCentro = new JPanel();
	    pSur = new JPanel();
	    pEste = new JPanel();
	    pOeste = new JPanel();
	    pHeader = new Header(seccion, usuario, this);
	  
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
		
	public InformacionRecurso(Libro libro, Usuario usuario) {
		if (usuario instanceof Admin) {
			InformacionRecursoAdmin nuevaVentana = new InformacionRecursoAdmin(libro,usuario);
			nuevaVentana.setVisible(true);
			vInformacionRecurso.dispose();
		}
		setMainWindowProperties(Seccion.BIBLIOTECA, usuario);
		setTitle ("BiblioTech - Harry Potter 1 (No Logueado)");
		//PANEL OESTE
		pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));
		pOeste.setBackground(Color.WHITE);
		//PANEL CENTRO
		pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
		pCentro.setBackground(Color.WHITE);
			
		//TAMAÑO DE PANELES SOUTH Y CENTRE
		pOeste.setPreferredSize(new Dimension(500, 0));
		pCentro.setPreferredSize(new Dimension(1000, 0));
		
		
		//PANEL DE IMAGEN DEL LIBRO
		JPanel panelimagenLibro= new JPanel();
		panelimagenLibro.setBackground(Color.WHITE);
		JLabel imagenDelLibro = new JLabel();
		imagenDelLibro.setPreferredSize(new Dimension(350,500));
		ImageIcon imagen = libro.getFoto(); 
		
		//Esacalar imagen 
		Image img = imagen.getImage();
		Image scaledImg = img.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
		imagenDelLibro.setIcon(new ImageIcon(scaledImg));
		
		imagenDelLibro.setIcon(imagen);
		panelimagenLibro.add(imagenDelLibro);
		pOeste.add(panelimagenLibro);
		
		//PANEL DE LA DESCRIPCIÓN DEL LIBRO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setPreferredSize(new Dimension(900, 50)); 
		panelTitulo.setBackground(Color.WHITE);
		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setBackground(Color.WHITE);
		JLabel tituloLibro = new JLabel(libro.getTitulo());
		tituloLibro.setFont(new Font("Arial", Font.BOLD, 24));
		JTextArea descripcionLibro = new JTextArea( "Autor: J.K. Rowling\r\n"
				+ "Género: Fantasía, Juvenil\r\n"
				+ "Temas: Magia, Amistad, Aventura, Identidad\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Harry Potter y la Piedra Filosofal es el inicio de una saga que ha cautivado a lectores de todas las edades en todo el mundo. En esta primera entrega, conocemos a Harry Potter, un niño huérfano que ha pasado una vida triste y sin amor en casa de sus crueles tíos. Todo cambia el día que recibe una misteriosa carta que revela que es, en realidad, un mago y que ha sido aceptado en el Colegio Hogwarts de Magia y Hechicería.\r\n"
				+ "\r\n"
				+ "A medida que Harry descubre este fascinante mundo de hechizos, criaturas mágicas y encantamientos, también se entera de un oscuro secreto sobre sus padres y el misterioso pasado que lo une a un temible mago, Voldemort. Con sus nuevos amigos, Hermione y Ron, Harry comienza una aventura que lo llevará a enfrentarse con desafíos sorprendentes y pruebas de valentía, a la vez que aprende sobre la amistad, la lealtad y el poder de la elección en medio de la adversidad.\r\n"
				+ "\r\n"
				+ "Esta novela es la puerta de entrada a un universo mágico lleno de detalles, personajes inolvidables y lecciones profundas que han convertido a la serie de Harry Potter en un fenómeno literario y cultural. Harry Potter y la Piedra Filosofal es una lectura ideal tanto para jóvenes que se adentran en el mundo de la fantasía como para adultos que buscan revivir el asombro de descubrir la magia por primera vez.");
		
			
		descripcionLibro.setFont(new Font("Arial", Font.PLAIN, 18));
        descripcionLibro.setEditable(false);
        descripcionLibro.setLineWrap(true);
        descripcionLibro.setBorder(null);
        descripcionLibro.setBorder(BorderFactory.createEmptyBorder());
        descripcionLibro.setWrapStyleWord(true);
        descripcionLibro.setBackground(Color.WHITE);
        
        
        // Agregar JTextArea en un JScrollPane
        JScrollPane descripcionScrollPane = new JScrollPane(descripcionLibro);
        descripcionScrollPane.setBackground(Color.WHITE);
        descripcionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descripcionScrollPane.setPreferredSize(new Dimension(700, 700)); // Tamaño más grande para descripción
        descripcionScrollPane.setBorder(null);
        
        // Añadir título y descripción al panel
        panelTitulo.add(tituloLibro, BorderLayout.NORTH);
        
        panelDescripcion.add(descripcionScrollPane, BorderLayout.CENTER);
        pCentro.add(panelTitulo);
        pCentro.add(panelDescripcion);
		
		
		//PANEL REVIEWS
		JPanel reviews = new JPanel();
		reviews.setLayout(new BoxLayout(reviews, BoxLayout.Y_AXIS));
		
		reviews.setBackground(Color.WHITE);
		
		JLabel tituloReviews = new JLabel ("Reviews");
		tituloReviews.setFont(new Font("Arial", Font.BOLD, 16));
		reviews.add(tituloReviews);
		
		JTextArea textoReviews = new JTextArea("Harry Potter y la piedra filosofal\" es el comienzo perfecto para una serie mágica que captura la imaginación de niños y adultos. La manera en la que Rowling introduce el mundo mágico a través de los ojos de un niño que no sabía que era especial es realmente entrañable. Este primer libro está lleno de aventuras, humor y amistad. ¡Imposible dejarlo hasta el final!\r\n"
				+ "— Marta G.\r\n"
				+ "\r\n"
				+ "Este libro no solo es emocionante y fácil de leer, sino que también aborda temas profundos como el valor, la lealtad y la amistad. La autora crea un mundo tan detallado que uno se pierde por completo en los pasillos de Hogwarts y las calles de Hogsmeade. Me encantó desde la primera página y lo recomiendo a cualquier persona que busque una historia inolvidable.\r\n"
				+ "— Luis R.\r\n"
				+ "\r\n"
				+ "Un clásico moderno que merece todos los elogios que ha recibido. Aunque está escrito para jóvenes, su narrativa cautiva a lectores de todas las edades. Harry es un personaje increíblemente humano y cercano, y los personajes secundarios, como Hermione y Ron, son entrañables y realistas. Este libro es solo la puerta de entrada a un universo que se expande y evoluciona en cada entrega.\r\n"
				+ "— Laura P.\r\n"
				+ "\r\n"
				+ "¡Increíble! La autora no solo creó un mundo completamente nuevo y único, sino que lo hizo tan detallado y rico en historia que parece real. \"La piedra filosofal\" es el libro perfecto para introducirse en el mundo de la magia, y está lleno de momentos de emoción, intriga y lecciones de vida. Recomiendo este libro a cualquiera que quiera sumergirse en una aventura mágica.\r\n"
				+ "— David M.\r\n"
				+ "\r\n"
				+ "Un libro que cambió la literatura juvenil para siempre. Lo leí de pequeño y me marcó, pero volverlo a leer ahora como adulto me hace darme cuenta de cuánto detalle y dedicación hay en cada página. Los personajes son inspiradores y la trama está llena de giros inesperados. Perfecto para leer en cualquier momento.\r\n"
				+ "— Elena S.");
		textoReviews.setFont(new Font("Arial", Font.PLAIN, 14));
		textoReviews.setEditable(false);
		textoReviews.setBackground(Color.WHITE);
        
		JScrollPane reviewsScrollPane = new JScrollPane(textoReviews);
        reviewsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        reviewsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reviewsScrollPane.setPreferredSize(new Dimension(200, 100)); // Ajusta el tamaño preferido según necesites
        reviewsScrollPane.setBorder(null);
        reviewsScrollPane.setPreferredSize(new Dimension(400, 150));

        reviews.add(reviewsScrollPane);
       
		pOeste.add(reviews);
		
		//PANEL BOTONES
		
		JPanel botonesPanel = new JPanel(new GridBagLayout());
		
		botonesPanel.setPreferredSize(new Dimension(100,100));
		botonesPanel.setBackground(Color.WHITE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
        
		JButton botonReview = new JButton("Añadir review");
		JButton botonReservar = new JButton("Reservar");
		botonReview.setFont(new Font("Arial", Font.BOLD, 17));
		botonReview.setPreferredSize(new Dimension(200, 50));
		botonReservar.setFont(new Font("Arial", Font.BOLD, 17));
		botonReservar.setPreferredSize(new Dimension(200, 50));
        
        if (!(usuario instanceof Cliente)) {
			botonReview.setEnabled(false);
			botonReservar.setEnabled(false);
		}
		botonesPanel.add(botonReview);
		botonesPanel.add(botonReservar);
       
		pCentro.add(botonesPanel);
		
		botonReview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres añadir una review?","Confirmar review", JOptionPane.YES_NO_OPTION);
                 
	             if (respuesta == JOptionPane.YES_OPTION) {
	            	 
	             }
			}
		});
		
		setVisible(true);
	}
	public InformacionRecurso(Sala sala, Usuario usuario) {
		if (usuario instanceof Admin) {
			InformacionRecursoAdmin nuevaVentana = new InformacionRecursoAdmin(sala,usuario);
			nuevaVentana.setVisible(true);
			vInformacionRecurso.dispose();
		}
		setMainWindowProperties(Seccion.SALAS_DE_ESTUDIO, usuario);
	    setTitle("Sala " + Integer.toString(sala.getId()) );
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BorderLayout());
	    panelPrincipal.setBackground(Color.WHITE);
	    
	    JLabel tituloSala = new JLabel("Sala " + Integer.toString(sala.getId()), SwingConstants.CENTER);
	    tituloSala.setFont(new Font("Arial", Font.BOLD, 40));
	    tituloSala.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
	    tituloSala.setVerticalAlignment(SwingConstants.CENTER);
	    tituloSala.setBackground(Color.WHITE);
	    panelPrincipal.add(tituloSala, BorderLayout.NORTH);

	    
	    String textoDescripcion = "La sala de estudio privada es un espacio diseñado especialmente para el enfoque y la productividad en un ambiente tranquilo y cómodo. Con una superficie de aproximadamente 20 metros cuadrados, la sala cuenta con un amplio escritorio de madera que permite organizar libros, cuadernos, y dispositivos electrónicos sin generar distracciones. La iluminación natural proviene de una gran ventana ubicada en la pared norte, ofreciendo una vista agradable sin perturbar la concentración. Además, se han instalado persianas ajustables para regular la luz según las necesidades del usuario, creando un ambiente adaptable tanto para el trabajo diurno como nocturno.\r\n"
                + "\r\n"
                + "El mobiliario incluye una silla ergonómica de alta calidad que proporciona soporte lumbar y ajustes en altura e inclinación, ideal para largas horas de estudio o trabajo. Para maximizar el confort y la salud postural, también cuenta con un reposapiés regulable. La sala está equipada con una pequeña estantería donde se pueden almacenar libros de referencia, material de oficina y objetos personales. Sobre el escritorio se encuentra una lámpara LED de bajo consumo con varios niveles de intensidad y modos de luz, pensada para reducir la fatiga visual.\r\n"
                + "\r\n"
                + "Para facilitar el aprendizaje visual y colaborativo, la sala dispone de una pizarra blanca de gran tamaño, acompañada de un set de marcadores en diferentes colores y un borrador magnético. En la pared opuesta, se ha instalado un proyector de alta resolución, ideal para revisar presentaciones o estudiar en grupo con proyecciones claras y nítidas. También cuenta con conectividad HDMI y adaptadores para varios dispositivos.\r\n"
                + "\r\n"
                + "Además, la sala está equipada con aire acondicionado y calefacción, permitiendo ajustar la temperatura en cualquier época del año. Se ha cuidado especialmente la insonorización del espacio, por lo que los ruidos externos son mínimos, lo que asegura un entorno silencioso y propicio para la concentración. Para la comodidad del usuario, hay una pequeña estación de café y té, así como un dispensador de agua fría y caliente.\r\n"
                + "\r\n"
                + "Por último, la sala de estudio cuenta con conexión Wi-Fi de alta velocidad, y dispone de múltiples enchufes y puertos USB alrededor del escritorio, facilitando la carga de dispositivos electrónicos. Este espacio privado ofrece todo lo necesario para maximizar la eficiencia y el confort, convirtiéndose en el lugar perfecto para realizar investigaciones, preparar exámenes o desarrollar proyectos personales sin interrupciones.";

        JTextArea descripcionSala = new JTextArea(textoDescripcion);
        descripcionSala.setFont(new Font("Arial", Font.PLAIN, 18));
        descripcionSala.setLineWrap(true); 
        descripcionSala.setWrapStyleWord(true); 
        descripcionSala.setEditable(false); 
        descripcionSala.setPreferredSize(new Dimension(700, 700));


        JScrollPane scrollPane = new JScrollPane(descripcionSala);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        // Añadir el JScrollPane al panel principal
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Añadir el panel principal al JFrame
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        
	    pOeste.add(panelPrincipal);
	    pEste.setLayout(new BorderLayout());
	    
	    JButton reservarButton = new JButton("Reservar");
	    reservarButton.setFont(new Font("Arial", Font.BOLD, 20));
        reservarButton.setPreferredSize(new Dimension(200, 50));
        
        reservarButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Cerrar la ventana actual
		        vInformacionRecurso.dispose();
		        // Abrir Venatana de ConfirmacionReserva
		        new VentanaConfirmaciónDeReserva();
		    }
		});
        
        pEste.setBorder(new EmptyBorder(0, 0, 10, 15));
        pOeste.setBorder(new EmptyBorder(0, 15, 10, 0));
	    pEste.add(reservarButton, BorderLayout.SOUTH);

	    // Asegúrate de que pEste esté agregado al JFrame
	    getContentPane().add(pEste, BorderLayout.EAST);

	    reservarButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Cerrar la ventana actual
		        vInformacionRecurso.dispose();
		        // Abrir Venatana de ConfirmacionReserva
		        new VentanaConfirmacionReservaSalaPrivada();
		    }
		});
	   
	    setVisible(true);
	}
    public InformacionRecurso(Evento evento, Usuario usuario) {
    	if (usuario instanceof Admin) {
			InformacionRecursoAdmin nuevaVentana = new InformacionRecursoAdmin(evento,usuario);
			nuevaVentana.setVisible(true);
			vInformacionRecurso.dispose();
		}
        this.evento= evento;
    	setMainWindowProperties(Seccion.EVENTOS, usuario);
    	setTitle("Evento " + evento.getTitulo() );
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BorderLayout());
	    panelPrincipal.setBackground(Color.WHITE);
	    
	    JLabel tituloEvento = new JLabel(evento.getTitulo(), SwingConstants.CENTER);
	    tituloEvento.setFont(new Font("Arial", Font.BOLD, 40));
	    tituloEvento.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
	    tituloEvento.setVerticalAlignment(SwingConstants.CENTER);
	    tituloEvento.setBackground(Color.WHITE);
	    panelPrincipal.add(tituloEvento, BorderLayout.NORTH);

	    
	    String textoOriginal = "Las Jornadas de Innovación Educativa tienen como objetivo reunir a docentes, investigadores y profesionales del ámbito educativo para compartir experiencias y buenas prácticas en la enseñanza. Este evento es una oportunidad para reflexionar sobre las metodologías educativas contemporáneas y su aplicación en el aula.";

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
    	
    	
    	BotonReservar();
    	
    
	    
		setVisible (true);
	}
//    private void addTextBlock(JPanel panel) {
//    	
//    	
//    	JPanel blockPanel = new JPanel();
//        blockPanel.setLayout(new BoxLayout(blockPanel, BoxLayout.Y_AXIS));
//
//        // Primera línea de texto
//        JLabel parrafoLabel = new JLabel();
//        parrafoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        
//        blockPanel.add(parrafoLabel);
//		
//	}
    
    private void BotonReservar() {
    	
 	    pEste.setLayout(new BorderLayout());
 	    
 	    JButton reservarButton = new JButton("Reservar");
 	    reservarButton.setFont(new Font("Arial", Font.BOLD, 20));
        reservarButton.setPreferredSize(new Dimension(200, 50));

 	    pEste.add(reservarButton, BorderLayout.SOUTH);
 	    pEste.setBorder(new EmptyBorder(0, 0, 10, 15));

 	    // Asegúrate de que pEste esté agregado al JFrame
 	    getContentPane().add(pEste, BorderLayout.EAST);
 	    
 	    reservarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Cerrar la ventana actual
		        vInformacionRecurso.dispose();
		        // Abrir Venatana de ConfirmacionReserva
		        new VentanaConfirmacionReservaEvento(evento);
		    }
 	    });
    }
	
	public static void main(String[] args) {
		ImageIcon foto = Utils.loadImage("books/9780006514855" + ".jpg", 350, 403);
		Libro libro = new Libro(0000000000000, "Libro 1", "Autor 1", 300, "Sinopsis", "Genero 1", 30, 2003, foto, new ArrayList<Review>());
		Evento evento = new Evento("Charla sobre la Comunicación", TipoEvento.CHARLA, new ArrayList<Cliente>(), null);
		SalaPrivada sala = new SalaPrivada(2, 110, 2, null, null);		
		
		new InformacionRecurso(libro, null);
		//new InformacionRecurso(evento, new Cliente());
		//new InformacionRecurso(sala, new Cliente());
		
	}

}
