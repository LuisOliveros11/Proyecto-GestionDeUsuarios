import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BotonTabla extends DefaultTableCellRenderer{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object boton, boolean isSelected, boolean hasFocus,
			int row, int column) {
			if(boton instanceof JButton) {
				return (JButton)boton;
			}
		
		return super.getTableCellRendererComponent(table, boton, isSelected, hasFocus, row, column);
	}
}
