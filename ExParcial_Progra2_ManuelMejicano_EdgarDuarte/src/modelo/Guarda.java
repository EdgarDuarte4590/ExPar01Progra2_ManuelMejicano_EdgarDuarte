package modelo;

public class Guarda extends Persona {

    private int IDAcceso;
    private String contrasena;

    public Guarda(String nombre, int iD, int iDAcceso, String contrasena) {
        super(nombre, iD);
        IDAcceso = iDAcceso;
        this.contrasena = contrasena;
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

}
