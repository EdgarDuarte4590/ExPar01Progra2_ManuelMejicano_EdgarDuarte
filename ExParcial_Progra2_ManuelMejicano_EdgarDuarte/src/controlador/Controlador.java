package controlador;

import java.io.InputStreamReader;
import java.sql.*;
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
    // esta clase es el controlador de la aplicacion, se encarga de manejar la
    // logica del programa y de interactuar con las vistas tanto de admistrador
    // commo de ociciales, ademas de manejar la interaccion con los objetos de las
    // clases modelo

    // declaracion de los arraylist que guardaran los objetos de cada clase

    // se elimino el arraylist de Admins ya que funciona con la base de datos, y se
    // guardan los administradores en la base de datos

    // no se puede eliminar el arraylist de oficiales, ya que apesar de que se
    // guarda en la base de datos, se utiliza para mostrar los oficiales en la vista
    // de oficiales, cuando todo se conecte a la
    // base de datos, se eliminara este arraylist y se utilizara la base de datos
    // para obtener los oficiales
    private ArrayList<Guarda> oficiales;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<IngresoFuncionario> ingresosFuncionarios;
    private ArrayList<IngresoExterno> ingresosExternos;
    private ArrayList<VehiculoExterno> ingresosVehiculoExterno;
    private ArrayList<Salida> salidasEstudiantes;
    private boolean sesionInciadaOficial = false;
    private boolean sesionIniciadaAdmin = true;
    // private String idAcceso = "1234", contraAdmin = "Douglas2025";
    Connection connection = null;
    public Statement statement = null;
    ResultSet resultSet = null;

    private String idOficialActual;// guarda el id del oficial que inicio sesion
    public MenuPrincipal menuPrincipal;// declaracion de la interface grafica del menu principal
    public DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss"); // formato en que se guardara y mostrara
                                                                                // la hora
    public Controlador() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto1?verifyServerCertificate=false&useSSL=true", "edgar_manuel", "QWERTY12345@");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
        }
        // inicializacion de los arraylist de cada clase
        // administradores = new ArrayList<>();

        oficiales = new ArrayList<>();
        estudiantes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        salidasEstudiantes = new ArrayList<>();
        ingresosFuncionarios = new ArrayList<>();
        ingresosExternos = new ArrayList<>();
        ingresosVehiculoExterno = new ArrayList<>();

        menuPrincipal = new MenuPrincipal(this);
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);
    }

    // metodo que se encarga de buscar un estudainte por su carnet, este metodo se
    // utiliza en la vista de oficiales para buscar un estudiante
    // en la vista de salida de estudiantes, se le pasa el carnet y se busca en el
    // arraylist de estudiantes
    // y asi se selecciona el estudiante en el combo box de la vista de salida de
    // estudiantes
    // por medio de esta busca podemos obteber el index del estudiante en el
    // arraylist yposterior sus atributos
    public int buscarEstudiante(String carnet) { // Ocupa el numero de carnet
        for (int i = 0; i < estudiantes.size(); i++) {

            if (estudiantes.get(i).getCarnet().equals(carnet)) {
                return i;
            }
        }
        System.out.println("No se encontró el estudiante con el carnet: " + carnet);

        return -1;
    }

    // metodo que se encarga de buscar un funcionario por su placa, este metodo se
    // utiliza en la vista de oficiales para buscar un funcionario
    // en la vista de ingreso de funcionarios, se le pasa la placa y se busca en el
    // arraylist de funcionarios
    // y asi se selecciona el funcionario en el combo box de la vista de ingreso de
    // funcionarios
    // por medio de esta busca podemos obteber el index del funcionario en el
    // arraylist y posterior obterner sus atributos
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

    public void guardar() {
        InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/modelo/administradores.json"));
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

    public void agregarOficial(String nombreCompleto, String nombreUsuario, String contrasena, String telefono,
            String cedula) {
        String[] partes = nombreCompleto.trim().split("\\s+");
        String nombre1 = "", nombre2 = "", apellido1 = "", apellido2 = "", tipo = "Guarda";
        if (partes.length == 1) {
            nombre1 = partes[0];
        } else if (partes.length == 2) {
            nombre1 = partes[0];
            apellido1 = partes[1];
        } else if (partes.length == 3) {
            nombre1 = partes[0];
            apellido1 = partes[1];
            apellido2 = partes[2];
        } else if (partes.length >= 4) {
            nombre1 = partes[0];
            nombre2 = partes[1];
            apellido1 = partes[2];
            apellido2 = partes[3];
        }
        String sql = "INSERT INTO usuarios (nombre1, nombre2, apellido1, apellido2, nombreUsuario, contraseña, numeroTelefono, cedula, tipoUsuario) VALUES ('"
                + nombre1 + "', '" + nombre2 + "', '" + apellido1 + "', '" + apellido2 + "', '" + nombreUsuario + "', '"
                + contrasena + "', '" + telefono + "', '" + cedula + "','" + tipo + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Oficial agregado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar oficial: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al agregar oficial: " + e.getMessage());
        }
    }

    public void eliminarOficial(int indexGuarda) {
        if (indexGuarda >= 0 && indexGuarda < oficiales.size()) {
            oficiales.remove(indexGuarda);
            JOptionPane.showMessageDialog(null, "Oficial eliminado correctamente.");
        } else {
            System.out.println("Índice no válido para eliminar el oficial.");
        }
    }

    // donde se comprueba el inicio e sesion del guarda
    public void loginOficial(String idAcceso, String contrasena) {
        try {
            String sql = "SELECT * FROM usuarios WHERE nombreUsuario = '" + idAcceso + "' AND tipoUsuario = 'Guarda'";
            ;

            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {

                String contrasenaDB = rs.getString("contraseña");
                String nombreUsuarioBD = rs.getString("nombreUsuario");

                if (contrasenaDB != null && contrasenaDB.equals(contrasena) && nombreUsuarioBD.equals(idAcceso)) {
                    String nombre1 = rs.getString("nombre1");
                    String nombre2 = rs.getString("nombre2");
                    String apellido1 = rs.getString("apellido1");
                    String apellido2 = rs.getString("apellido2");
                    sesionInciadaOficial = true;
                    idOficialActual = idAcceso;
                    JOptionPane.showMessageDialog(null,
                            "Bienvenido " + nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar iniciar sesión: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al iniciar sesión: " + e.getMessage());
        }
    }
    // comprueba el inicio de sesion de adminitrador

    public void loginAdmin(String nombreUsuario, String contrasena) {
        try {
            String sql = "SELECT * FROM usuarios WHERE nombreUsuario = '" + nombreUsuario + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String contrasenaDB = rs.getString("contraseña");
                String nombreUsuarioBD = rs.getString("nombreUsuario");
                if (contrasenaDB != null && contrasenaDB.equals(contrasena) && nombreUsuarioBD.equals(nombreUsuario)) {
                    String nombre1 = rs.getString("nombre1");
                    String nombre2 = rs.getString("nombre2");
                    String apellido1 = rs.getString("apellido1");
                    String apellido2 = rs.getString("apellido2");
                    sesionIniciadaAdmin = true;
                    JOptionPane.showMessageDialog(null,
                            "Bienvenido " + nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectas.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar iniciar sesión: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }

    public boolean isSesionIniciadaAdmin() {
        return sesionIniciadaAdmin;
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
