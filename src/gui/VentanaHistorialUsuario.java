package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BiblioTech.Cliente;
import BiblioTech.Seccion;
import BiblioTech.Usuario;
import domain.ButtonCellRenderer;
import domain.ImageCellEditor;
import domain.ImageCellRenderer;

public class VentanaHistorialUsuario extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaHistorialUsuario() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640,480);
		setTitle("BiblioTech - Historial");
		setLocationRelativeTo(null);
		
		//panel arriba
		JPanel header = new Header(Seccion.BIBLIOTECA, new Cliente());
		add(header,BorderLayout.NORTH);
		
		//Panel contenedor
		String [][] tablaHistorial = new String [200][2];
				
		for (int i = 0; i < 200; i++) {
			int numLibro = i+1;
			String[] libro = {numLibro + ".jpg" , "Descripcion libro " +numLibro};
			tablaHistorial[i] = libro;
		};
		
		//String[] datos = {"Libro","Descripcion","review"};
		DefaultTableModel modeloTablaHistorial = new DefaultTableModel();
		modeloTablaHistorial.addColumn("Libro");
		modeloTablaHistorial.addColumn("Descripcion");
		modeloTablaHistorial.addColumn("review");
		
		for (int j = 0; j < tablaHistorial.length; j++) {
			modeloTablaHistorial.addRow(tablaHistorial[j]);
		}
		
		JTable historial = new JTable(modeloTablaHistorial);
		historial.setRowHeight(76);
		JScrollPane scrollPane = new JScrollPane(historial);
		add(scrollPane);
		
		historial.getColumnModel().getColumn(0).setCellRenderer(new ImageCellRenderer());
		historial.getColumnModel().getColumn(0).setCellEditor(new ImageCellEditor());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaHistorialUsuario ventana = new VentanaHistorialUsuario();
	}

}
