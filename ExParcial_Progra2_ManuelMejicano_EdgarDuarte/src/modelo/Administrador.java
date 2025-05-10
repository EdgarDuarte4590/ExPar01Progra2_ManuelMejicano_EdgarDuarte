package modelo;

// Clase Administrador: Representa a un administrador del sistema.
// Atributos: contrasena, usuario.
// Métodos: Getters y setters para los atributos.

public class Administrador extends Persona {
   private String contrasena;
    private String usuario;

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
