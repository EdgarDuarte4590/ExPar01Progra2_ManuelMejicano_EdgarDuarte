package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class IngresoExterno extends Ingreso {
    private Persona visitante; // composición
    private boolean estado;

    
   
    public IngresoExterno(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, String nombreGuarda, boolean estado, String nomnbreV, int idV) {
        super(fechaIngreso, motivo, horaIngreso, nombreGuarda);
        this.visitante = new Persona(nomnbreV, idV); 
        this.visitante = visitante;
        this.estado = estado;
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
