package negocio.entidades.MediosDePago;

public  class MDPClase {
    private static MDPClase instancia = null;


    public MDPClase() {

    }

    public static MDPClase getInstance() {
        if (instancia == null) {
            instancia = new MDPClase();
        }
        return instancia;
    }



    public  float calcularImporteTotal(float subtotal, int cuotas){
        return 0;
    }

}
