package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dbmejorada.UsuarioDTO;
import domain.Cliente;
import main.Main;

public class SalaPublicaDaemon extends JFrame {

	
	private static final long serialVersionUID = 1L;
	public Thread thread;

	public SalaPublicaDaemon(Cliente usuario) {
		thread = new Thread(new Runnable() {
            @Override
            public void run() {
            	
                for (int i = 12600; i >= 0; i--) { // Tres horas y media
                    final int tiempoRestante = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        	
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    	return;
                    }
                }

                JOptionPane.showMessageDialog(null, usuario.getNombre() + ", Tienes 30 minutos para devolver tu bloque.", "Recordatorio", JOptionPane.INFORMATION_MESSAGE);
                
                for (int i = 1800; i >= 0; i--) { // Media hora (cuatro horas en total)
                    final int tiempoRestante = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        	
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    	return;
                    }
                }
                
                JOptionPane.showMessageDialog(null, usuario.getNombre() + ", Se ha agotado el tiempo de devolución. Has recibido una amonestación.", "Amonestación recibida", JOptionPane.INFORMATION_MESSAGE); 
                Main.getUsuarioDAO().updateAmonestaciones(new UsuarioDTO(usuario.getDni(), usuario.getNombre(), usuario.getEmail(), usuario.getFechaCreacion(), usuario.getAmonestaciones(), false), usuario.getAmonestaciones() + 1);
                usuario.setAmonestaciones(usuario.getAmonestaciones() + 1);
                
                Main.getReservaSalaPublicaDAO().desasignarBloque(usuario.getDni());
            }
        });
			
		
		thread.start();
	}
}
