package negocio.gestores;


import negocio.entidades.MediosDePago.MDPClase;
import negocio.entidades.Producto;
import negocio.entidades.Venta;

import java.util.ArrayList;

public class GestorVentas {
    private int nrosVenta;
    private ArrayList<Venta> Ventas = null;
    private static GestorVentas instancia = null;

    //Instancia
    private GestorVentas() {
        Ventas = new ArrayList<Venta>();
    }

    public static GestorVentas getInstance() {
        if (instancia == null) {
            instancia = new GestorVentas();
        }
        return instancia;
    }

    public Venta getVentaPorCodigo(int codigo) {
        for (Venta v:Ventas) {
            if (v.getCodigoVenta()==(codigo)) {
                return v;
            }
        }
        return null;
    }

    /**
     * OPCION 2
     *     public void registraVenta(int codigo, float importe, MDP mdp, float importeFinal){
     *         for (Venta v: Ventas) {
     *             if (!Ventas.contains(v.getCodigoVenta() == codigo)){
     *                 Venta v2 = new Venta(codigo,importe,mdp, 0);
     *                 v2.setImporteFinal(MDPClase.calcularImporteTotal());
     *             }
     *         }
     *     }
     */
    /**public void registrarVenta(int codigo, float importe, MDPClase mdp, float importeFinal){
     Venta vntVerificar = getVentaPorCodigo(codigo);
     Venta vntNew = new Venta(codigo,importe, mdp, 0);
     if (vntVerificar == null) {
         vntNew.setCodigoVenta(codigo);
         vntNew.setImporte(importe);
         vntNew.setMdp(mdp);
         vntNew.setImporteFinal(mdp.calcularImporteTotal(vntNew));
         Ventas.add(vntNew);
        }
    }
     **/
    /**public void registrarVenta(int codigo, float importe, MDPClase mdp, float importeFinal) {
        Venta vntReg = getVentaPorCodigo(nrosVenta);
        if (vntReg != null) {
            vntReg.setCodigoVenta(nrosVenta);
            vntReg.setMdp(mdp);
            vntReg.setImporte(GestorProductoVendido.getInstance().totalSumaProductos(nrosVenta));
            vntReg.setImporteFinal(mdp.calcularImporteTotal());
            Ventas.set(Ventas.indexOf(getVentaPorCodigo(codigo)), vntReg);
        }
    }**/

    public ArrayList<Venta> getVentas(){
        ArrayList<Venta> lista = new ArrayList<>();
        for (Venta v:
             Ventas) {
            lista.add(v);
        }
        return lista;
    }

    public void agregar(Venta v){
        Ventas.add(v);
        finalizarVenta();
    }


    public void empezarVenta(){
        Venta vntNew = new Venta(nrosVenta,0,null,0);
    }

    public int getNrosVenta() {
        return nrosVenta;
    }

    public void setNrosVenta(int nrosVenta) {
        this.nrosVenta = nrosVenta;
    }

    public void finalizarVenta(){
        nrosVenta += 1;
    }

    public void cancelarLaVenta(int codigo){
        Ventas.removeIf(v -> v.getCodigoVenta() == codigo);
    }
}
