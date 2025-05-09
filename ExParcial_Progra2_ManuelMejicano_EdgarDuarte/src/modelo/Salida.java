package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Salida {

    private LocalDate fechaSalida;
    private LocalTime horaSalida;//hora exacta en que se registra la salida
    private String motivoSalida;
    private Persona persona; // agregación
    private Guarda guarda; //agregación

    
    public Salida(LocalDate fechaSalida, LocalTime horaSalida, String motivoSalida, Persona persona, Guarda guarda) {
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.motivoSalida = motivoSalida;
        this.persona = persona;
        this.guarda = guarda;
    }
    
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
    public String getMotivoSalida() {
        return motivoSalida;
    }
    public void setMotivoSalida(String motivoSalida) {
        this.motivoSalida = motivoSalida;
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

   
    

    
    

}
