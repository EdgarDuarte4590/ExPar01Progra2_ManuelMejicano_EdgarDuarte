package controlador;

import java.util.ArrayList;

import modelo.Estudiante;
import modelo.Guarda;

public class Controlador {
    private ArrayList<modelo.Administrador> administradores;
    private ArrayList<Guarda> oficiales;
    private ArrayList<Estudiante> estudiantes;

    public Controlador() {
        administradores = new ArrayList<>();
        oficiales = new ArrayList<>();
        estudiantes = new ArrayList<>();
        
    }

    public void agregarOficial(Guarda oficial) {
        oficiales.add(oficial);
    }

}
