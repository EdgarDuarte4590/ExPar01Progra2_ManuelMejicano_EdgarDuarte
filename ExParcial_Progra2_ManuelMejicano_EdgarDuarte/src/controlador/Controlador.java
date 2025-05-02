package controlador;

import java.util.ArrayList;

import modelo.Estudiante;
import modelo.Guarda;


public class Controlador {
      private ArrayList<modelo.Administrador> administradores;
        private ArrayList<Guarda> oficiales;
        private ArrayList<Estudiante> estudiantes;
        private vista.administrador.VistaAdmin vistaAdmin;
        private vista.oficiales.VistaOficiales vistaOficiales;
        private vista.estudiantes.VistaEstudiante vistaEstudiante;
    
        public Controlador() {
            administradores = new ArrayList<>();
            oficiales = new ArrayList<>();
            estudiantes = new ArrayList<>();
            vistaAdmin = new vista.administrador.VistaAdmin();
            vistaOficiales = new vista.oficiales.VistaOficiales();
            vistaEstudiante = new vista.estudiantes.VistaEstudiante();
        }
    
        public void iniciar() {
            // Aquí puedes agregar la lógica para iniciar la aplicación
            // Por ejemplo, mostrar la vista del administrador
            vistaAdmin.mostrar();
        }   

}
