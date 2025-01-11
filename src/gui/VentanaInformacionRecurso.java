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

import dbmejorada.SalaDTO;
import dbmejorada.UsuarioDAO;
import dbmejorada.UsuarioDTO;
import domain.Admin;
import domain.Cliente;
import domain.Evento;
import domain.Libro;
import domain.Recurso;
import domain.Reserva;
import domain.Review;
import domain.Sala;
import domain.SalaEventos;
import domain.SalaPrivada;
import domain.Seccion;
import domain.TipoEvento;
import domain.Usuario;
import gui.components.Header;
import main.Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import utils.Utils;

public class VentanaInformacionRecurso extends JFrame {
	
	private JFrame vInformacionRecurso;
	private Evento evento;
	private static final long serialVersionUID = 1647556562163809896L;
	private JPanel pOeste, pEste, pSur, pCentro, pHeader;
	private Usuario usuario = Main.getUsuario();
	
	public void setMainWindowProperties(Seccion seccion) {
		
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
		
	public VentanaInformacionRecurso(Libro libro) {

		setMainWindowProperties(Seccion.BIBLIOTECA);
		setTitle ("Biblioteca: " + libro.getTitulo());
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
		panelimagenLibro.setAlignmentX(CENTER_ALIGNMENT);
		panelimagenLibro.setBorder(new EmptyBorder (0,65,0,0));
		JLabel imagenDelLibro = new JLabel();
		imagenDelLibro.setPreferredSize(new Dimension(275,500));
		Image imagen = libro.getFoto().getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imagen);
		imagenDelLibro.setIcon(imagenEscalada);
		imagenDelLibro.setAlignmentX(CENTER_ALIGNMENT);
		
		panelimagenLibro.add(imagenDelLibro);
		pOeste.add(panelimagenLibro);
		
		//PANEL DE LA DESCRIPCIÓN DEL LIBRO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setPreferredSize(new Dimension(900, 50)); 
		panelTitulo.setBackground(Color.WHITE);
		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
		panelDescripcion.setBackground(Color.WHITE);
		JLabel tituloLibro = new JLabel(libro.getTitulo());
		JTextArea tituloLibroArea = new JTextArea(libro.getTitulo());

		tituloLibro.setFont(new Font("Arial", Font.BOLD, 24));
		tituloLibroArea.setFont(tituloLibro.getFont());

		List<JTextArea> areas = new ArrayList<>();

		JTextArea taAutor = new JTextArea("Autor(a): " + libro.getAutor());
		JTextArea taGenero = new JTextArea("Género: " + libro.getGenero());
		JTextArea taNumeroPaginas = new JTextArea("Número de páginas: " + libro.getNumeroDePaginas());
		JTextArea taRating = new JTextArea("Rating: " + libro.getRating() + "/10");
		JTextArea taSinopsis = new JTextArea("Sinopsis: " + libro.getSinopsis());

		areas.add(taAutor);
		areas.add(taGenero);
		areas.add(taNumeroPaginas);
		areas.add(taRating); // TODO: ?
		areas.add(taSinopsis);

		for (JTextArea ta : areas) {
			ta.setFont(new Font("Arial", Font.PLAIN, 18));
        	ta.setEditable(false);
 	       	ta.setLineWrap(true);
    	    ta.setBorder(null);
        	ta.setBorder(BorderFactory.createEmptyBorder());
     	   	ta.setWrapStyleWord(true);
        	ta.setBackground(Color.WHITE);
		}
        
        // Agregar JTextArea en un JScrollPane
        JScrollPane sinopsisScrollPane = new JScrollPane(taSinopsis);
        sinopsisScrollPane.setBackground(Color.WHITE);
        sinopsisScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sinopsisScrollPane.setBorder(null);
        
        // Añadir título y descripción al panel
		if (usuario instanceof Admin) {
			panelTitulo.add(tituloLibroArea, BorderLayout.NORTH);
		} else {
        	panelTitulo.add(tituloLibro, BorderLayout.NORTH);
		}
        panelDescripcion.add(taAutor, BorderLayout.CENTER);
		panelDescripcion.add(taGenero, BorderLayout.CENTER);
		panelDescripcion.add(taNumeroPaginas, BorderLayout.CENTER);
		panelDescripcion.add(taRating, BorderLayout.CENTER);
		panelDescripcion.add(sinopsisScrollPane, BorderLayout.CENTER);
        pCentro.add(panelTitulo);
        pCentro.add(panelDescripcion);
		
		
		//PANEL REVIEWS
		JPanel reviews = new JPanel();
		reviews.setLayout(new BoxLayout(reviews, BoxLayout.Y_AXIS));
		
		reviews.setBackground(Color.WHITE);
		
		JLabel tituloReviews = new JLabel ("REVIEWS");
		tituloReviews.setFont(new Font("Arial", Font.BOLD, 16));
		tituloReviews.setAlignmentX(CENTER_ALIGNMENT);
		reviews.add(tituloReviews);
		
		String stringReviews = "";
		for (Review review : libro.getReviews()) {
			stringReviews += review.toString() + "\n";
		}

		if (stringReviews.equals("")) {
			stringReviews = "Este libro no tiene reviews";
		}

		JTextArea textoReviews = new JTextArea(stringReviews);
		textoReviews.setFont(new Font("Arial", Font.PLAIN, 14));
		textoReviews.setEditable(false);
		textoReviews.setBackground(Color.WHITE);
        
		JScrollPane reviewsScrollPane = new JScrollPane(textoReviews);
        reviewsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        reviewsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reviewsScrollPane.setPreferredSize(new Dimension(200, 100));
        reviewsScrollPane.setPreferredSize(new Dimension(400, 150));
      

        reviews.add(reviewsScrollPane);
       
		pOeste.add(reviews);
		
		//PANEL BOTONES
		
		JPanel botonesPanel = new JPanel();
		
		
		botonesPanel.setBackground(Color.WHITE);
		botonesPanel.setBorder(new EmptyBorder(110,0,45, 0));
		
		/*GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
        */
		
		JButton botonReview = new JButton("Añadir review");
		JButton botonReservar = new JButton("Reservar");
		JButton botonEditar = new JButton("Editar");
		botonEditar.addActionListener(e -> {
			dispose();
			new VentanaCrearEditarLibro(this, libro);
		});
		botonReview.setFont(new Font("Arial", Font.BOLD, 17));		
		botonReservar.setFont(new Font("Arial", Font.BOLD, 17));
        
        if (usuario == null) {
			botonReview.setEnabled(false);
			botonReservar.setEnabled(false);
		}else if (usuario instanceof Admin) {
			botonesPanel.add(botonEditar);
		} else {
			botonesPanel.add(botonReview);
			botonesPanel.add(botonReservar);
		}

		pCentro.add(botonesPanel);
		
		botonReview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAñadirReview(libro);
			}
		});
		
		pOeste.setBorder(new EmptyBorder(0,20,0,0));
		pCentro.setBorder(new EmptyBorder(70, 50, 0, 0));
		
		botonReservar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				 
				VentanaConfirmacionDeReserva nuevaVentana = new VentanaConfirmacionDeReserva(libro);
				nuevaVentana.setVisible(true);
				vInformacionRecurso.dispose();
			}
		});
		
		setVisible(true);
	}
	
	public VentanaInformacionRecurso(Sala sala) {
		setMainWindowProperties(Seccion.SALAS_DE_ESTUDIO);
	    setTitle("Salas privadas: Sala " + Integer.toString(sala.getId()));
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    panelPrincipal.setBackground(Color.WHITE);
	    
	    JLabel tituloSala = new JLabel("Sala " + Integer.toString(sala.getId()), SwingConstants.CENTER);
	    tituloSala.setFont(new Font("Arial", Font.BOLD, 40));
	    tituloSala.setAlignmentX(CENTER_ALIGNMENT);
	    tituloSala.setBackground(Color.WHITE);
	    panelPrincipal.add(tituloSala, BorderLayout.NORTH);
	    
	    
	    List<JTextArea> areas = new ArrayList<>();
		//int capacidad, int id, int piso, ArrayList<Cliente> listaClientes

        JTextArea taPiso = new JTextArea("Piso: " + Integer.toString(sala.getPiso()));
		JTextArea taCapacidad = new JTextArea("Capacidad: " + Integer.toString(sala.getCapacidad()));
		JTextArea taRecursos = new JTextArea("Recursos: " + (((SalaPrivada) sala).getRecursos()));

		areas.add(taPiso);
		areas.add(taCapacidad);
		areas.add(taRecursos);

		for (JTextArea area : areas) {
			area.setFont(new Font("Arial", Font.BOLD, 16));
 	        area.setLineWrap(true); 
    	    area.setWrapStyleWord(true); 
        	area.setEditable(false); 
		}

        panelPrincipal.add(taPiso, BorderLayout.CENTER);
		panelPrincipal.add(taCapacidad, BorderLayout.CENTER);
		panelPrincipal.add(taRecursos, BorderLayout.CENTER);
		
	    pOeste.add(panelPrincipal);
	    
	    //panel JTextArea de SalasPrivadas
	    JTextArea taDescripcion = new JTextArea(
	    	    "BiblioTech ofrece salas privadas para estudiantes interesados/as en un entorno de estudio para trabajos grupales. " +
	    	    "Con diferentes recursos, cada sala ofrece diferentes posibilidades de trabajo para los alumnos/as.\n\n" +
	    	    "Para reservar una sala, el interesado/a tiene que tener una cuenta en BiblioTech. Pulsa el botón de reserva y sigue las instrucciones.\n\n" +
	    	    "NORMAS:\n" +
	    	    "- No está permitido comer en las salas.\n" +
	    	    "- Se deben respetar los horarios de entrada y salida de reserva.\n" +
	    	    "- Se ruega tratar con cuidado los recursos de cada sala\n" +
	    	    "- En caso de encontrar alguna anomalía en los recursos o en el estado de la sala, contactar con la recepción."
	    	);
	    taDescripcion.setFont(new Font("Arial", Font.ITALIC, 16));
	    taDescripcion.setLineWrap(true); 
	    taDescripcion.setWrapStyleWord(true); 
	    taDescripcion.setEditable(false);
	    
	    pCentro.setLayout(new BorderLayout());
	    //Panel Boton
	    JPanel panelboton = new JPanel();
	    panelboton.setBackground(Color.WHITE);
	    JButton reservarButton = new JButton("Reservar");
	    JButton botonEditar = new JButton("Editar");
	    botonEditar.addActionListener(e -> {
	    	new VentanaCrearEditarSala(this, (SalaPrivada) sala);
	    	dispose();
	    });
	   
        if (usuario == null) {
        	panelboton.add(reservarButton);
			reservarButton.setEnabled(false);
		}else if (usuario instanceof Admin) {
			panelboton.add(botonEditar);
		} else {
			panelboton.add(reservarButton);
		}
        
	    reservarButton.setFont(new Font("Arial", Font.BOLD, 20));
	    botonEditar.setFont(new Font("Arial", Font.BOLD, 20));
	     
        pCentro.setBorder(new EmptyBorder(160, 100, 30, 15));
        pOeste.setBorder(new EmptyBorder(140, 15, 10, 0));
        pCentro.add(taDescripcion);
	    pCentro.add(panelboton, BorderLayout.SOUTH);

	    // Asegúrate de que pCentrpesté agregado al JFrame
	    

	    reservarButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Cerrar la ventana actual
		        vInformacionRecurso.dispose();
		        // Abrir Venatana de ConfirmacionReserva
		        new VentanaConfirmacionReservaSalaPrivada((SalaPrivada) sala);
		    }
		});
	   
	    setVisible(true);
	}
	
    public VentanaInformacionRecurso(Evento evento) {
    	
    	setMainWindowProperties(Seccion.EVENTOS);
	    setTitle(evento.getTitulo());
	    
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
	    
	    //panel JTextArea de SalasPrivadas
	    JTextArea taDescripcion = new JTextArea(
	    	    "BiblioTech organiza en ciertos días unos eventos sobre diferentes temáticas. Desde charlas hasta talleres, " +
	    	    "BiblioTech invita a expertos/as de diferentes disciplinas para todas las audiencias. Para poder participar en un evento, " +
	    	    "pulsa el botón de \"Reservar\" para reservar una plaza de asistencia al evento. Los eventos son de asistencia gratuita a los usuarios/as registrados en la plataforma.\n\n" +
	    	    "NORMAS:\n" +
	    	    "- La reserva para asistir a un evento es obligatoria.\n" +
	    	    "- Se ruega no alterar las salas de eventos.\n" +
	    	    "- Trata con respeto a los y las asistentes del evento, al igual que a los organizadores.\n" +
	    	    "- Se ruega a el/la asistente puntualidad a la hora de asistir, para no interrumpir el entorno del evento."
	    	);
	    taDescripcion.setFont(new Font("Arial", Font.ITALIC, 16));
	    taDescripcion.setLineWrap(true); 
	    taDescripcion.setWrapStyleWord(true); 
	    taDescripcion.setEditable(false);
	    
	    pCentro.setLayout(new BorderLayout());
	    //Panel Boton
	    JPanel panelboton = new JPanel();
	    
	    JButton botonEditar = new JButton("Editar");
	    botonEditar.addActionListener(e -> {
	    	new VentanaCrearEditarEvento(this, evento);
	    	dispose();
	    });
	    
	    panelboton.setBackground(Color.WHITE);
	    JButton reservarButton = new JButton("Reservar");
	   
        
        if (usuario == null) {
        	panelboton.add(reservarButton);
			reservarButton.setEnabled(false);
		}else if (usuario instanceof Admin) {
			panelboton.add(botonEditar);
		} else {
			panelboton.add(reservarButton);
		}
	    reservarButton.setFont(new Font("Arial", Font.BOLD, 20));
	    botonEditar.setFont(new Font("Arial", Font.BOLD, 20));
	    panelboton.add(reservarButton);
	    
        
        pCentro.setBorder(new EmptyBorder(160, 100, 30, 15));
        pOeste.setBorder(new EmptyBorder(140, 15, 10, 0));
        pCentro.add(taDescripcion);
	    pCentro.add(panelboton, BorderLayout.SOUTH);

	    // Asegúrate de que pCentrpesté agregado al JFrame
	    

	    reservarButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Cerrar la ventana actual
		        vInformacionRecurso.dispose();
		        // Abrir Venatana de ConfirmacionReserva
		        new VentanaConfirmacionReservaEvento(evento);
		    }
		});
	   
	    setVisible(true);
    }
        /*this.evento = evento;
    	setMainWindowProperties(Seccion.EVENTOS);
    	setTitle("Eventos: " + evento.getTitulo());
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    panelPrincipal.setBackground(Color.WHITE);

	    JPanel panelTitulo = new JPanel();
	    panelTitulo.setBackground(Color.WHITE);
	    
	    JLabel tituloEvento = new JLabel(evento.getTitulo(), SwingConstants.CENTER);
	    tituloEvento.setFont(new Font("Arial", Font.BOLD, 40));

	    
	    tituloEvento.setBackground(Color.WHITE);
	    panelTitulo.add(tituloEvento, BorderLayout.NORTH);

	    List<JTextArea> areas = new ArrayList<>();
	    
	    JTextArea taTipoEvento = new JTextArea("Tipo del Evento: " + evento.getTipoEvento().toString());
		JTextArea taSala = new JTextArea("Sala: " + evento.getSala().getId());
		JTextArea taFecha = new JTextArea("Fecha: " + evento.getFechaHora().toString());
	

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
    	
    	BotonReservar();
    	    
		setVisible(true);
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
		if (usuario == null) {
			reservarButton.setEnabled(false);
		}
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
	*/
	public LocalDate calcularDiasParaDevolver(int paginas) { // TODO
		LocalDate fechaDevolucion = LocalDate.now();
		int dias = Math.round(paginas / 10);
		return fechaDevolucion.plusDays(dias);
	}
	
	public static void main(String[] args) {
		ImageIcon foto = Utils.loadImage("books/9780006514855" + ".jpg", 128, 200);
		Libro libro = new Libro(0000000000000, "Libro 1", "Autor 1", 300, "Sinopsis", "Genero 1", 30, 2003, foto, new ArrayList<Review>());
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setAdmin(isDefaultLookAndFeelDecorated());
		usuarioDTO.setAmonestaciones(12);
		usuarioDTO.setDni("73291756J");
		usuarioDTO.setNombre("Jorge");
		
		ArrayList<Recurso>recursos = new ArrayList<>();
		recursos.add(Recurso.ORDENADORES);
		recursos.add(Recurso.PIZARRA);
		recursos.add(Recurso.PROYECTOR);
		
		Sala sala = new SalaPrivada(100, 987, 2, recursos, new ArrayList<Reserva> ());
		
		
		
		
		
		//new VentanaInformacionRecurso(sala);
		new VentanaInformacionRecurso(libro);
		//new InformacionRecurso(sala, new Cliente());
		//new VentanaInformacionRecurso(libro);
		
	}

}
