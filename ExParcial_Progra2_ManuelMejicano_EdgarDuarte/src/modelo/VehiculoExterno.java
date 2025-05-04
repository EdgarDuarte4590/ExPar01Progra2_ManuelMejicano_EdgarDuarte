package modelo;

import java.time.LocalDate;

public class VehiculoExterno extends IngresoExterno  {

    
    private int cantidadPasajeros;
    private String compania;
    private Vehiculo vehiculo;
    


    public VehiculoExterno(LocalDate fechaIngreso, String motivo, String horaIngreso, String horaSalida,
            Persona visitante, boolean estado, Guarda guarda, int cantidadPasajeros, String compania,
            Vehiculo vehiculo) {
        super(fechaIngreso, motivo, horaIngreso, horaSalida, visitante, estado, guarda);
        this.cantidadPasajeros = cantidadPasajeros;
        this.compania = compania;
        this.vehiculo = vehiculo;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
