package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import BiblioTech.Cliente;
import BiblioTech.MetodosDeOrdenamiento;
import BiblioTech.Seccion;
import BiblioTech.Usuario;
import utils.Utils;

public class VentanaBiblioteca extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaBiblioteca(Usuario usuario) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (usuario == null) {
			setTitle("Bibliotech - No logueado");			
		} else {			
			setTitle("Bibliotech - logueado" + usuario.getClass().toString());
		}

		setExtendedState(MAXIMIZED_BOTH);
		setSize(640,480);
		setLocationRelativeTo(null);
		
		// Panel superior que contendrá el Header
        JPanel panelSuperior = new Header(Seccion.BIBLIOTECA, usuario);
        
        // Agregar panel superior al marco principal
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel inferior
		JPanel panelContenido = new JPanel(new BorderLayout());
		panelContenido.setBackground(Color.green);
		
		JPanel subPanelContenido1 = new JPanel(new BorderLayout());
		panelContenido.add(subPanelContenido1, BorderLayout.NORTH);
		subPanelContenido1.setBackground(Color.blue);
		
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
		ordenar.setBackground(Color.pink);
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
		buscador.addKeyListener(new KeyAdapter() {
			@Override
			 public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(buscador.getText());
					//recargar pagina con la lista filtrada
					
				}
			}
		});
		subPanelContenido1.add(buscador, BorderLayout.CENTER);
		buscador.setBackground(Color.red);
		
		// Añadir libro
		if (usuario instanceof Admin) {
			JPanel panelAddLibro = createPanelAddLibro();
	        subPanelContenido1.add(panelAddLibro, BorderLayout.WEST);
		}		
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(0, 4));
		//subPanelContenido2.setBackground(Color.orange);
		for (int i = 1; i < 501; i++) {
			subPanelContenido2.add(new JButton("Libro " + i));
		}
		
		JScrollPane scrollBar = new JScrollPane(subPanelContenido2);
		panelContenido.add(scrollBar, BorderLayout.CENTER);
		
		scrollBar.setBackground(Color.magenta);

		add(panelContenido, BorderLayout.CENTER);
		
		setVisible(true);
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

	public static void main(String[] args) {
		VentanaBiblioteca ventana = new VentanaBiblioteca(null);
//		VentanaBiblioteca ventana2 = new VentanaBiblioteca(new Cliente());
//		VentanaBiblioteca ventana3 = new VentanaBiblioteca(new Admin());
	}

}
