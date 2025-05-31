package modelo;


import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Ingreso {
private LocalDate fechaIngreso;//dia en que se registra el ingreso
private String motivo;// motivo por el cual entra a la institucion
private LocalTime horaIngreso;
private String nombreGuarda;//guarda que registro el ingreso


public Ingreso(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, String nombreGuarda) {
    this.fechaIngreso = fechaIngreso;
    this.motivo = motivo;
    this.horaIngreso = horaIngreso;
    this.nombreGuarda = nombreGuarda;
}



public String getMotivo() {
    return motivo;
}

public void setMotivo(String motivo) {
    this.motivo = motivo;
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
