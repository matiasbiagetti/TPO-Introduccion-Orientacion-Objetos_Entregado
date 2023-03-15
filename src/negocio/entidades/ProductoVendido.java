package negocio.entidades;

import negocio.gestores.GestorProductos;

public class    ProductoVendido {
    private int codVenta;
    private int codProducto;
    private int cantidad;
    private float precioSuma;

    public ProductoVendido(int cv, int cp, float precioSuma, int cantidad) {
        this.codVenta = cv;
        this.codProducto = cp;
        this.precioSuma = precioSuma;
        this.cantidad = cantidad;
    }




    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public int getCodVenta() {
        return codVenta;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public float getPrecioT() {
        return precioSuma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String toString(){
        return GestorProductos.getInstance().getProductoPorCodigo(getCodProducto()).getDescripcion() + " - Cantidad: " + getCantidad();
    }

    public String getDescripcion(){
        return GestorProductos.getInstance().getProductoPorCodigo(this.getCodProducto()).getDescripcion();

    }


}
