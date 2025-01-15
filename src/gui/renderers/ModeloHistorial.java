package gui.renderers;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import db.LibroDTO;
import domain.Usuario;
import main.Main;

public class ModeloHistorial extends AbstractTableModel {
	private static final long serialVersionUID = -2132890663934147336L;
	private static String[] cabeceras = {"Portada", "Título", "Autor"};
	private static ArrayList<LibroDTO> historialUsuario;
	
	public ModeloHistorial() {
		Usuario usuario = Main.getUsuario();
		historialUsuario = Main.getLibroDAO().getHistorialByCliente(usuario.getDni());
	}
	
	@Override
	public int getRowCount() {
		return historialUsuario.size();
	}

	@Override
	public int getColumnCount() {
		return cabeceras.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return historialUsuario.get(rowIndex).getIsbn();	
		} else if (columnIndex == 1) {
			return historialUsuario.get(rowIndex).getTitulo();
		} else {
			return historialUsuario.get(rowIndex).getAutor();
		}
	}

	@Override
	public String getColumnName(int column) {
		return cabeceras[column];
	}
}