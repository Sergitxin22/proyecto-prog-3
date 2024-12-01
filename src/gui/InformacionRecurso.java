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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
		imagenDelLibro.setPreferredSize(new Dimension(275,500));
		Image imagen = libro.getFoto().getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imagen);
		imagenDelLibro.setIcon(imagenEscalada);
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
		
		JLabel tituloReviews = new JLabel ("Reviews");
		tituloReviews.setFont(new Font("Arial", Font.BOLD, 16));
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
        
        if (usuario == null) {
			botonReview.setEnabled(false);
			botonReservar.setEnabled(false);
		}

		if (usuario instanceof Admin) {

			for (JTextArea ta : areas) {
				ta.setEditable(true);
			}

			tituloLibroArea.setEditable(true);

			JButton guardarCambiosButton = new JButton("Guardar cambios");
			guardarCambiosButton.addActionListener(e -> {

				String titulo = tituloLibroArea.getText();
				System.out.println(titulo);

				String autor = taAutor.getText();
				autor = autor.substring(10);
				System.out.println(autor);

				String genero = taGenero.getText();
				genero = genero.substring(8);
				System.out.println(genero);

				String numeroPaginas = taNumeroPaginas.getText();
				numeroPaginas = numeroPaginas.substring(19);
				System.out.println(numeroPaginas);

				String rating = taRating.getText();
				int lengthRating = taRating.getText().length();
				rating = rating.substring(8, lengthRating - 3);
				System.out.println(rating);

				String sinopsis = taSinopsis.getText();
				sinopsis = sinopsis.substring(10);
				System.out.println(sinopsis);

				libro.setTitulo(titulo);
				libro.setAutor(autor);
				libro.setGenero(genero);
				libro.setNumeroDePaginas(Integer.parseInt(numeroPaginas));
				libro.setRating(Integer.parseInt(rating));
				libro.setSinopsis(sinopsis);
				repaint();
				// TODO: AÑADIR AQUI FUNCIONALIDAD PARA PASARLO A LA BASE DE DATOS Y A LA LISTA DE LIBROS
			});
			
			JButton eliminarLibroButton = new JButton("Eliminar libro");

			botonesPanel.add(guardarCambiosButton);
			botonesPanel.add(eliminarLibroButton);
		} else {
			botonesPanel.add(botonReview);
			botonesPanel.add(botonReservar);
		}

		
       
		pCentro.add(botonesPanel);
		
		botonReview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AñadirReview ventanaNueva = new AñadirReview(libro, (Cliente) usuario);
			}
		});
		
		botonReservar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				 
				VentanaConfirmaciónDeReserva nuevaVentana = new VentanaConfirmaciónDeReserva(libro);
				nuevaVentana.setVisible(true);
				vInformacionRecurso.dispose();
			}
		});
		
		setVisible(true);
	}
	public InformacionRecurso(Sala sala, Usuario usuario) {
		if (usuario instanceof Admin) {
			vInformacionRecurso.dispose();
		}
		setMainWindowProperties(Seccion.SALAS_DE_ESTUDIO, usuario);
	    setTitle("Sala " + Integer.toString(sala.getId()) );
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    panelPrincipal.setBackground(Color.WHITE);
	    
	    JLabel tituloSala = new JLabel("Sala " + Integer.toString(sala.getId()), SwingConstants.CENTER);
	    tituloSala.setFont(new Font("Arial", Font.BOLD, 40));
	    tituloSala.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
	    tituloSala.setVerticalAlignment(SwingConstants.CENTER);
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
			area.setFont(new Font("Arial", Font.BOLD, 38));
 	        area.setLineWrap(true); 
    	    area.setWrapStyleWord(true); 
        	area.setEditable(false); 
		}

        panelPrincipal.add(taPiso, BorderLayout.CENTER);
		panelPrincipal.add(taCapacidad, BorderLayout.CENTER);
		panelPrincipal.add(taRecursos, BorderLayout.CENTER);
		
	    pOeste.add(panelPrincipal);
	    pEste.setLayout(new BorderLayout());
	    JButton reservarButton = new JButton("Reservar");
	    reservarButton.setFont(new Font("Arial", Font.BOLD, 20));
        reservarButton.setPreferredSize(new Dimension(200, 50));
 
        
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
		        new VentanaConfirmacionReservaSalaPrivada((SalaPrivada) sala, usuario);
		    }
		});
	   
	    setVisible(true);
	}
    public InformacionRecurso(Evento evento, Usuario usuario) {
    	if (usuario instanceof Admin) {
			vInformacionRecurso.dispose();
		}
        this.evento= evento;
    	setMainWindowProperties(Seccion.EVENTOS, usuario);
    	setTitle("Evento " + evento.getTitulo() );
	    
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
		JTextArea taFecha = new JTextArea("Fecha: " + evento.getFecha().toString());
		JTextArea taHora = new JTextArea("Hora: " + evento.getHora());

		areas.add(taTipoEvento);
		areas.add(taSala);
		areas.add(taFecha);
		areas.add(taHora);

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
		panelPrincipal.add(taHora, BorderLayout.CENTER);
	    

        // Añadir el panel principal al JFrame
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        
        pOeste.setBorder(new EmptyBorder(0, 0, 10, 0));
	    pOeste.add(panelPrincipal);
    	
    	
    	BotonReservar(usuario);
    	
    
	    
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
    
    private void BotonReservar(Usuario usuario) {
    	
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
		        new VentanaConfirmacionReservaEvento(evento, usuario);
		    }
 	    });
    }
	
	public LocalDate calcularDiasParaDevolver(int paginas) {
		LocalDate fechaDevolucion = LocalDate.now();
		int dias = Math.round(paginas / 10);
		return fechaDevolucion.plusDays(dias);
	}

	public static void main(String[] args) {
		ImageIcon foto = Utils.loadImage("books/9780006514855" + ".jpg", 128, 200);
		Libro libro = new Libro(0000000000000, "Libro 1", "Autor 1", 300, "Sinopsis", "Genero 1", 30, 2003, foto, new ArrayList<Review>());

		libro.getReviews().add(new Review(libro, new Cliente("e", "Ane", "s", LocalDateTime.now(), "a", new ArrayList<>(), new ArrayList<>(), 2), "Buen libro", 3));
		libro.getReviews().add(new Review(libro, new Cliente("e", "Ander", "s", LocalDateTime.now(), "a", new ArrayList<>(), new ArrayList<>(), 2), "Mal libro", 10));


		Evento evento = new Evento(12, "Charla sobre la Comunicación", TipoEvento.CHARLA, new ArrayList<Cliente>(), new SalaEventos(100, 2, 4, new ArrayList<Cliente>(), new Evento()), LocalDate.now(), 19);
		SalaPrivada sala = new SalaPrivada(2, 110, 2, null, null);		
		
		//new InformacionRecurso(sala, new Cliente());
		new InformacionRecurso(evento, new Cliente());
		//new InformacionRecurso(sala, new Cliente());
		//new InformacionRecurso(libro, new Cliente());
		
	}

}
