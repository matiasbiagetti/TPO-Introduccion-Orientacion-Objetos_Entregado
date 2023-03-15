package negocio.entidades.MediosDePago;

import negocio.entidades.Venta;
import negocio.gestores.GestorVentas;

import java.util.Date;

public class TC extends MDPClase {
    private static TC instancia = null;

    public TC() {
        super();

    }

    public static TC getInstance() {
        if (instancia == null) {
            instancia = new TC();
        }
        return instancia;
    }



    @Override
    public String toString() {
        return "Tarjeta de Credito";
    }

    @Override
    public float calcularImporteTotal(float subtotal, int cuotas) {
        float total = 0;
        if (cuotas == 1){
            return subtotal;
        }
        if (cuotas == 2){
            total = (float) (subtotal  + cuotas * ( subtotal* 0.06));
        }
        else if (cuotas == 3){
            total = (float) (subtotal  + cuotas * (subtotal*0.12));
        }
        else if (cuotas == 6){
            total = (float) (subtotal + cuotas * (subtotal * 0.20));
        }
        return total;
    }
}


