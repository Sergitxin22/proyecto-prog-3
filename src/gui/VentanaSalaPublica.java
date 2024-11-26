package gui;

import BiblioTech.Seccion;
import BiblioTech.Usuario;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import utils.Utils;

public class VentanaSalaPublica extends JFrame {

    public VentanaSalaPublica(Usuario usuario)  {

        setTitle("Sala Pública");
        setSize(1280, 720);
        setLocationRelativeTo(null);
	    
        // Header panel
        JPanel header = new Header(Seccion.SALAS_DE_ESTUDIO, usuario);
        
        // Image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Sala Pública");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));

        JLabel imageIcon = new JLabel();
        imageIcon.setIcon(Utils.loadImage("salaPublicaFoto.png", 400, 300));

        imagePanel.add(titleLabel);
        imagePanel.add(imageIcon);

        // Information panel
        JPanel informationPanel = new JPanel(new GridLayout(2, 1));

        JTextArea informationTextArea = new JTextArea("En DeustoTech ofrecemos una sala de estudio pública para todo estudiante dispuesto/a a estudiar en un entorno sin distracciones disponible sin reserva previa.\n\nCon un total de 250 plazas, la sala se divide en pequeños bloques de estudio en los que se encuentra un enchufe, luz y espacio suficiente para cada estudiante. A cada bloque se le asigna un estudiante que tendrá que asignarse un bloque al llegar al centro. Para más información, entre en 'Más información y normas'");

        informationTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        informationTextArea.setLineWrap(true); 
        informationTextArea.setWrapStyleWord(true); 
        informationTextArea.setEditable(false); 
        informationTextArea.setColumns(40);

        // Button panel
        JPanel buttonPanel = new JPanel();

        JButton masInformacionButton = new JButton("Más información y normas");
        JButton asignarBloqueButton = new JButton("Asignar bloque");

        buttonPanel.add(masInformacionButton);
        buttonPanel.add(asignarBloqueButton);

        informationPanel.add(informationTextArea);
        informationPanel.add(buttonPanel);

        // Borders
        header.setBorder(new EmptyBorder(0, 0, 20, 0));
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        imagePanel.setBorder(new EmptyBorder(70, 50, 0, 0));
        buttonPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        informationPanel.setBorder(new EmptyBorder(70, 20, 0, 50));

        add(header, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.WEST);
        add(informationPanel, BorderLayout.EAST);

	    setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaSalaPublica(null);
    }
    
}
