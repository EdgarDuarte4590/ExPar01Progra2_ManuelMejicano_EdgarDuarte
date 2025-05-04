package modelo;

public class VehiculoExterno extends Vehiculo {

    private String nombreChofer;
    private int cantidadPasajeros;
    private String compania;

    public VehiculoExterno(String placa, String tipoVehiculo, String nombreChofer, int cantidadPasajeros,
            String compania) {
        super(placa, tipoVehiculo);
        this.nombreChofer = nombreChofer;
        this.cantidadPasajeros = cantidadPasajeros;
        this.compania = compania;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
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

}
