package usuario.comprador;

import negocio.gestores.GestorVentas;
import usuario.comprador.compra.FrmAgregarProductoACarrito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenuComprador extends JDialog{
    private JButton btnComprar;
    private JPanel panel1;
    private JButton btnCatalogo;
    private JLabel lblMenuComprador;

    private FrmMenuComprador self;

    public FrmMenuComprador(Window owner, String titulo) {
        super(owner, titulo);

        this.setContentPane(panel1);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);

        this.self = this;

    }
    private void asociarEvento(){
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAgregarProductoACarrito frame = new FrmAgregarProductoACarrito(self, "Agregar Producto a Carrito");
                GestorVentas.getInstance().empezarVenta();
                frame.setVisible(true);
            }
        });
    }
}
