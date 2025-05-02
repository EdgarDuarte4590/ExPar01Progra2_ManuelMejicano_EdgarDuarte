package modelo;

public class VehiculoExterno {

    private String nombreChofer;
    private String placa;
    private int cantidadPasajeros;
    private String compania;

    public VehiculoExterno(String nombreChofer, String placa, int cantidadPasajeros, String compania) {
        this.nombreChofer = nombreChofer;
        this.placa = placa;
        this.cantidadPasajeros = cantidadPasajeros;
        this.compania = compania;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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
