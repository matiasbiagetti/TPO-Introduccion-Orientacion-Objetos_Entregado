package util;


import javax.swing.table.DefaultTableModel;

public class ModeloTable extends DefaultTableModel {

    String [] titulos;
    Object [][] datos;

    public ModeloTable (Object[][] datos, String[] titulos){
        super();
        this.titulos = titulos;
        this.datos = datos;
        setDataVector(datos,titulos);
    }
     public ModeloTable(){

     }
     public boolean isCellEditable(int row, int column){
        if (column == utilidades.CODIGO){
            return false;
        }
        else{
            return false;
        }
     }

}
