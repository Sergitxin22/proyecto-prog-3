package domain;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import utils.Utils;

public class ImageCellRenderer extends JLabel implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7780014438006723528L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String 	ubicacionImagen ="books/small/" + value;
		ImageIcon iconoSeccion = Utils.loadImage(ubicacionImagen,50,74);
	    JLabel iconLabel = new JLabel(iconoSeccion);
		//System.out.println(value);
		
		return iconLabel;
	}

}
