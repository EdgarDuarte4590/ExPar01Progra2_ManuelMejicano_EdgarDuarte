package modelo;

public class Funcionario extends Persona {
    private String puesto;
    private Vehiculo vehiculo;
   

    public Funcionario(String nombre, int iD, String puesto) {
        super(nombre, iD);
        this.puesto = puesto;
    
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    
}
