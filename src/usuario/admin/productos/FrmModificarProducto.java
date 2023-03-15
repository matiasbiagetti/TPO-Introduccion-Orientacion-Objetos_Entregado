package usuario.admin.productos;

import negocio.entidades.Producto;
import negocio.gestores.GestorProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModificarProducto extends JDialog{
    private JPanel panel1;
    private JLabel lblCodigo;
    private JLabel lblDescripcion;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JLabel lblPrecio;
    private JTextField txtStock;
    private JTextField txtStockMin;
    private JLabel lblStockMin;
    private JLabel lblStock;
    private JButton btnModificar;
    private JButton btnCancelar;
    private JLabel lblModificarProdcuto;
    private JTextField txtCod;
    private Producto objProducto;

    public FrmModificarProducto(Window owner, String title, Producto p) {
        super(owner, title);
        this.setContentPane(panel1);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);
        objProducto = p;
        setearCampos();



    }




    private void setearCampos(){
        txtCod.setText(String.valueOf(objProducto.getCodigo()));
        txtDescripcion.setText(objProducto.getDescripcion());
        txtPrecio.setText(String.valueOf(objProducto.getPrecioUn()));
        txtStock.setText(String.valueOf(objProducto.getStock()));
        txtStockMin.setText(String.valueOf(objProducto.getMinStock()));
    }

    private void asociarEvento(){
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptar();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });


    }

    public Producto getProducto(){
        return this.objProducto;
    }

    private void aceptar(){
        try{
        GestorProductos.getInstance().modificarProducto(objProducto.getCodigo(),txtDescripcion.getText(),Float.parseFloat(txtPrecio.getText()),Integer.parseInt(txtStock.getText()),Integer.parseInt(txtStockMin.getText()));
        this.setVisible(false);}
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Debe completar correctamente todos los campos");
        }
    }

    private void cancelar(){
        this.setVisible(false);
    }
}
