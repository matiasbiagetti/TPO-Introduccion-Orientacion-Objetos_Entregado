package ejecucion;


import negocio.gestores.GestorProductos;
import usuario.FrmMenuInicio;


public class main {
    public static void main(String[] args) {

        GestorProductos.getInstance().cargarProducto(1,"Jabon",10,100,10);
        GestorProductos.getInstance().cargarProducto(2,"Shampoo",10,150,10);
        GestorProductos.getInstance().cargarProducto(3,"Teclado",10,160,10);
        GestorProductos.getInstance().cargarProducto(4,"Mouse",10,170,10);
        GestorProductos.getInstance().cargarProducto(5,"Escoba",10,180,10);
        GestorProductos.getInstance().cargarProducto(6,"Caramelo",10,190,10);
        GestorProductos.getInstance().cargarProducto(7,"Lavarropas",10,100,10);
        GestorProductos.getInstance().cargarProducto(8,"Dulce de Leche",10,100,10);
        GestorProductos.getInstance().cargarProducto(9,"Telefono",10,100,10);
        GestorProductos.getInstance().cargarProducto(10,"Silla",10,100,10);
        GestorProductos.getInstance().cargarProducto(11,"Sillon",10,100,10);
        GestorProductos.getInstance().cargarProducto(12,"Mesa",10,5,10);









        /**
         Producto p1 = new Producto(1,"Jabon",10,100,10);
         Producto p2 = new Producto(2,"Jabon",10,100,10);
         Producto p3 = new Producto(3,"Jabon",10,100,10);
         Producto p4 = new Producto(4,"Jabon",10,100,10);
         Producto p5 = new Producto(5,"Jabon",10,100,10);
         Producto p6 = new Producto(6,"Jabon",10,100,10);
         Producto p7 = new Producto(7,"Jabon",10,100,10);
         Producto p8 = new Producto(8,"Jabon",10,100,10);
         Producto p9 = new Producto(9,"Jabon",10,100,10);
         Producto p10 = new Producto(10,"Jabon",10,100,10);
         Producto p11 = new Producto(11,"Jabon",10,100,10);**/



         FrmMenuInicio frame = new FrmMenuInicio("Inicio");
         frame.setVisible(true);






    }

    }
