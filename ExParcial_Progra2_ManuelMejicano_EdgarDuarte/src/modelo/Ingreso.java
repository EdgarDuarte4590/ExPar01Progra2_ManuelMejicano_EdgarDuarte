package modelo;

public class Ingreso {
private String fechaIngreso;
private String motivo;
private String horaIngreso;
private Persona persona;
private Guarda guarda;

public Ingreso(String fechaIngreso, String motivo, String horaIngreso, Persona persona, Guarda guarda) {
    this.fechaIngreso = fechaIngreso;
    this.motivo = motivo;
    this.horaIngreso = horaIngreso;
    this.persona = persona;
    this.guarda = guarda;
}

public String getFechaIngreso() {
    return fechaIngreso;
}

public void setFechaIngreso(String fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
}

public String getMotivo() {
    return motivo;
}

public void setMotivo(String motivo) {
    this.motivo = motivo;
}

public String getHoraIngreso() {
    return horaIngreso;
}

public void setHoraIngreso(String horaIngreso) {
    this.horaIngreso = horaIngreso;
}

public Persona getPersona() {
    return persona;
}

public void setPersona(Persona persona) {
    this.persona = persona;
}

public Guarda getGuarda() {
    return guarda;
}

public void setGuarda(Guarda guarda) {
    this.guarda = guarda;
}


}
