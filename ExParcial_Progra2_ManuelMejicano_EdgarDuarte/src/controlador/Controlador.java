package controlador;

import java.awt.Menu;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Estudiante;
import modelo.Funcionario;
import modelo.Guarda;
import vista.MenuPrincipal;

public class Controlador {
    private ArrayList<modelo.Administrador> administradores;
    private ArrayList<Guarda> oficiales;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Funcionario> funcionarios;
    private boolean sesionIniciada = true;
    private String idOficialActual;
    public MenuPrincipal menuPrincipal;

    public Controlador() {
        menuPrincipal = new MenuPrincipal(this);
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);

        administradores = new ArrayList<>();
        oficiales = new ArrayList<>();
        estudiantes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public void agregarOficial(Guarda oficial) {
        oficiales.add(oficial);
    }

    public void eliminarOficial(int indexGuarda){
        if (indexGuarda >= 0 && indexGuarda < oficiales.size()) {
            oficiales.remove(indexGuarda);
            JOptionPane.showMessageDialog(null, "Oficial eliminado correctamente.");
        } else {
            System.out.println("Índice no válido para eliminar el oficial.");
        }
    }

    public void loginOficial(String idAcceso, String contrasena) {
        for (Guarda oficial : oficiales) {
            if (oficial.getIDAcceso().equals(idAcceso) && oficial.getContrasena().equals(contrasena)) {
                sesionIniciada = true;
                idOficialActual = oficial.getIDAcceso(); // Guardar el índice del oficial actual
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "ID de acceso o contraseña incorrectos.");
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

    public String getIdOficialActual() {
        return idOficialActual;
    }

    public void setIdOficialActual(String idOficialActual) {
        this.idOficialActual = idOficialActual;
    }

   

}
