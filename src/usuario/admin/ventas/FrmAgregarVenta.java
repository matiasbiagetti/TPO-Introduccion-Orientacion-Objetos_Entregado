package usuario.admin.ventas;

import negocio.entidades.MediosDePago.Efectivo;
import negocio.entidades.MediosDePago.MDPClase;
import negocio.entidades.MediosDePago.TC;
import negocio.entidades.MediosDePago.TD;
import negocio.entidades.Producto;
import negocio.entidades.ProductoVendido;
import negocio.entidades.Venta;
import negocio.gestores.GestorProductoVendido;
import negocio.gestores.GestorProductos;
import negocio.gestores.GestorVentas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmAgregarVenta extends JDialog{
    private JButton btnAgregarOtro;
    private JButton btnFinalizar;
    private JTextField txtProducto;
    private JComboBox cmbCantidad;
    private JComboBox cmbMDP;
    private JComboBox cmbCuotas;
    private JList lstProductosAgregados;
    private JTextField txtNroVenta;
    private JLabel lblTotal;
    private JPanel panel2;
    private JPanel panel1;
    private JScrollPane scrollSinStock;
    private JScrollPane scrollDisponibles;
    private JList lstConStock;
    private JList lstSinStock;
    private JLabel lblPrecioUn;
    private JButton btnCancelar;
    private JLabel lblTotalConf;
    private JButton eliminarProductoButton;

    private ProductoVendido objPvEnCurso;
    private int nroVenta = GestorVentas.getInstance().getNrosVenta();
    private MDPClase mdp = new MDPClase();


    private Venta objVenta;
    private ProductoVendido objPv;

    public FrmAgregarVenta(Window owner, String titulo) {
        super(owner, titulo);

        this.setContentPane(panel1);
        this.setContentPane(panel2);
        this.setSize(1121, 460);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.asociarEvento();
        this.setModal(true);
        inicializarCmbCuotas();

        txtNroVenta.setText(String.valueOf(GestorVentas.getInstance().getNrosVenta()));
        inicializarTodosLosComponentes();



    }


    public Venta getVenta(){
        return this.objVenta;
    }


    private void asociarEvento(){
        //Boton cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        //Boton agregar otro producto
        btnAgregarOtro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOtroProducto();

            }
        });

        //Seleccionar otro producto
        lstConStock.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                seleccionarOtroProductos();

            }
        });

        //Seleccionar otra cantidad
        cmbCantidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarCantidad();

            }
        });

        //Cambiar metodo de pago
        cmbMDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarMedioDePago();

            }
        });

        //Boton finalizar venta
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList arr = new ArrayList();
                for (int i = 0; i < lstProductosAgregados.getModel().getSize(); i++) {
                    arr.add(String.valueOf(lstProductosAgregados.getModel().getElementAt(i)));
                    System.out.println(arr);
                }
                if (arr.size() != 0 ) {
                    finalizarVenta();
                    GestorProductoVendido.getInstance().buscarProductosMasVendidos();
                    System.out.println(GestorProductoVendido.getInstance().getProductosVendidos());
                }
                else {
                    JOptionPane.showMessageDialog(null,"No puede hacer una venta sin seleccionar productos.");
                }

            }
        });

        //Cambiar nro de cuotas
        cmbCuotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarMedioDePago();
            }
        });

        //Boton eliminar
        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDeAgregados();
                setearListaProductosAgregados();
                seleccionarOtroProductos();


            }
        });



    }

    //LISTA DE PRODUCTOS CON STOCK
    private void setearListaProdDisponibles(){
        lstConStock.setListData(GestorProductos.getInstance().getProductosenStock().toArray());
        lstConStock.setSelectedIndex(0);
    }

    //LISTA DE PRODUCTOS PRODUCTOS SIN STOCK
    private void setearListaProdSinStock(){
        lstSinStock.setListData(GestorProductos.getInstance().getProductosSinStock().toArray());
        lstSinStock.setSelectedIndex(0);
    }

    //LISTA DE PRODUCTOS YA AGREGADOS
    private void setearListaProductosAgregados(){
        for (ProductoVendido pv:
              GestorProductoVendido.getInstance().getProductosVendidos()) {
            if (pv.getCodVenta() == nroVenta){
                lstProductosAgregados.setListData((GestorProductoVendido.getInstance().productosDeUnaVenta(nroVenta)).toArray());

            }

        }
    }

    //COMBOBOX DE CANTIDADES
    private void setCantidades(){
        cmbCantidad.removeAllItems();
        cmbCantidad.setEnabled(true);
        try {
            for (int i = 1; i <= GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(lstConStock.getSelectedValue())).getStock(); i++) {
                cmbCantidad.addItem(i);
            }
        }catch (Exception e){
            cmbCantidad.setEnabled(false);
        }
        }

    //LBL DEL TOTAL CON SELECCIONADO
    private void setearLblTotal() {
        float total = 0;
        total = totalConMDPSeleccionado();
        lblTotal.setText(String.valueOf(total));
    }

    //LBL DEL TOTAL CONFIRMADO
    private void setearLblTotalConfirmado() {
        float total = 0;
        total = totalConMDPConfirmado();
        lblTotalConf.setText(String.valueOf(total));
    }


    //CAPTAR MDP
    private float totalConMDPSeleccionado(){

        if (cmbMDP.getSelectedItem() == "Efectivo"){
            mdp = new Efectivo();
        }
        if (cmbMDP.getSelectedItem() == "Tarjeta de credito"){
            mdp = new TC();
        }
        if (cmbMDP.getSelectedItem() == "Tarjeta de debito"){
            mdp = new TD();
        }
        return mdp.calcularImporteTotal(subtotalAgregadosYSeleccionado(), (Integer) cmbCuotas.getSelectedItem()) ;

    }

    private float totalConMDPConfirmado(){

        if (cmbMDP.getSelectedItem() == "Efectivo"){
            mdp = new Efectivo();
        }
        if (cmbMDP.getSelectedItem() == "Tarjeta de credito"){
            mdp = new TC();
        }
        if (cmbMDP.getSelectedItem() == "Tarjeta de debito"){
            mdp = new TD();
        }
        return mdp.calcularImporteTotal(GestorProductoVendido.getInstance().sumarProductosVendidosDeVenta(nroVenta), (Integer) cmbCuotas.getSelectedItem()) ;

    }



    private void setearTxtProducto(){
        try{
        txtProducto.setText(lstConStock.getSelectedValue().toString());}
        catch (Exception e){
            txtProducto.setText("");
        }
    }

    private void inicializarCmbCuotas(){
        cmbCuotas.addItem(1);
        cmbCuotas.addItem(2);
        cmbCuotas.addItem(3);
        cmbCuotas.addItem(6);
    }

    private float obtenerPrecioDelSeleccionado(){
        float precioUn = 0;
        try {
            precioUn = GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(lstConStock.getSelectedValue())).getPrecioUn();
            return precioUn;
        }catch(Exception e){
            return 0;
        }
    }

    private void setearLblPrecioUn(){
        lblPrecioUn.setText(String.valueOf(obtenerPrecioDelSeleccionado()));
    }

    private void inicializarTodosLosComponentes(){
        setearListaProdDisponibles();
        setearListaProdSinStock();
        setearListaProductosAgregados();
        setearTxtProducto();
        setCantidades();
        setearLblPrecioUn();
        setearLblTotal();
        setearLblTotalConfirmado();

    }

    private void seleccionarOtroProductos(){
        setCantidades();
        setearLblPrecioUn();
        setearLblTotal();
        setearLblTotalConfirmado();
        setearTxtProducto();

    }

    private void cambiarCantidad(){
        setearLblTotal();
        setearLblTotalConfirmado();
    }

    private void cambiarMedioDePago(){
        setearLblTotal();
        setearLblTotalConfirmado();
        checkHabilitarCuotas();
    }

    private void agregarOtroProducto(){
        try{
            objPvEnCurso = new ProductoVendido(nroVenta,GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(lstConStock.getSelectedValue())).getCodigo(),(Integer) cmbCantidad.getSelectedItem()*GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(lstConStock.getSelectedValue())).getPrecioUn(),(Integer) cmbCantidad.getSelectedItem());
            //resto el stock
            GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(lstConStock.getSelectedValue())).setStock(GestorProductos.getInstance().getProductoPorDescripcion(String.valueOf(lstConStock.getSelectedValue())).getStock()-(Integer) cmbCantidad.getSelectedItem());
            //Agregar el PV
            agregarPv();
            //inicializo denuevo los componentes
            inicializarTodosLosComponentes();

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debe completar correctamente todos los campos");
        }
    }

    private void cancelar(){


        GestorProductoVendido.getInstance().cancelarLaVenta(Integer.parseInt(txtNroVenta.getText()));
        GestorVentas.getInstance().cancelarLaVenta(nroVenta);

        this.setVisible(false);
    }

    private void checkHabilitarCuotas(){
        if ((String) cmbMDP.getSelectedItem() == "Tarjeta de credito"){
            cmbCuotas.setEnabled(true);
        }else {
            cmbCuotas.setSelectedIndex(0);
            cmbCuotas.setEnabled(false);
        }
    }

    public ProductoVendido getProductoVendido(){
        return this.objPvEnCurso;
    }

    private void agregarPv(){
        ProductoVendido pv = getProductoVendido();
        GestorProductoVendido.getInstance().agregar(pv);
    }

    private void finalizarVenta(){
        try{
            objVenta = new Venta(nroVenta,GestorProductoVendido.getInstance().sumarProductosVendidosDeVenta(nroVenta), mdp , totalConMDPConfirmado());
            this.setVisible(false);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        }
    }


    private float subtotalAgregadosYSeleccionado(){
        float subtotalAS = 0;
        try {
            subtotalAS = GestorProductoVendido.getInstance().sumarProductosVendidosDeVenta(nroVenta);
            subtotalAS += obtenerPrecioDelSeleccionado() * ((Integer) cmbCantidad.getSelectedItem());
            return subtotalAS;
        }catch(Exception e){
            return 0;
        }

    }

    private void eliminarDeAgregados() {
        int cantidad;
        int cp;
        //devuelvo el stock
        try {
        cantidad = ((ProductoVendido) lstProductosAgregados.getSelectedValue()).getCantidad();
        cp = ((ProductoVendido) lstProductosAgregados.getSelectedValue()).getCodProducto();
            GestorProductos.getInstance().getProductoPorCodigo(cp).setStock(GestorProductos.getInstance().getProductoPorCodigo(cp).getStock() + cantidad);
            GestorProductoVendido.getInstance().eliminarPV(lstProductosAgregados.getSelectedValue());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No hay ningun producto seleccionado para eliminar de la venta");
        }
    }

    //BACKLOG

    //METODO DE PAGO  - LISTO
    //MDP - PARA TENER EL TOTAL + SELECCIONADO CALCUALR EL TOTAL Y PASAR POR PARAMETRO EL SELECCIONADO?
    //QUE DEVUELVA EL STOCK CUANDO CANCELA - LISTO
    //QUE CAMBIE EL NUMERO DE VENTA - LISTO
    //ELIMINAR PRODUCTO DEL CARRITO?










}
