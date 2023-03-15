package negocio.entidades.MediosDePago;

import negocio.entidades.Venta;
import negocio.gestores.GestorVentas;

import java.util.Date;

public class TD extends MDPClase{
    private static TD instancia = null;

    public TD() {
        super();

    }

    public static TD getInstance() {
        if (instancia == null) {
            instancia = new TD();
        }
        return instancia;
    }

    @Override
    public float calcularImporteTotal(float subtotal, int cuotas) {
        return subtotal;
    }

    @Override
    public String toString() {
        return "Tarjeta de debito";
    }
}
