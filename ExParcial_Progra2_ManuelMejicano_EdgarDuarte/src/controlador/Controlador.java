package controlador;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.Funcionario;
import modelo.Guarda;
import modelo.IngresoExterno;
import modelo.IngresoFuncionario;
import modelo.VehiculoExterno;
import vista.MenuPrincipal;

public class Controlador {
    private ArrayList<modelo.Administrador> administradores;
    private ArrayList<Guarda> oficiales;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<IngresoFuncionario> ingresosFuncionarios;
    private ArrayList<IngresoExterno> ingresosExternos;
    private ArrayList<VehiculoExterno> ingresosVehiculoExterno;
    private boolean sesionIniciada = false;
    private String idOficialActual;
    public MenuPrincipal menuPrincipal;
    public DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss"); 

    public Controlador() {
        menuPrincipal = new MenuPrincipal(this);
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);

        administradores = new ArrayList<>();
        oficiales = new ArrayList<>();
        estudiantes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        ingresosFuncionarios=new ArrayList<>();
        ingresosExternos = new ArrayList<>();
        ingresosVehiculoExterno = new ArrayList<>();
    }

    public Funcionario buscarFuncionarioPorPlaca(String placa) {
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getVehiculo() != null && funcionario.getVehiculo().getPlaca().equals(placa)) {
                return funcionario;
            }
        }
                JOptionPane.showMessageDialog(null, "Funcionario no encontrado con la placa: " + placa);
        return null; // Si no se encuentra el funcionario
    }

    public Guarda buscarGuardaPorID(String id) {
        for (Guarda guarda : getOficiales()) {
            if (guarda.getIDAcceso().equals(id)) {
                return guarda;
            }
        }
        return null; // Si no se encuentra el guarda
    }

    public void setIngresosFuncionarios(ArrayList<IngresoFuncionario> ingresosFuncionarios) {
        this.ingresosFuncionarios = ingresosFuncionarios;
    }

    public ArrayList<IngresoExterno> getIngresosExternos() {
        return ingresosExternos;
    }

    public void setIngresosExternos(ArrayList<IngresoExterno> ingresosExternos) {
        this.ingresosExternos = ingresosExternos;
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

    public ArrayList<IngresoFuncionario> getIngresosFuncionarios() {
        return ingresosFuncionarios;
    }

    public ArrayList<VehiculoExterno> getIngresosVehiculoExterno() {
        return ingresosVehiculoExterno;
    }

    public void setIngresosVehiculoExterno(ArrayList<VehiculoExterno> ingresosVehiculoExterno) {
        this.ingresosVehiculoExterno = ingresosVehiculoExterno;
    }

    public DateTimeFormatter getFormato() {
        return formato;
    }

    public void setFormato(DateTimeFormatter formato) {
        this.formato = formato;
    }



    

}
