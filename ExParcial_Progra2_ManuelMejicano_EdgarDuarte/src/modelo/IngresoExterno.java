package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class IngresoExterno extends Ingreso {
    private Persona visitante;
    private boolean estado;

    
    public IngresoExterno(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, Persona persona, String guarda,
            Persona visitante) {
        super(fechaIngreso, motivo, horaIngreso, persona, guarda);
        this.visitante = visitante;
        this.estado = false;
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
