package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import domain.Admin;
import domain.Libro;
import domain.MetodosDeOrdenamiento;
import domain.Seccion;
import domain.Usuario;
import gui.components.AddPanel;
import gui.components.Header;
import io.InputUtils;
import main.Main;
import utils.Utils;

public class VentanaBiblioteca extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final ArrayList<Libro> listaLibros = InputUtils.cargarLibros();
	private ArrayList<Libro> listaLibrosRenderizada = new ArrayList<Libro>(listaLibros);
	private Usuario usuario = Main.getUsuario();
	
	public VentanaBiblioteca() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (usuario == null) {
			setTitle("Biblioteca - No logueado");			
		} else {			
			setTitle("Biblioteca - Logueado: " + usuario.getNombre());
		}
		
		setSize(1280,720);
		setLocationRelativeTo(null);
		
		// Panel superior que contendrá el Header
        JPanel panelSuperior = new Header(Seccion.BIBLIOTECA, usuario, this);
        
        // Agregar panel superior al marco principal
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel inferior
		JPanel panelContenido = new JPanel(new BorderLayout());
		
		JPanel subPanelContenido1 = new JPanel(new BorderLayout());
		panelContenido.add(subPanelContenido1, BorderLayout.NORTH);
		
		MetodosDeOrdenamiento[] array = new MetodosDeOrdenamiento[4];
		int contador = 0;
				
		for (MetodosDeOrdenamiento metodo: MetodosDeOrdenamiento.values()) {
			array[contador]=metodo;
			contador++;
		}
		
		JComboBox<MetodosDeOrdenamiento> ordenar = new JComboBox<MetodosDeOrdenamiento>(array);
		subPanelContenido1.add(ordenar, BorderLayout.EAST);

		JTextField buscador = new JTextField("Buscador");
		buscador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscador.setText("");
			}
		});
		
		subPanelContenido1.add(buscador, BorderLayout.CENTER);
		
		// Añadir libro
		if (usuario instanceof Admin) {
			JPanel panelAddLibro = new AddPanel(this, Seccion.BIBLIOTECA, usuario);
	        subPanelContenido1.add(panelAddLibro, BorderLayout.WEST);
		}		
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(0, 8));
		
		int contadorLibros = 0;
		for (Libro libro : listaLibrosRenderizada) {
			JPanel panelCentrarLibro = crearPanelLibroCentrado(libro);
			subPanelContenido2.add(panelCentrarLibro);
			if (contadorLibros >= 30) break;
			contadorLibros++;
		}
		
		JScrollPane scrollBar = new JScrollPane(subPanelContenido2);
		panelContenido.add(scrollBar, BorderLayout.CENTER);

		add(panelContenido, BorderLayout.CENTER);
		
		buscador.addKeyListener(new KeyAdapter() {
			@Override
			 public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(buscador.getText());
					//recargar pagina con la lista filtrada
					List<Libro> listaFiltrada = listaLibros.stream()
							.filter(libro -> libro.getTitulo().toLowerCase().contains(buscador.getText().toLowerCase()))
							.toList();
					listaLibrosRenderizada = new ArrayList<Libro>(listaFiltrada);
					
					 // Llamar a recargar el panel
					recargarPanelContenido(subPanelContenido2);
				}
			}
		});
		
		ordenar.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED && !(e.getItem().equals("Ordenar"))) {
					MetodosDeOrdenamiento metodoOrdenamiento = (MetodosDeOrdenamiento) e.getItem();
		            ordenarLibros(metodoOrdenamiento,subPanelContenido2);
				}
			}
		});
		
		setVisible(true);
	}
	
	
	private JPanel crearPanelLibroCentrado(Libro libro) {
		JPanel panelCentrarLibro = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JPanel panelLibro = new JPanel();
		panelLibro.setLayout(new BoxLayout(panelLibro,BoxLayout.Y_AXIS));
		ImageIcon imagenLibro = libro.getFoto();
        JLabel iconLabel = new JLabel(imagenLibro);
		panelLibro.add(iconLabel);
		
		String titulo = libro.getTitulo();
		
		if (titulo.length() >= 14) {
			titulo = libro.getTitulo().substring(0, 14) + "...";
		}
		JLabel tituloLibro = new JLabel(titulo);

		panelLibro.setToolTipText(libro.getTitulo());
		panelLibro.add(tituloLibro);
		
		panelCentrarLibro.add(panelLibro);
		
		panelLibro.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				abrirVentanaInformacionLibro(libro);
				super.mouseClicked(e);
			}
			
		});
		return panelCentrarLibro;
	}

	private void recargarPanelContenido(JPanel subPanelContenido2) {
	    subPanelContenido2.removeAll(); // Eliminar todos los componentes actuales.

	    int contadorLibros = 0;
	    for (Libro libro : listaLibrosRenderizada) {
	        JPanel panelCentrarLibro = crearPanelLibroCentrado(libro);
	        subPanelContenido2.add(panelCentrarLibro);
	        if (contadorLibros >= 30) break;
	        contadorLibros++;
	    }

	    subPanelContenido2.revalidate(); // Informar al layout que actualice la UI.
	    subPanelContenido2.repaint();   // Redibujar el panel.
	}
	

	private void abrirVentanaInformacionLibro(Libro libro) {
		// TODO descomentar cuando se actualice el constructor de la ventana InformacionRecurso
		//InformacionRecurso ventanaInformacionLibro = new InformacionRecurso(libro, this);
		VentanaInformacionRecurso ventanaInformacionLibro = new VentanaInformacionRecurso(libro);
		ventanaInformacionLibro.setVisible(true);
		this.setVisible(false);
	}

	private void ordenarLibros(MetodosDeOrdenamiento item, JPanel subPanelContenido2) {
//		switch (item) {
//		case TITULO:
//			Collections.sort(listaLibrosRenderizada, (o1, o2) -> o1.getTitulo().compareTo(o2.getTitulo()));
//			break;
//		case AUTOR:
//			Collections.sort(listaLibrosRenderizada, (o1, o2) -> o1.getAutor().compareTo(o2.getAutor()));
//			break;
//		case FECHA:
//			Collections.sort(listaLibrosRenderizada, (o1, o2) -> o1.getFechaPublicacion() - o2.getFechaPublicacion());
//			break;
//		default:
//			listaLibrosRenderizada = new ArrayList<Libro>(listaLibros);
//			break;
//		}
		listaLibrosRenderizada = Utils.sortArrayMetodoDeOrdenamiento(listaLibrosRenderizada, item, listaLibrosRenderizada.size());
		recargarPanelContenido(subPanelContenido2);
	}
	
	public static void main(String[] args) {
		new VentanaBiblioteca();
	}

}
