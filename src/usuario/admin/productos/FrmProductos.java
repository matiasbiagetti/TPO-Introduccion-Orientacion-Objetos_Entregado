package usuario.admin.productos;

import negocio.entidades.Producto;
import negocio.gestores.GestorProductos;
import util.ModeloTable;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmProductos extends JDialog {
    private JPanel panel1;
    private JList lstProductos;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnAgregar;


    private FrmProductos self;

    public FrmProductos(Window owner, String titulo) {
        super(owner, titulo);

        this.setContentPane(panel1);
        this.setSize(1121, 460);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);
        this.setearListaProductos();


        this.self = this;

    }



    private void setearListaProductos() {
        lstProductos.setListData(GestorProductos.getInstance().getProductos().toArray());
    }

    private void asociarEvento() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FrmAgregarProducto frmAgregar = new FrmAgregarProducto(self, "Agregar Producto");
                //frmAgregar.setVisible(true);
                        agregar();

            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto p;
                p = (Producto) lstProductos.getSelectedValue();
                if (p != null) {
                    GestorProductos.getInstance().eliminarProducto(p);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
                }
                setearListaProductos();

            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar();

            }
        });
    }

    private void agregar() {
        FrmAgregarProducto fag = new FrmAgregarProducto(self, "Agregar Producto");
        fag.setModal(true);
        fag.setVisible(true);
        try {
            int indice = lstProductos.getNextMatch(fag.getProducto().getDescripcion(), 0, Position.Bias.Forward);
            if (indice == -1) {
                GestorProductos.getInstance().cargarProducto(fag.getProducto().getCodigo(), fag.getProducto().getDescripcion(), fag.getProducto().getPrecioUn(), fag.getProducto().getStock(), fag.getProducto().getMinStock());
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un producto con esta descripcion, intente con uno nuevo.");
            }
            lstProductos.setListData(GestorProductos.getInstance().getProductos().toArray());
        } catch(Exception e) {

        }
    }

    private void modificar() {
        Producto p;
        p = (Producto) lstProductos.getSelectedValue();
        if (p != null) {
            FrmModificarProducto fmod = new FrmModificarProducto(self, "Modificar Producto", (Producto) lstProductos.getSelectedValue());
            fmod.setModal(true);
            fmod.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
        }
        setearListaProductos();


        lstProductos.setListData(GestorProductos.getInstance().getProductos().toArray());

    }


}



