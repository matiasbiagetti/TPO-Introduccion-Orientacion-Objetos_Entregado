package usuario.admin.ventas;

import negocio.entidades.Venta;
import negocio.gestores.GestorVentas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmVentas extends JDialog {
    private JList lstNrosVenta;
    private JButton cargarVentaButton;
    private JPanel panel1;

    private FrmVentas self;

    public FrmVentas(Window owner, String titulo) {
        super(owner, titulo);

        this.setContentPane(panel1);
        this.setSize(1121, 460);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);
        setearListaVentas();


        this.self = this;

    }




    private void setearListaVentas() {
        lstNrosVenta.setListData(GestorVentas.getInstance().getVentas().toArray());
    }

    private void asociarEvento(){
        cargarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FrmAgregarProducto frmAgregar = new FrmAgregarProducto(self, "Agregar Producto");
                //frmAgregar.setVisible(true);
                agregar();

            }
        });


    }

    private void agregar() {
        FrmAgregarVenta fcv = new FrmAgregarVenta(self, "Cargar Venta");
        fcv.setModal(true);
        fcv.setVisible(true);



        Venta v = fcv.getVenta();
        if(v != null) {
            GestorVentas.getInstance().agregar(v);
        }

        lstNrosVenta.setListData(GestorVentas.getInstance().getVentas().toArray());}


}
