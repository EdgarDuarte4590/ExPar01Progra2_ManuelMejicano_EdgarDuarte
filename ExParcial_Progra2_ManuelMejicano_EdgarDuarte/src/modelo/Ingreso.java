package modelo;


import java.time.LocalDate;
import java.time.LocalTime;

public class Ingreso {
private LocalDate fechaIngreso;
private String motivo;
private LocalTime horaIngreso;
private Persona persona;
private String nombreGuarda;
private String tipoVehiculo;
private String placaVehiculo;

public Ingreso(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, Persona persona, String nombreGuarda) {
    this.fechaIngreso = fechaIngreso;
    this.motivo = motivo;
    this.horaIngreso = horaIngreso;
    this.persona = persona;
    this.nombreGuarda = nombreGuarda;
}



public String getTipoVehiculo() {
    return tipoVehiculo;
}



public void setTipoVehiculo(String tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
}



public String getPlacaVehiculo() {
    return placaVehiculo;
}



public void setPlacaVehiculo(String placaVehiculo) {
    this.placaVehiculo = placaVehiculo;
}



public String getMotivo() {
    return motivo;
}

public void setMotivo(String motivo) {
    this.motivo = motivo;
}



public Persona getPersona() {
    return persona;
}

public void setPersona(Persona persona) {
    this.persona = persona;
}



public LocalDate getFechaIngreso() {
    return fechaIngreso;
}



public void setFechaIngreso(LocalDate fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
}



public LocalTime getHoraIngreso() {
    return horaIngreso;
}



public void setHoraIngreso(LocalTime horaIngreso) {
    this.horaIngreso = horaIngreso;
}



public String getNombreGuarda() {
    return nombreGuarda;
}



public void setNombreGuarda(String nombreGuarda) {
    this.nombreGuarda = nombreGuarda;
}


}
