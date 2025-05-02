package modelo;

public class Persona {
protected String nombre;
protected  int ID;


public Persona(String nombre, int iD) {
    this.nombre = nombre;
    ID = iD;
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



}
