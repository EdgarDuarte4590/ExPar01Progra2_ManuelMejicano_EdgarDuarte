package controlador;

import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.Funcionario;
import modelo.Guarda;
import modelo.IngresoExterno;
import modelo.IngresoFuncionario;
import modelo.Salida;
import modelo.VehiculoExterno;
import vista.MenuPrincipal;

// Clase Controlador: Maneja la lógica del programa y la interacción entre vistas y modelos.
// Atributos y métodos principales: Implementa la lógica de negocio y coordina las vistas.
public class Controlador {
// esta clase es el controlador de la aplicacion, se encarga de manejar la logica del programa y de interactuar con las vistas tanto de admistrador 
//commo de ociciales, ademas de manejar la interaccion con los objetos de las clases modelo



    //declaracion de los arraylist que guardaran los objetos de cada clase 
    private ArrayList<modelo.Administrador> administradores;
    private ArrayList<Guarda> oficiales;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<IngresoFuncionario> ingresosFuncionarios;
    private ArrayList<IngresoExterno> ingresosExternos;
    private ArrayList<VehiculoExterno> ingresosVehiculoExterno;
    private ArrayList<Salida> salidasEstudiantes;
    private boolean sesionInciadaOficial = false;
    private boolean sesionIniciadaAdmin = false;
    
    private String idAcceso = "1234", contraAdmin = "Douglas2025";

    private String idOficialActual;// guarda el id del oficial que inicio sesion
    public MenuPrincipal menuPrincipal;//declaracion de la interface grafica del menu principal
    public DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss"); // formato en que se guardara y mostrara la hora

    public Controlador() {
       

        //inicializacion de los arraylist de cada clase
        administradores = new ArrayList<>();

        oficiales = new ArrayList<>();
        estudiantes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        salidasEstudiantes = new ArrayList<>();
        ingresosFuncionarios=new ArrayList<>();
        ingresosExternos = new ArrayList<>();
        ingresosVehiculoExterno = new ArrayList<>();

        menuPrincipal = new MenuPrincipal(this);
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);
    }
    //metodo que se encarga  de buscar un estudainte por su carnet, este metodo se utiliza en la vista de oficiales para buscar un estudiante
    //en la vista de salida de estudiantes, se le pasa el carnet y se busca en el arraylist de estudiantes
    //y asi se selecciona el estudiante en el combo box de la vista de salida de estudiantes
    //por medio de esta busca podemos obteber el index del estudiante en el arraylist yposterior sus atributos
    public int buscarEstudiante(String carnet){ //Ocupa el numero de carnet
       for (int i = 0; i < estudiantes.size(); i++) {
         
             if (estudiantes.get(i).getCarnet().equals(carnet)) {
                 return i;
             }
       }
         System.out.println("No se encontró el estudiante con el carnet: " + carnet);

        return -1; 
    }

    //metodo que se encarga de buscar un funcionario por su placa, este metodo se utiliza en la vista de oficiales para buscar un funcionario
    //en la vista de ingreso de funcionarios, se le pasa la placa y se busca en el arraylist de funcionarios
    //y asi se selecciona el funcionario en el combo box de la vista de ingreso de funcionarios
    //por medio de esta busca podemos obteber el index del funcionario en el arraylist y posterior obterner sus atributos
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


    public void guardar(){
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/modelo/administradores.json"));
    }
    

    public void setSesionIniciadaAdmin(boolean sesionIniciadaAdmin) {
        this.sesionIniciadaAdmin = sesionIniciadaAdmin;
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
    // donde se comprueba el inicio e sesion del guarda
    public void loginOficial(String idAcceso, String contrasena) {
        for (Guarda oficial : oficiales) {
            if (oficial.getIDAcceso().equals(idAcceso) && oficial.getContrasena().equals(contrasena)) {
                sesionInciadaOficial = true;
                idOficialActual = oficial.getIDAcceso(); // Guardar el índice del oficial actual
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "ID de acceso o contraseña incorrectos.");
    }
//comprueba el inicio de sesion de adminitrador
    public void loginAdmin(String idAcceso, String contrasena) {
        if (idAcceso.equals(this.idAcceso) && contrasena.equals(this.contraAdmin)) {
            sesionIniciadaAdmin = true;
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
        } else {
            JOptionPane.showMessageDialog(null, "ID de acceso o contraseña incorrectos.");
        }
    }

    public boolean isSesionIniciadaAdmin() {
        return sesionIniciadaAdmin;
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

    public boolean isSesionInciadaOficial() {
        return sesionInciadaOficial;
    }

    public void setSesionInciadaOficial(boolean sesionIniciada) {
        this.sesionInciadaOficial = sesionIniciada;
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

    public ArrayList<Salida> getSalidasEstudiantes() {
        return salidasEstudiantes;
    }

    public void setSalidasEstudiantes(ArrayList<Salida> salidasEstudiantes) {
        this.salidasEstudiantes = salidasEstudiantes;
    }



    

}
