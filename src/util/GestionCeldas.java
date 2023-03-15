package util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;

public class GestionCeldas extends DefaultTableCellRenderer {
    private String tipo="text";

    private Font normal = new Font("Verdana", Font.PLAIN,12);
    private Font bold = new Font("Verdana", Font.BOLD, 12);

    public GestionCeldas(){

        }
    public GestionCeldas(String tipo){
        this.tipo = tipo;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        //Controlar la tabla
        Color colorFondo = null;
        Color colorFondoPorDefecto = new Color(192,192,192);
        Color colorFondoSeleccionado = new Color(140,140,140);
        Color colorFondoBajoStock = new Color(172, 9, 9);

        if (selected) {
            this.setBackground(colorFondoPorDefecto);
        }
        else{
            this.setBackground(Color.white);
        }
        if (tipo.equals("texto")){
            if (isFocusable()) {
                colorFondo = colorFondoSeleccionado;

            }
            else{
                colorFondo = colorFondoPorDefecto;
            }
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value);
            this.setBackground((selected)? colorFondo :Color.WHITE);
            return this;
        }
        if(tipo.equals("numerico")){
            if(isFocusable()){
                colorFondo = colorFondoSeleccionado;
            }else{
                colorFondo = colorFondoPorDefecto;
            }
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value) ;
            this.setForeground((selected)? new Color(255,255,255) : new Color(32,117,32));
            this.setBackground((selected)? colorFondo: Color.WHITE);
            this.setFont(bold);
            return this;
        }
        return this;
    }


}
