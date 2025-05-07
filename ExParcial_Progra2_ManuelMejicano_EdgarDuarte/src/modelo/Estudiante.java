package modelo;

import java.time.LocalDate;

public class Estudiante extends Persona {

    private String carnet;
    private String codigoAcceso;
    private LocalDate fechaNacimiento;

    public Estudiante(String nombre, int iD, String carnet, String codigoAcceso, LocalDate fechaNacimiento,
            String nacionalidad) {
        super(nombre, iD);
        this.carnet = carnet;
        this.codigoAcceso = codigoAcceso;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        edad = calcularEdad();
    }
    
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int calcularEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

}
