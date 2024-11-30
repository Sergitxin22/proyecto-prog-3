package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import BiblioTech.Admin;
import BiblioTech.Libro;
import BiblioTech.MetodosDeOrdenamiento;
import BiblioTech.Seccion;
import BiblioTech.Usuario;
import utils.Utils;

public class VentanaBiblioteca extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ArrayList<Libro> listaLibros = Utils.cargarLibros();
	private ArrayList<Libro> listaLibrosRenderizada = new ArrayList<Libro>(listaLibros);
	
	public VentanaBiblioteca(Usuario usuario) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (usuario == null) {
			setTitle("Bibliotech - No logueado");			
		} else {			
			setTitle("Bibliotech - logueado" + usuario.getClass().toString());
		}
		
		setSize(1280,720);
		setLocationRelativeTo(null);
		
		// Panel superior que contendrá el Header
        JPanel panelSuperior = new Header(Seccion.BIBLIOTECA, usuario);
        
        // Agregar panel superior al marco principal
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel inferior
		JPanel panelContenido = new JPanel(new BorderLayout());
		
		JPanel subPanelContenido1 = new JPanel(new BorderLayout());
		panelContenido.add(subPanelContenido1, BorderLayout.NORTH);
		
		MetodosDeOrdenamiento[] array = new MetodosDeOrdenamiento[3];
		int contador = 0;
				
		for (MetodosDeOrdenamiento metodo: MetodosDeOrdenamiento.values()) {
			array[contador]=metodo;
			contador++;
		}
		
		JComboBox ordenar = new JComboBox(array);
		ordenar.insertItemAt("Ordenar", 0);
		ordenar.setSelectedIndex(0);
		subPanelContenido1.add(ordenar, BorderLayout.EAST);
		ordenar.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if(ordenar.getItemAt(0).equals("Ordenar")) {
					ordenar.removeItemAt(0);
				};
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
			JPanel panelAddLibro = createPanelAddLibro();
	        subPanelContenido1.add(panelAddLibro, BorderLayout.WEST);
		}		
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(0, 8));
		//subPanelContenido2.setBackground(Color.orange);
//		ArrayList<Libro> listaLibros = Utils.cargarLibros();
		System.out.println(listaLibrosRenderizada.toString());
		
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
					recargarPanelContenido(subPanelContenido2, scrollBar);
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
//		try {
//			imagenLibro = Utils.loadImage("books/big/" + libro + ".jpg",98,151);
//		} catch (Exception e) {
//			imagenLibro = Utils.loadImage("books/noImagen.jpg",98,151);
//		}
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
				JLabel labelTitulo = (JLabel) panelLibro.getComponent(1);
				String titulo = labelTitulo.getToolTipText();
				abrirVentanaInformacionLibro(libro);
				System.out.println(libro);
				super.mouseClicked(e);
			}
			
		});
		return panelCentrarLibro;
	}

	private JPanel createPanelAddLibro() {
		JPanel panelAddLibro = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, -5, 0, 5); // Margen entre componentes (icono y texto)
	    gbc.anchor = GridBagConstraints.CENTER; // Centrar verticalmente y horizontalmente
	    
		ImageIcon iconoAddLibro = Utils.loadImage("add.png",36,36);
	    JLabel iconLabel = new JLabel(iconoAddLibro);
	     
	    JLabel textLabel = new JLabel("Añadir libro");
     
	    // Añadir mouse listener para el panel
	    panelAddLibro.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
	        	System.out.println("Panel clickeado");
	            // Aquí puedes agregar la lógica que necesites
        	}
	    });
	    
	    panelAddLibro.add(iconLabel, gbc);
	    gbc.gridx = 1; // Segunda columna
	    panelAddLibro.add(textLabel, gbc);
	    return panelAddLibro;
	}
	private void recargarPanelContenido(JPanel subPanelContenido2, JScrollPane scrollBar) {
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
		InformacionRecurso ventanaInformacionLibro = new InformacionRecurso(libro);
		ventanaInformacionLibro.setVisible(true);
		setVisible(false);
		
	}

	private void ordenarLibros(MetodosDeOrdenamiento item) {
		System.out.println(item);
	}
	
	public static void main(String[] args) {
		new VentanaBiblioteca(null);
//		VentanaBiblioteca ventana2 = new VentanaBiblioteca(new Cliente());
//		VentanaBiblioteca ventana3 = new VentanaBiblioteca(new Admin());
	}

}
