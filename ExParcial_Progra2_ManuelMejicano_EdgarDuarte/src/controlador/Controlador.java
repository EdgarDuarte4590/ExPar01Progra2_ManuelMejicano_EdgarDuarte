package controlador;

import java.util.ArrayList;

import modelo.Estudiante;
import modelo.Guarda;

public class Controlador {
    private ArrayList<modelo.Administrador> administradores;
    private ArrayList<Guarda> oficiales;
    private ArrayList<Estudiante> estudiantes;
    private boolean sesionIniciada = false;
    private int idOficialActual = -1; 

    public Controlador() {
        administradores = new ArrayList<>();
        oficiales = new ArrayList<>();
        estudiantes = new ArrayList<>();
        
    }

    public void agregarOficial(Guarda oficial) {
        oficiales.add(oficial);
    }

    public ArrayList<modelo.Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(ArrayList<modelo.Administrador> administradores) {
        this.administradores = administradores;
    }

    public ArrayList<Guarda> getOficiales() {
        return oficiales;
    }

    public void setOficiales(ArrayList<Guarda> oficiales) {
        this.oficiales = oficiales;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    public int getIdOficialActual() {
        return idOficialActual;
    }

    public void setIdOficialActual(int idOficialActual) {
        this.idOficialActual = idOficialActual;
    }

}
