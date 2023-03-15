package usuario.comprador.compra;

import com.sun.jdi.IntegerValue;
import negocio.gestores.GestorProductoVendido;
import negocio.gestores.GestorProductos;
import negocio.gestores.GestorVentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmAgregarProductoACarrito extends JDialog {
    private JPanel panel1;
    private JTextField txtNroVenta;
    private JButton cancelarButton1;
    private JButton irAPagarButton;
    private JButton agregarOtroButton1;
    private JComboBox cmbProductoElegido;
    private JLabel lblPrecioParcial;
    private JLabel lblNroVenta;
    private JLabel lblProducto;
    private JLabel lblCantidad;
    private JLabel lblAnotacionPrecio;
    private JLabel lblSigno;
    private JLabel lblTituloSeleccionarProductos;
    private JComboBox cmbCantidad;
    private JLabel lblStock;
    private JLabel lblEtiquetaStockDisponible;

    private FrmAgregarProductoACarrito self;

    public FrmAgregarProductoACarrito(Window owner, String titulo) {
        super(owner, titulo);

        this.setContentPane(panel1);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);
        this.setearComboBox(GestorProductos.getInstance().nombresDeProductos());
        this.setearNroVenta();
        this.setearLblPrecio();
        this.setearLblStock();
        this.self = this;



    }

    private void setearLblStock(){
        lblStock.setText(String.valueOf(GestorProductos.getInstance().getProductoPorDescripcion((String) cmbProductoElegido.getSelectedItem()).getStock()));
    }
    private void setearComboBoxCantidad(int max){
    for (int i = 1;i<= max;i++){
        cmbCantidad.addItem(i);
    }
        }
    private void setearNroVenta(){
        txtNroVenta.setText(String.valueOf(GestorVentas.getInstance().getNrosVenta()));
    }
    private void setearComboBox(ArrayList<String> a){
        for (String producto:a) {
            cmbProductoElegido.addItem(producto);

        }
    }
    private void setearLblPrecio(){
        lblPrecioParcial.setText(String.valueOf((GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(cmbProductoElegido.getSelectedItem())).getStock())*(Integer) cmbCantidad.getSelectedItem()));
    }



    private void asociarEvento(){
        cmbProductoElegido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setearComboBoxCantidad(GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(cmbProductoElegido.getSelectedItem())).getStock());
                setearLblPrecio();
                setearLblStock();

            }
        });
        cmbCantidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setearLblPrecio();
            }
        });

        agregarOtroButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(GestorVentas.getInstance().getNrosVenta());
                GestorProductos.getInstance().restarStockAgregado(((Integer) cmbCantidad.getSelectedItem()),String.valueOf(cmbProductoElegido.getSelectedItem()));
               // GestorProductoVendido.getInstance().agrgarProductoVendido(Integer.parseInt(txtNroVenta.getText()),String.valueOf(cmbProductoElegido.getSelectedItem()),((Integer) cmbCantidad.getSelectedItem()));
                setearComboBoxCantidad(GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(cmbProductoElegido.getSelectedItem())).getStock());
                setearLblPrecio();
                setearLblStock();
                System.out.println(GestorVentas.getInstance().getNrosVenta());

            }
        });

    }
}
