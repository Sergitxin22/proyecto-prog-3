package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import BiblioTech.MetodosDeOrdenamiento;
import BiblioTech.Seccion;

public class VentanaBibliotecaNoLogueado extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaBibliotecaNoLogueado() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Bibliotech - No logueado");
		setExtendedState(MAXIMIZED_BOTH);
		setSize(640,480);
		setLocationRelativeTo(null);
		
		// Panel superior que contendrá el Header
        JPanel panelSuperior = new Header(Seccion.BIBLIOTECA, null);
        
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
	
	public static void main(String[] args) {
		VentanaBibliotecaNoLogueado ventana = new VentanaBibliotecaNoLogueado();
	}

}
