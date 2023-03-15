package usuario;

import negocio.gestores.GestorProductoVendido;
import usuario.admin.FrmMenuAdmin;
import usuario.comprador.FrmMenuComprador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenuInicio extends JFrame{
    private JButton btnAdmin;
    private JPanel panel1;
    private JButton btnComprador;

    private FrmMenuInicio self;

    public FrmMenuInicio(String titulo){
        super(titulo);

        this.setContentPane(panel1);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();

        this.self = this;


    }


    private void asociarEvento(){
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMenuAdmin frame = new FrmMenuAdmin(self, "Menu de Administrador");
                frame.setVisible(true);

            }
        });

        btnComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMenuComprador frame = new FrmMenuComprador(self,"Menu de Compradores");
                frame.setVisible(true);

            }
        });
    }
}
