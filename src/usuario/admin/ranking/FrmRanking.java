package usuario.admin.ranking;

import negocio.gestores.GestorProductoVendido;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FrmRanking extends JDialog{
    private JPanel panel1;
    private JLabel lsblRanking;
    private JList lstRanking;
    private JScrollPane scrollPanel;

    public FrmRanking(Window owner, String titulo) {
        super(owner, titulo);

        this.setContentPane(panel1);
        this.setSize(1121, 460);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);
        setearRanking();

    }



    private void asociarEvento(){



    }

    private void setearRanking(){
        lstRanking.setListData(GestorProductoVendido.getInstance().buscarProductosMasVendidos().toArray());


    }











}
