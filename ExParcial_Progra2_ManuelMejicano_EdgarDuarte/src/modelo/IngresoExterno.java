package modelo;

import java.time.LocalDate;

public class IngresoExterno {
    LocalDate fechaIngreso;
    private String motivo, horaIngreso, horaSalida;
    private Persona visitante;
    private boolean estado;
    private Guarda guarda;

  

    public IngresoExterno(LocalDate fechaIngreso, String motivo, String horaIngreso, String horaSalida,
            Persona visitante, boolean estado, Guarda guarda) {
        this.fechaIngreso = fechaIngreso;
        this.motivo = motivo;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.visitante = visitante;
        this.estado = false;
        this.guarda = guarda;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Persona getVisitante() {
        return visitante;
    }

    public void setVisitante(Persona visitante) {
        this.visitante = visitante;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
