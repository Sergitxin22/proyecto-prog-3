package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import BiblioTech.MetodosDeOrdenamiento;

public class VentanaBibliotecaNoLogueado extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaBibliotecaNoLogueado() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bibliotech - No logueado");
		setSize(640,480);
		
		// Panel superior que contendrá dos paneles
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.GRAY);
        
        // Primer panel (izquierdo)
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIzquierdo.setBackground(Color.LIGHT_GRAY);
        
        // Crear el icono
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/libros.png"));

        JLabel iconLabel = new JLabel(icon);
        JLabel textLabel = new JLabel("BiblioTech"); // Texto al lado del icono
        
        // Añadir mouse listener para el ícono
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Ícono clickeado");
                // Aquí puedes agregar la lógica que necesites
            }
        });
        
        // Añadir mouse listener para el texto
        textLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Texto clickeado");
                // Aquí puedes agregar la lógica que necesites
            }
        });

        panelIzquierdo.add(iconLabel);
        panelIzquierdo.add(textLabel);
        
        // Segundo panel (derecho)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.DARK_GRAY);
        
        ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("images/user.png"));

        JLabel iconLabel2 = new JLabel(icon2);
        panelDerecho.add(iconLabel2);
        
        // Agregar los paneles izquierdo y derecho al panel superior
        panelSuperior.add(panelIzquierdo, BorderLayout.WEST);
        panelSuperior.add(panelDerecho, BorderLayout.EAST);
        
        // Agregar panel superior al marco principal
        add(panelSuperior, BorderLayout.NORTH);
		
		// Panel inferior
		JPanel panelContenido = new JPanel(new BorderLayout());
		add(panelContenido, BorderLayout.CENTER);
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
		
		JPanel subPanelContenido2 = new JPanel(new GridLayout(2,4));
		panelContenido.add(subPanelContenido2, BorderLayout.CENTER);
		subPanelContenido2.setBackground(Color.orange);
//		subPanelContenido2.setLayout(GridLayout(2,4));
		subPanelContenido2.add(new JButton("Libro 1"));
		subPanelContenido2.add(new JButton("Libro 2"));
		subPanelContenido2.add(new JButton("Libro 3"));
		subPanelContenido2.add(new JButton("Libro 4"));
		subPanelContenido2.add(new JButton("Libro 5"));
		subPanelContenido2.add(new JButton("Libro 6"));
		subPanelContenido2.add(new JButton("Libro 7"));
		subPanelContenido2.add(new JButton("Libro 8"));
		
		
		JScrollPane scrollBar = new JScrollPane();
		subPanelContenido2.add(scrollBar, BorderLayout.EAST);
		
		scrollBar.setBackground(Color.magenta);

				
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaBibliotecaNoLogueado ventana = new VentanaBibliotecaNoLogueado();
	}

}
