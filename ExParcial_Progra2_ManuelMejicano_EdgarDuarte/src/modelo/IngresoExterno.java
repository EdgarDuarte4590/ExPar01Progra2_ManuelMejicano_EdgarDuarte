package modelo;

public class IngresoExterno {

    private String motivo,
            fechaIngreso,
            horaIngreso,horaSalida;

    private VehiculoExterno vehiculoExterno;
    private Guarda guarda;



    public IngresoExterno(String motivo, String fechaIngreso, String horaIngreso, String horaSalida,
            VehiculoExterno vehiculoExterno, Guarda guarda) {
        this.motivo = motivo;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.vehiculoExterno = vehiculoExterno;
        this.guarda = guarda;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public VehiculoExterno getVehiculoExterno() {
        return vehiculoExterno;
    }

    public void setVehiculoExterno(VehiculoExterno vehiculoExterno) {
        this.vehiculoExterno = vehiculoExterno;
    }

    public Guarda getGuarda() {
        return guarda;
    }

    public void setGuarda(Guarda guarda) {
        this.guarda = guarda;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    

}
