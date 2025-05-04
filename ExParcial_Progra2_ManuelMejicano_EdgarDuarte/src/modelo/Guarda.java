package modelo;

public class Guarda extends Persona {

    private String IDAcceso;
    private String contrasena;
    private String numeroTelefono;



    public Guarda(String nombre, int id,String iDAcceso, String contrasena, String numeroTelefono) {
        super(nombre, id);
        IDAcceso = iDAcceso;
        this.contrasena = contrasena;
        this.numeroTelefono = numeroTelefono;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public String getIDAcceso() {
        return IDAcceso;
    }


    public void setIDAcceso(String iDAcceso) {
        IDAcceso = iDAcceso;
    }


    public String getNumeroTelefono() {
        return numeroTelefono;
    }


    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }


    

}
