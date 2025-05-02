package modelo;

public class Guarda extends Persona {

    private int IDAcceso;
    private String contrasena;
    private int numeroTelefono;



    public Guarda(String nombre, int iD, int iDAcceso, String contrasena, int numeroTelefono) {
        super(nombre, iD);
        IDAcceso = iDAcceso;
        this.contrasena = contrasena;
        this.numeroTelefono = numeroTelefono;
    }

    public int getIDAcceso() {
        return IDAcceso;
    }

    public void setIDAcceso(int iDAcceso) {
        IDAcceso = iDAcceso;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    

}
