package modelo;

public class Guarda {
    String nombre;
    int ID;
    String contrasena;

    
    public Guarda(String nombre, int iD, String contrasena) {
        this.nombre = nombre;
        ID = iD;
        this.contrasena = contrasena;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
