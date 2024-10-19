package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1102784202811827191L;

	public Header() {
		setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        
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
        
        // Agregar los paneles izquierdo y derecho al Header
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.EAST);
	}

}
