package modelo;

public class Estudiante extends Persona {

    private String carnet;
    private String codigoAcceso;

    public Estudiante(String nombre, int iD, String carnet, String codigoAcceso) {
        super(nombre, iD);
        this.carnet = carnet;
        this.codigoAcceso = codigoAcceso;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }


}
