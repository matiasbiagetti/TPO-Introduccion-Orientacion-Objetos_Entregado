package negocio.entidades.MediosDePago;

public class Efectivo extends MDPClase{

    private static Efectivo instancia = null;

    public Efectivo() {
        super();

    }

    public static Efectivo getInstance() {
        if (instancia == null) {
            instancia = new Efectivo();
        }
        return instancia;
    }

    public float calcularImporteTotal(float subtotal, int cuotas) {
        return (float) (subtotal - subtotal*0.1);

    }

    @Override
    public String toString() {
        return "Efectivo";
    }
}
