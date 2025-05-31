package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class IngresoExterno extends Ingreso {
    private Persona visitante; // composición
    // se declara una persona visitante
   
    public IngresoExterno(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, String nombreGuarda, String nomnbreV, int idV) {
        super(fechaIngreso, motivo, horaIngreso, nombreGuarda);
        // se da la composicion aqui creamos a la persona
        this.visitante = new Persona(nomnbreV, idV); 
       
    }

    public Persona getVisitante() {
        return visitante;
    }

    public void setVisitante(Persona visitante) {
        this.visitante = visitante;
    }

}
