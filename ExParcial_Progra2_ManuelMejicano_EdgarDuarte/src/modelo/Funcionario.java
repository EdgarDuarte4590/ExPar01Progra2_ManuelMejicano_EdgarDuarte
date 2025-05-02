package modelo;

public class Funcionario extends Persona {
    private String puesto;
    private int IDFuncionario;

    public Funcionario(String nombre, int iD, String puesto, int IDFuncionario) {
        super(nombre, iD);
        this.puesto = puesto;
        this.IDFuncionario = IDFuncionario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getIDFuncionario() {
        return IDFuncionario;
    }

    public void setIDFuncionario(int iDFuncionario) {
        IDFuncionario = iDFuncionario;
    }

    
}
