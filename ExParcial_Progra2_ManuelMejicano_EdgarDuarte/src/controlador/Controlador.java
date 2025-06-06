package controlador;

import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
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

   
    private boolean sesionInciadaOficial = false;
    private boolean sesionIniciadaAdmin = false;

    // private String idAcceso = "1234", contraAdmin = "Douglas2025";
    public Connection connection = null;
    public Statement statement = null;
    ResultSet resultSet = null;

    private String idOficialActual; // guarda el id del oficial que inicio sesion
    public MenuPrincipal menuPrincipal;// declaracion de la interface grafica del menu principal
    public DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss"); // formato en que se guardara y mostrara

    public Controlador() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://10.153.159.15:3306/proyecto1?verifyServerCertificate=false&useSSL=true",
                    "edgar_manuel", "QWERTY12345@");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.");
        } catch (SQLException e) {

        }


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
    public String buscarNombreEstudiante(String carnet) { // Ocupa el numero de carnet
        String sql = "SELECT * nombre1, nombre2, apellido1, apellido2 FROM estudiantes WHERE carnet = '" + carnet + "'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String nombreEstudiante = rs.getString("nombre1") + " " + rs.getString("nombre2") + " "
                        + rs.getString("apellido1") + " " + rs.getString("apellido2");
                JOptionPane.showMessageDialog(null, "Estudiante encontrado: " + nombreEstudiante);
                return nombreEstudiante; // Retorna el nombre del estudiante si se encuentra
            } else {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado con el carnet: " + carnet);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar estudiante: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al buscar estudiante: " + e.getMessage());
        }
        return null; // Si no se encuentra el estudiante
    }

    public ResultSet busquedaDinamicaFuncionarios(String criterio) {
        String sql = "SELECT * FROM usuarios WHERE tipoUsuario = 'Funcionario' AND (nombre1 LIKE '%" + criterio
                + "%' OR nombre2 LIKE '%" + criterio + "%' OR apellido1 LIKE '%" + criterio + "%' OR apellido2 LIKE '%"
                + criterio + "%' OR cedula LIKE '%" + criterio + "%')";

        try {
            Statement stmnt = connection.createStatement();
            return stmnt.executeQuery(sql);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null; // Si ocurre un error, retorna null:

    }

    

    public String buscarNombrePersona(String cedula) { // Recibe la cédula de la persona
        // Construye la consulta SQL para obtener los nombres y apellidos de la tabla
        // personas
        String sql = "SELECT nombre1, nombre2, apellido1, apellido2 FROM personas WHERE cedula = '" + cedula + "'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                // Si se encuentra un registro, arma el nombre completo
                String nombreCompleto = rs.getString("nombre1") + " "
                        + rs.getString("nombre2") + " "
                        + rs.getString("apellido1") + " "
                        + rs.getString("apellido2");
                JOptionPane.showMessageDialog(null, "Persona encontrada: " + nombreCompleto);
                return nombreCompleto; // Retorna el nombre completo de la persona
            } else {
                // Si no hay resultados, informa que no se encontró la persona
                JOptionPane.showMessageDialog(null, "Persona no encontrada con la cédula: " + cedula);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar persona: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al buscar persona: " + e.getMessage());
        }
        return null; // Si no se encuentra la persona, devuelve null
    }

   


    public String buscarGuardaPorUsuario(String nombreUsuario) {
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = '" + nombreUsuario + "' AND tipoUsuario = 'Guarda'";
        String nombreGuarda = null;
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                nombreGuarda = rs.getString("nombre1") + " " + rs.getString("nombre2") + " " + rs.getString("apellido1")
                        + " " + rs.getString("apellido2");

                return nombreGuarda; // Retorna el ResultSet si se encuentra el guarda
            } else {
                //OptionPane.showMessageDialog(null, "Guarda no encontrado con el nombre de usuario: " + nombreUsuario);
            }
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar guarda: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al buscar guarda: " + e.getMessage());
        }

        return null; // Si no se encuentra el guarda
    }


    public void guardar() {
        InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/modelo/administradores.json"));
    }

    public void setSesionIniciadaAdmin(boolean sesionIniciadaAdmin) {
        this.sesionIniciadaAdmin = sesionIniciadaAdmin;
    }



    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public void agregarOficial(String nombre1, String nombre2, String apellido1, String apellido2, String nombreUsuario,
            String contrasena, String telefono,
            String cedula) {
        String tipo = "Guarda";
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

    public void eliminarOficial(String nombreUsuario) {

        String sql = "DELETE FROM usuarios WHERE nombreUsuario = '" + nombreUsuario + "' AND tipoUsuario = 'Guarda'";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Oficial eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el oficial con el nombre de usuario: " + nombreUsuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar oficial: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al eliminar oficial: " + e.getMessage());
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
            String sql = "SELECT * FROM usuarios WHERE nombreUsuario = '" + nombreUsuario + "'" 
                    + " AND tipoUsuario = 'Administrador'";
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
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectas");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
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



    public DateTimeFormatter getFormato() {
        return formato;
    }

    public void setFormato(DateTimeFormatter formato) {
        this.formato = formato;
    }


    public void editarOficial(String nombre1, String nombre2, String apellido1, String apellido2, String telefono,
            String nombreUsuario, String contrasena, String id, String nombreUsuarioModi) {
        String sql = "UPDATE usuarios SET cedula = '" + id + "', nombre1 = '" + nombre1 + "', nombre2 = '" + nombre2
                + "', apellido1 = '" + apellido1 + "', apellido2 = '" + apellido2 + "', numeroTelefono = '"
                + telefono + "', nombreUsuario = '" + nombreUsuario + "', contraseña = '" + contrasena
                + "' WHERE nombreUsuario = '" + nombreUsuarioModi + "'";
        try {

            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Oficial editado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar oficial: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al editar oficial: " + e.getMessage());
        }
    }

    public void editarEstudiante(String nombre1, String nombre2, String apellido1, String apellido2, String id,
            LocalDate fechaNacimiento, String carnet, String nacionalidad, String direccion, String carnetModi) {

        java.sql.Date fechaNacimientoSQL = java.sql.Date.valueOf(fechaNacimiento);

        String sql = "UPDATE estudiantes SET nombre1 = '" + nombre1 + "', nombre2 = '" + nombre2 + "', apellido1 = '"
                + apellido1 + "', apellido2 = '" + apellido2 + "', cedula = '"
                + id + "', fechaNacimiento = '" + fechaNacimientoSQL + "', carnet = '" + carnet + "', nacionalidad = '"
                + nacionalidad + "', direccion = '" + direccion
                + "' WHERE carnet = '" + carnetModi + "'";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante editado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar estudiante: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al editar estudiante: " + e.getMessage());
        }

    }

    public void eliminarEstudiante(String carnet) {
        String sql = "DELETE FROM estudiantes WHERE carnet = '" + carnet + "'";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el estudiante con el carnet: " + carnet);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar estudiante: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al eliminar estudiante: " + e.getMessage());
        }
    }

    public ResultSet consultarEstudiante(String nombreCompleto) {
        String sql = "SELECT * FROM estudiantes WHERE CONCAT(nombre1, ' ', nombre2, ' ', apellido1, ' ', apellido2) LIKE '%"
                + nombreCompleto + "%'";

        try {
            Statement stet = connection.createStatement();
            return stet.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage());
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            return null;
        }
    }

  

    public void registrarSalidaEstudiante(String carnetEstudiante, LocalDate fechaSalida, LocalTime horaSalida,
            String motivoSalida, String nombreGuarda) {
        String sql = "INSERT INTO salidas_estudiantes (carnet, fecha, hora, motivo, nombre_usuario_guarda) VALUES ('"
                + carnetEstudiante + "', '" + fechaSalida + "', '" + horaSalida + "', '" + motivoSalida + "', '"
                + nombreGuarda + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Salida registrada correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar salida: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al registrar salida: " + e.getMessage());
        }
    }

    public void registrarPersonaExterna(String cedula, String nombre1, String nombre2, String apellido1,
            String apellido2) {
        String tipoPersona = "Externa";
        String sql = "INSERT INTO personas (cedula, nombre1, nombre2, apellido1, apellido2, tipoPersona) VALUES ('"
                + cedula + "', '" + nombre1 + "', '" + nombre2 + "', '" + apellido1 + "', '" + apellido2 + "', '"
                + tipoPersona + "')";

        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Persona externa registrada correctamente.");
            }

        } catch (SQLException e) {
        }
    }


    public void registarIngresoExterno(String cedula,LocalDate fecha, LocalTime hora, String motivo, String nombreGuarda) {
        String tipoIngreso="Externo";
        String sql = "INSERT INTO ingresos (cedula, fecha, hora, motivo, nombre_usuario_guarda,tipoIngreso) VALUES ('"
                + cedula + "', '" + fecha + "', '" + hora + "', '" + motivo + "', '" + nombreGuarda + "', '" + tipoIngreso + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Ingreso externo registrado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar ingreso externo: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al registrar ingreso externo: " + e.getMessage());
        }
    }

    public void agregarIngresoFuncionario(String idFuncionario, LocalDate fechaIngreso, LocalTime horaIngreso,
            String nameUserGuarda) {
        String sql = "INSERT INTO ingresos (cedula, fecha, hora, nombre_usuario_guarda, tipoIngreso) VALUES ('"
                + idFuncionario + "', '"
                + fechaIngreso + "', '" + horaIngreso + "', '" + nameUserGuarda + "', '" + "Funcionario" + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Ingreso de funcionario registrado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar ingreso de funcionario: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error inesperado al registrar ingreso de funcionario: " + e.getMessage());
        }
    }

    public ResultSet consultarFuncionario(String cedula) {
        String sql = "SELECT * FROM personas WHERE tipoPersona = 'Funcionario' AND cedula ='" + cedula + "'";

        // Consulta SQL para buscar un funcionario por su cédula
        try {
            Statement stet = connection.createStatement();
            return stet.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta: " + e.getMessage());
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            return null;
        }
    }

    public String getTipoVehiculo(String placa) {
        String tipoVehiculo = "";
        String query = "SELECT tipoVehiculo FROM vehiculos WHERE placa = '" + placa + "'";
        try {
            Statement stmt = connection.createStatement(); // ← nuevo Statement
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                tipoVehiculo = rs.getString("tipoVehiculo");
            }
            rs.close(); // Cierra el ResultSet explícitamente
            stmt.close(); // Cierra el Statement explícitamente
        } catch (SQLException e) {
            System.out.println("Error al obtener tipo de vehículo: " + e.getMessage());
        }
        return tipoVehiculo;
    }

    public void registrarPersonaVehiculoExterno(String cedula, String nombre1, String nombre2, String apellido1, String apellido2, String placa) {
        String tipoPersona = "ExternaVehiculo";
        String sql = "INSERT INTO personas (cedula, nombre1, nombre2, apellido1, apellido2, tipoPersona, placaVehiculo) VALUES ('"
                + cedula + "', '" + nombre1 + "', '" + nombre2 + "', '" + apellido1 + "', '" + apellido2 + "', '" + tipoPersona + "', '" + placa + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Persona externa con vehículo registrada correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar persona externa con vehículo: " + e.getMessage());
        }
    }

    public void registarIngresoVehiculoExterno(String cedula, LocalDate fecha, LocalTime hora, String motivo, String nombreGuarda, String placa) {
        String tipoIngreso = "VehiculoExterno";
        String sql = "INSERT INTO ingresos (cedula, fecha, hora, motivo, nombre_usuario_guarda, tipoIngreso, placa_vehiculo) VALUES ('"
                + cedula + "', '" + fecha + "', '" + hora + "', '" + motivo + "', '" + nombreGuarda + "', '" + tipoIngreso + "', '" + placa + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Ingreso de vehículo externo registrado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar ingreso de vehículo externo: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al registrar ingreso de vehículo externo: " + e.getMessage());
        }
    }
 
    public void registrarVehiculoExterno(String placa,int cantidadPasajeros, String nombreEmpresa,String tipoVehiculo ){
        String sql = "INSERT INTO vehiculos (placa, cantidadPasajeros, nombreEmpresa, tipoVehiculo) VALUES ('"
                + placa + "', '" + cantidadPasajeros + "', '" + nombreEmpresa + "', '" + tipoVehiculo + "')";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Vehículo externo registrado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar vehículo externo: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al registrar vehículo externo: " + e.getMessage());
        }
    }

    public void eliminarIngresoExterno(String id, String tipoIngreso) {
        String sql = "DELETE FROM ingresos WHERE id = '" + id + "' AND tipoIngreso = '" + tipoIngreso + "'";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Ingreso externo eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el ingreso externo con la cédula: " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar ingreso externo: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al eliminar ingreso externo: " + e.getMessage());
        }
    }

    public void eliminarSalidaEstudiante(String idSalida) {
        String sql = "DELETE FROM salidas_estudiantes WHERE id = '" + idSalida + "'";
        try {
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Salida de estudiante eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la salida de estudiante con el ID: " + idSalida);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar salida de estudiante: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al eliminar salida de estudiante: " + e.getMessage());
        }
    }

}