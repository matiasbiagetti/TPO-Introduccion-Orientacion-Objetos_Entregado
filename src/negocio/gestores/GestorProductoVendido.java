package negocio.gestores;

import negocio.entidades.Producto;
import negocio.entidades.ProductoVendido;
import negocio.entidades.Venta;


import java.lang.reflect.Array;
import java.util.*;

public class GestorProductoVendido {

    private ArrayList<ProductoVendido> ProductosVendidos;
    private static GestorProductoVendido instancia = null;


    //Instancia
    private GestorProductoVendido() {
        ProductosVendidos = new ArrayList<>();
    }

    public static GestorProductoVendido getInstance() {
        if (instancia == null) {
            instancia = new GestorProductoVendido();
        }
        return instancia;
    }



    public ArrayList<ProductoVendido> getProductosVendidos() {
        return ProductosVendidos;
    }

    public ArrayList<ProductoVendido> productosDeUnaVenta(int codigoVenta){
        ArrayList<ProductoVendido> lista = new ArrayList<>();
        for (ProductoVendido pv:
        ProductosVendidos) {
            if (pv.getCodVenta() == codigoVenta){
                lista.add(pv);
            }

        }
        return lista;
    }

    public float sumarProductosVendidosDeVenta(int codigoVenta){
        float total = 0;
        for (ProductoVendido pv:
             ProductosVendidos) {
            if (codigoVenta == pv.getCodVenta()){
                total += pv.getCantidad() * GestorProductos.getInstance().getPrecioPorCodigo(pv.getCodProducto());
            }

        }
        return total;
    }

    public void cancelarLaVenta(int codigo){

        for (ProductoVendido pv:
                GestorProductoVendido.getInstance().getProductosVendidos()) {
            if (pv.getCodVenta() == codigo){
                Producto prod = GestorProductos.getInstance().getProductoPorCodigo(pv.getCodProducto());
                GestorProductos.getInstance().getProductoPorCodigo(pv.getCodProducto()).setStock(prod.getStock()+pv.getCantidad());

            }

        }
        ProductosVendidos.removeIf(pv -> pv.getCodVenta() == codigo);
    }

    public void agregar(ProductoVendido pv){
        ProductosVendidos.add(pv);
    }

    public void eliminarPV(Object pv){
        ProductosVendidos.remove(pv);
    }

    public List buscarProductosMasVendidos(){
        Map<String, Integer> map = new HashMap<String , Integer>();
        for (ProductoVendido pv:
             ProductosVendidos) {
                if(map.containsKey(pv.getDescripcion())) {
                    int a = map.get(pv.getDescripcion());
                    map.replace(pv.getDescripcion(), a + pv.getCantidad());
                }
                else{
                    map.put(pv.getDescripcion(), pv.getCantidad());
                }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        list.forEach(System.out::println);

        return list;

    }





}





