package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class IngresoFuncionario extends Ingreso {
    public Funcionario funcionario;//agregacion de un funcionario, previamente registrado

    public IngresoFuncionario(LocalDate fechaIngreso, String motivo, LocalTime horaIngreso, String nombreGuarda,
            Funcionario funcionario) {
        super(fechaIngreso, motivo, horaIngreso, nombreGuarda);
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
   
    

}
