package util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class GestionEncabezadoTabla implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent jcomponent = null;

        if( value instanceof String ) {
            jcomponent = new JLabel((String) value);
            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.CENTER );
            ((JLabel)jcomponent).setSize( 30, jcomponent.getWidth() );
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth())  );
        }
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0,1,1,1, Color.black));
         jcomponent.setOpaque(true);
         jcomponent.setBackground(new Color(46, 61, 61));
         jcomponent.setToolTipText("Catalogo de productos");
         jcomponent.setForeground(Color.white);

        return jcomponent;
    }
}
