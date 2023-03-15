package usuario.admin.productos;

import negocio.entidades.Producto;
import negocio.gestores.GestorProductos;
import util.GestionCeldas;
import util.GestionEncabezadoTabla;
import util.ModeloTable;
import util.utilidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmCatalogo extends JDialog {

    private JTable tablaProductos;
    private JPanel contentPane;
    private JComboBox cmbFiltro;
    private JButton btnSinStock;
    private JScrollPane scrollPaneTabla;
    ArrayList<Producto> listaProductos;

    ModeloTable modelo;

    private FrmCatalogo self;


    public FrmCatalogo(Window owner, String titulo) {
        super(owner, titulo);

        this.setSize(1600, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);

        iniciarComponentes();
        construirTabla();
        //construirTablaSinStock();



    }


    private void asociarEvento(){
        /**cmbFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbFiltro.getSelectedItem()=="Sin stock"){
                    vaciarTabla();
                    iniciarComponentes();
                    construirTablaSinStock();
                }
                else{
                    vaciarTabla();
                    iniciarComponentes();
                    construirTabla();

                }

            }
        });**/









    }
    private void iniciarComponentes(){
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0,0));

        JButton btnSinStock = new JButton();
        contentPane.add(btnSinStock,BorderLayout.NORTH);
        btnSinStock.setSize(1500,100);

        //JLabel lblTablaProductos = new JLabel("Tabla Productos");
        //lblTablaProductos.setFont(new Font("Rockwell", Font.BOLD, 25));
        //contentPane.add(lblTablaProductos, BorderLayout.NORTH);

        /**cmbFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarTabla();
                construirTablaSinStock();
                System.out.println("se vacio");
            }
        });**/


        scrollPaneTabla = new JScrollPane();
        contentPane.add(scrollPaneTabla);

    }

    private void construirTablaSinStock(){
        listaProductos = consulaListaProductosSinStock();

        ArrayList<String> titulosList = new ArrayList<>();
        titulosList.add("Codigo");
        titulosList.add("Descripcion");
        titulosList.add("Precio Un.");
        titulosList.add("Stock");
        titulosList.add("Stock Min.");

        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length;i++){
            titulos[i]=titulosList.get(i);
        }
        Object[][] data = obtenerMatrizDatosSinStock(titulosList);
        construirTabla(titulos, data);



    }



    private void construirTabla(){
        listaProductos = consultarListaProductos();

        ArrayList<String> titulosList = new ArrayList<>();
        titulosList.add("Codigo");
        titulosList.add("Descripcion");
        titulosList.add("Precio Un.");
        titulosList.add("Stock");
        titulosList.add("Stock Min.");

        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length;i++){
            titulos[i]=titulosList.get(i);
        }
            Object[][] data = obtenerMatrizDatos(titulosList);
            construirTabla(titulos, data);



    }

    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new ModeloTable(data, titulos);
        tablaProductos.setModel(modelo);

        tablaProductos.getColumnModel().getColumn(utilidades.CODIGO).setCellRenderer(new GestionCeldas("numerico"));
        tablaProductos.getColumnModel().getColumn(utilidades.PRECIO).setCellRenderer(new GestionCeldas("numerico"));
        tablaProductos.getColumnModel().getColumn(utilidades.STOCK).setCellRenderer(new GestionCeldas("numerico"));
        tablaProductos.getColumnModel().getColumn(utilidades.STOCKMIN).setCellRenderer(new GestionCeldas("numerico"));
        tablaProductos.getColumnModel().getColumn(utilidades.DESCRIPCION).setCellRenderer(new GestionCeldas("texto"));


        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.setRowHeight(25);
        tablaProductos.setGridColor(new java.awt.Color(0, 0, 0));

        tablaProductos.getColumnModel().getColumn(utilidades.CODIGO).setPreferredWidth(130);
        tablaProductos.getColumnModel().getColumn(utilidades.DESCRIPCION).setPreferredWidth(380);
        tablaProductos.getColumnModel().getColumn(utilidades.PRECIO).setPreferredWidth(130);
        tablaProductos.getColumnModel().getColumn(utilidades.STOCK).setPreferredWidth(130);
        tablaProductos.getColumnModel().getColumn(utilidades.STOCKMIN).setPreferredWidth(130);

        JTableHeader jtableHeader = tablaProductos.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        tablaProductos.setTableHeader(jtableHeader);

        scrollPaneTabla.setViewportView(tablaProductos);


    }

    private void vaciarTabla(){
        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }

    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList){
        String informacion[][] = new String[listaProductos.size()][titulosList.size()];
        for (int x = 0; x < informacion.length; x++){
            informacion[x] [utilidades.CODIGO] = listaProductos.get(x).getCodigo() + "";
            informacion[x] [utilidades.DESCRIPCION] = listaProductos.get(x).getDescripcion() + "";
            informacion[x] [utilidades.PRECIO] = listaProductos.get(x).getPrecioUn() + "";
            informacion[x] [utilidades.STOCK] = listaProductos.get(x).getStock() + "";
            informacion[x] [utilidades.STOCKMIN] = listaProductos.get(x).getMinStock() + "";
        }
        return informacion;
    }

    private Object[][] obtenerMatrizDatosSinStock(ArrayList<String> titulosList){
        String informacion[][] = new String[listaProductos.size()][titulosList.size()];
        for (int x = 0; x < informacion.length; x++){
            if (listaProductos.get(x).getStock()<= listaProductos.get(x).getMinStock()) {
                informacion[x][utilidades.CODIGO] = listaProductos.get(x).getCodigo() + "";
                informacion[x][utilidades.DESCRIPCION] = listaProductos.get(x).getDescripcion() + "";
                informacion[x][utilidades.PRECIO] = listaProductos.get(x).getPrecioUn() + "";
                informacion[x][utilidades.STOCK] = listaProductos.get(x).getStock() + "";
                informacion[x][utilidades.STOCKMIN] = listaProductos.get(x).getMinStock() + "";
            }
        }
        return informacion;
    }

    private ArrayList<Producto> consultarListaProductos(){
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto p:
                GestorProductos.getInstance().getProductos()) {
            lista.add(p);
        }

        return lista;
    }

    private ArrayList<Producto> consulaListaProductosSinStock(){
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto p:
                GestorProductos.getInstance().getProductosSinStock()) {
            lista.add(p);
        }

        return lista;
    }

}

