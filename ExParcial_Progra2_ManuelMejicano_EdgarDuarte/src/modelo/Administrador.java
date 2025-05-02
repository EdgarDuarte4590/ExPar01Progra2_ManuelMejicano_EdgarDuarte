package modelo;

public class Administrador extends Persona {
   pr String contrasena;
    String usuario;

    public Administrador(String nombre, int iD, String contrasena, String usuario) {
        super(nombre, iD);
        this.contrasena = contrasena;
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
