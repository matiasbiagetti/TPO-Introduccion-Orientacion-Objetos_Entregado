package usuario.admin;

import usuario.admin.productos.FrmCatalogo;
import usuario.admin.productos.FrmCatalogoSinStock;
import usuario.admin.productos.FrmProductos;
import usuario.admin.ranking.FrmRanking;
import usuario.admin.ventas.FrmVentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenuAdmin extends JDialog {
    private JPanel panel1;
    private JButton btnProductos;
    private JButton btnRanking;
    private JButton btnCatalogo;
    private JPanel lblMenuAdmin;
    private JButton btnGestionVentas;
    private JButton btnCatSinStock;

    private FrmMenuAdmin self;

    public FrmMenuAdmin(Window owner, String titulo) {
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
        btnProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmProductos frmGestProd = new FrmProductos(self, "Gestion de Productos");
                frmGestProd.setVisible(true);
            }
        });

        btnCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCatalogo frmCat = new FrmCatalogo(self, "Catalogo de Productos");
                frmCat.setVisible(true);

            }
        });
        btnGestionVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVentas frmVentas = new FrmVentas(self, "Gestion de Ventas");
                frmVentas.setVisible(true);

            }
        });

        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                FrmRanking frmRank = new FrmRanking(self,"Ranking");
                frmRank.setVisible(true);
                }catch(Exception a){
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado. Intente denuevo mas tarde.");

                }


            }
        });

        btnCatSinStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCatSinStock.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FrmCatalogoSinStock frmCatSS = new FrmCatalogoSinStock(self, "Catalogo de Productos Sin Stock");
                        frmCatSS.setVisible(true);

                    }
                });
            }
        });


    }
}
