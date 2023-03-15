package negocio.gestores;

import negocio.entidades.Producto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GestorProductos {
    private int nrosProducto = 1;
    private ArrayList<Producto> Productos = null;
    private static GestorProductos instancia = null;

    //Instancia
    private GestorProductos() {
        Productos = new ArrayList<Producto>();
        setNrosProducto();
    }

    public static GestorProductos getInstance() {
        if (instancia == null) {
            instancia = new GestorProductos();
        }
        return instancia;
    }

    public Producto getProductoPorCodigo(int codigo) {
        for (Producto p:Productos) {
            if (p.getCodigo()==(codigo)) {
                return p;
            }
        }
        return null;
    }

    public Producto getProductoPorDescripcion(String descripcion){
        for (Producto p: Productos) {
            if (p.getDescripcion() == descripcion){
                return p;
            }
        }
        return null;
    }

    public void cargarProducto(int codigo, String descripcion, float precioUnitario, int cantidadStock, int stockMinimo){
        Producto prodVerificar = getProductoPorCodigo(codigo);
        Producto prodVerificar2 = getProductoPorDescripcion(descripcion);
        Producto prodNew = new Producto(codigo, descripcion, precioUnitario,  cantidadStock,stockMinimo);
        if (prodVerificar == null && prodVerificar2 == null) {
            //prodNew.setCodigo(codigo);
            prodNew.setCodigo(codigo);
            prodNew.setDescripcion(descripcion);
            prodNew.setPrecioUn(precioUnitario);
            prodNew.setStock(cantidadStock);
            prodNew.setMinStock(stockMinimo);
            agregar(prodNew);
        }
        else{
            JOptionPane.showMessageDialog(null, "Ya existe un producto con est descripcion.");
        }
    }

    public void agregar(Producto p){
        Productos.add(p);
        sumarNroProducto();
    }


    public void modificarProducto(int codigo, String descripcion, float precioUnitario, int cantidadStock, int stockMinimo){
        Producto prodMod = getProductoPorCodigo(codigo);
        if (prodMod != null){
            prodMod.setCodigo(codigo);
            prodMod.setDescripcion(descripcion);
            prodMod.setPrecioUn(precioUnitario);
            prodMod.setStock(cantidadStock);
            prodMod.setMinStock(stockMinimo);
            Productos.set(Productos.indexOf(getProductoPorCodigo(codigo)), prodMod);
        }
}

    public void eliminarProducto(Producto producto){
        Productos.removeIf(p -> p == producto);
    }

    public ArrayList<String> nombresDeProductos(){
        ArrayList<String> lista = new ArrayList<String>();
        for (Producto p:Productos) {
            lista.add(p.getDescripcion());
        }
        return lista;
    }

    public void restarStockAgregado(int cantidad, String descripcion){
        Producto producto = getProductoPorDescripcion(descripcion);
        producto.setStock(producto.getStock()-cantidad);
    }

    public ArrayList<Producto> getProductos(){
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto p:
             Productos) {
            lista.add(p);

        }
        return lista;
    }

    public int getNrosProducto() {
        return nrosProducto;
    }

    public void sumarNroProducto(){
        nrosProducto += 1;
    }

    public ArrayList<Producto> getProductosSinStock() {
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto p :
                Productos) {
            if (p.checkMinStock()) {
                lista.add(p);
            }

        }
        return lista;
    }

    public ArrayList<Producto> getProductosenStock() {
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto p :
                Productos) {
            if (p.getStock() > p.getMinStock()) {
                lista.add(p);
            }

        }
        return lista;
    }

    public float getPrecioPorCodigo(int codigo) {
        float precio = 0;
        for (Producto p :
                Productos) {
            if (p.getCodigo() == codigo) {
                precio = p.getPrecioUn();
            }

        }
        return precio;
    }

    private void setNrosProducto(){

        this.nrosProducto = Productos.size() +1;
    }

}








