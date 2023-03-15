package negocio.entidades;

import negocio.entidades.MediosDePago.MDPClase;

public class Venta {
    private float importe;
    private int codigoVenta;
    private MDPClase mdp;
    private float importeFinal;

    public Venta(int codigoVenta, float importe, MDPClase mdp, float importeFinal) {
        this.importe = importe;
        this.codigoVenta = codigoVenta;
        this.mdp = mdp;
        this.importeFinal = importeFinal;
    }

    public float getImporte() {
        return importe;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public MDPClase getMdp() {
        return mdp;
    }

    public float getImporteFinal() {
        return importeFinal;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public void setMdp(MDPClase mdp) {
        this.mdp = mdp;
    }

    public void setImporteFinal(float importeFinal) {
        this.importeFinal = importeFinal;
    }

    public String toString(){
        return String.valueOf(this.codigoVenta)+ " - " +mdp.toString() + " - Precio total de lista: $" + this.getImporte() + " - Precio final: $" + this.getImporteFinal();
    }


}
