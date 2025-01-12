package gui;

import domain.Admin;
import domain.Cliente;
import domain.SalaPublica;
import domain.Seccion;
import domain.Usuario;
import gui.components.Header;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import dbmejorada.ReservaSalaPublicaDTO;
import main.Main;
import utils.SalaPublicaDaemon;
import utils.Utils;

public class VentanaSalaPublica extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();
	private SalaPublicaDaemon daemon;

	public VentanaSalaPublica()  { 

        SalaPublica salaPublica = Main.getSalaPublica();
        
        setTitle("Sala Pública");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
        // Header panel
        JPanel header = new Header(Seccion.SALAS_DE_ESTUDIO, usuario, this);
        
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
        masInformacionButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "-Recuerda que una vez te asignas un bloque, debes devolverlo en un plazo de 4 horas.\n-No devolver el bloque en el tiempo acordado supondra añadir una amonestación al usuario/a.\n-Si el usuario/a tiene 3 o más amonestaciones, se le revocará el permiso de asignación.\n- Se recuerda a las personas usuarias que no está permitido comer dentro de ninguna sala de estudio.\n- Se ruega reducir al máximo el volumen de las actividades realizadas en la sala.\n- La asignación de bloque es obligatoria para evitar malentendidos y sobrecarga.\n- A la hora de abandonar la sala, asegúrese de desasignar su bloque para que pueda ser utilizado por otra persona.\n- Procurar dejar los bloques en el mismo estado en los que se han encontrado.", "Más información y normas", JOptionPane.INFORMATION_MESSAGE);

        });
        boolean clienteEnSala = clienteEnSalaPublica(usuario);
        JButton asignarBloqueButton = new JButton();

            
    
        if (usuario == null) {
            asignarBloqueButton.setEnabled(false);
        }
        if (clienteEnSala) {
             asignarBloqueButton.setText("Desasignar bloque");
        } else {
              asignarBloqueButton.setText("Asignar bloque");
        }
      

        asignarBloqueButton.addActionListener(e -> {

            if (clienteEnSala) {
            	 for (Integer i : salaPublica.getClientesPorBloque().keySet()) {
                     if (salaPublica.getClientesPorBloque().get(i) == usuario) {
                    	 salaPublica.getClientesPorBloque().put(i, null);
                    	 if(Main.getReservaSalaPublicaDAO().desasignarBloque(usuario.getDni())) {
                    		 JOptionPane.showMessageDialog(this, "Bloque desasignado correctamente.", "Bloque desasignado", JOptionPane.INFORMATION_MESSAGE);
                    		 new VentanaSalaPublica();
                    		 Main.getThreads().get(0).interrupt();
                    		 dispose();
                    		 
                    	 } else {
                    		 JOptionPane.showMessageDialog(this, "Error al desasignar el bloque.", "Error", JOptionPane.ERROR_MESSAGE);
                    	 }
                        break;
                     }
            	 }
            } else {
            	if (((Cliente) usuario).getAmonestaciones() >= 3) {
            		JOptionPane.showMessageDialog(this, "Has superado el límite de amonestaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            	} else {
            		int bloqueAsignado = 0;
                    for (Integer i : salaPublica.getClientesPorBloque().keySet()) {
                        if (salaPublica.getClientesPorBloque().get(i) == null) {
                            bloqueAsignado = i;
                            salaPublica.getClientesPorBloque().put(i, (Cliente) usuario);
                            ReservaSalaPublicaDTO reserva = new ReservaSalaPublicaDTO(0, LocalDateTime.now(), usuario.getDni(), bloqueAsignado);
                            Main.getReservaSalaPublicaDAO().addReservaSalaPublica(reserva);
                            break;
                        }
                    }

                    if (bloqueAsignado == 0) {
                        JOptionPane.showMessageDialog(this, "Lo lamentamos, la sala se encuentra llena actualmente.", "Error en la asignación", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Se le ha asignado el bloque " + bloqueAsignado + ".\nRecibirás un recordatorio 30 minutos antes del fin del plazo de devolución.", "Asignación confirmada", JOptionPane.INFORMATION_MESSAGE);
                        daemon = new SalaPublicaDaemon((Cliente) usuario);
                        Main.getThreads().add(daemon.thread);
                        new VentanaSalaPublica();
                        dispose();
                        
                    }
            	}
                
            }
        });

        if (usuario instanceof Admin) {
            masInformacionButton.setEnabled(false);
            asignarBloqueButton.setEnabled(false);
        }
        
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

    public static boolean clienteEnSalaPublica(Usuario usuario) {

        SalaPublica sala = Main.getSalaPublica();

        if (usuario == null) {
            return false;
        }

        for (Integer i: sala.getClientesPorBloque().keySet()) {
            if (sala.getClientesPorBloque().get(i) != null) {
                if (sala.getClientesPorBloque().get(i).equals(usuario)) {
                    return true;
                }
            }   
        }
        return false;
    }

    public static void main(String[] args) {
        new VentanaSalaPublica();
    }
    
}
