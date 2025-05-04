package modelo;


import java.time.LocalDate;
import java.time.LocalTime;

public class Ingreso {
private LocalDate fechaIngreso;
private String motivo;
private LocalTime horaIngreso;
private Persona persona;
private Guarda guarda;
private String tipoVehiculo;
private String placaVehiculo;

public Ingreso(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, Persona persona, Guarda guarda) {
    this.fechaIngreso = fechaIngreso;
    this.motivo = motivo;
    this.horaIngreso = horaIngreso;
    this.persona = persona;
    this.guarda = guarda;
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

public Guarda getGuarda() {
    return guarda;
}

public void setGuarda(Guarda guarda) {
    this.guarda = guarda;
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


}
