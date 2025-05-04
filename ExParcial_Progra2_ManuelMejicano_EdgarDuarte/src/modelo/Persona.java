package modelo;

public class Persona {
protected String nombre;
protected  int id;
protected String nacionalidad;
protected int edad;


public Persona(String nombre, int id) {  
    this.nombre = nombre;
    this.id = id;
    
}


public String getNombre() {
    return nombre;
}


public void setNombre(String nombre) {
    this.nombre = nombre;
}


public int getId() {
    return id;
}


public void setId(int iD) {
    id = iD;
}



}
