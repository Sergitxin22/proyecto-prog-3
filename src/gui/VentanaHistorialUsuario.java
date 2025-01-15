package gui;

import gui.components.Header;
import gui.renderers.*;
import main.Main;
import domain.Cliente;
import domain.Seccion;
import domain.Usuario;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaHistorialUsuario extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario = Main.getUsuario();

	public VentanaHistorialUsuario() {
		if (!(usuario instanceof Cliente)) {
			return ;
		}
		setSize(640,480);
		setTitle("Historial de libros");
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
				dispose();
            }
});
		
		//panel arriba
		JPanel header = new Header(Seccion.BIBLIOTECA, usuario, this);
		add(header,BorderLayout.NORTH);
		
		ModeloHistorial modeloTablaHistorial = new ModeloHistorial();
		
		JTable historial = new JTable(modeloTablaHistorial);
		historial.setRowHeight(76);
		historial.setRowSelectionAllowed(false);
		JScrollPane scrollPane = new JScrollPane(historial);
		historial.getTableHeader().setReorderingAllowed(false);
		add(scrollPane);
		
		historial.getColumnModel().getColumn(0).setCellRenderer(new ImageCellRenderer());
		historial.getColumnModel().getColumn(0).setCellEditor(new ImageCellEditor());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaHistorialUsuario();
	}

}
