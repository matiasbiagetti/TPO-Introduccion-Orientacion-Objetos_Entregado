package usuario.admin.productos;

import negocio.entidades.Producto;
import negocio.gestores.GestorProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class FrmAgregarProducto extends JDialog{
    private JPanel panel1;
    private JLabel lblDescripcion;
    private JTextField txtDescripcion;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JLabel lblStock;
    private JTextField txtStock;
    private JTextField txtStockMin;
    private JButton agregarButton;
    private JButton cancelarButton;
    private Producto objProducto;



    public FrmAgregarProducto(Window owner, String title) {
        super(owner, title);
        this.setContentPane(panel1);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);



    }



    private void asociarEvento(){
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptar();

            }
        });
        getRootPane().setDefaultButton(agregarButton);
        cancelarButton.addActionListener(new ActionListener() {
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
        try {
            Producto prodVerificar = GestorProductos.getInstance().getProductoPorDescripcion(txtDescripcion.getText());
            if (prodVerificar == null) {
                objProducto = new Producto(GestorProductos.getInstance().getNrosProducto(), txtDescripcion.getText(), Float.parseFloat(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), Integer.parseInt(txtStockMin.getText()));
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un producto con est descripcion.");
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debe completar correctamente todos los campos");
        }
    }

    private void cancelar(){
        this.objProducto=null;
        this.setVisible(false);
    }
}
