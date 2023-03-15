package negocio.entidades;

public class Producto {
    private int codigo;
    private String descripcion;
    private float precioUn;
    private int stock;
    private int minStock;

    public Producto(int codigo, String descripcion, float precioUn, int stock, int minStock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUn = precioUn;
        this.stock = stock;
        this.minStock = minStock;
    }


    public boolean checkMinStock() {
        return (this.minStock >= this.stock);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioUn() {
        return precioUn;
    }

    public void setPrecioUn(float precioUn) {
        this.precioUn = precioUn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }


    public String toString(){
        return this.getDescripcion() ;
    }



}
