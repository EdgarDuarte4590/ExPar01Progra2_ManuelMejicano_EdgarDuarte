package vista.oficiales;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Estudiante;
import modelo.Guarda;
import modelo.Salida;

public class PanelSalidaEstudiante extends JPanel {
    VistaOficiales vistaOficiales;

    

    public PanelSalidaEstudiante(VistaOficiales vistaOficiales) {
        this.vistaOficiales = vistaOficiales;
        initComponents();
    }

    public JPanel initComponents() {
        JPanel panelEstSalidas = new JPanel();
        panelEstSalidas.setLayout(null); // Establecer el diseño nulo para el panel de ingreso
        panelEstSalidas.setBounds(0, 0, 1366, 720);

        JLabel label = new JLabel("Registra las salidas de los estudiantes del Colegio Técnico Profesional de Upala");
        label.setBounds(25, 50, 700, 30);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        panelEstSalidas.add(label);

        JLabel label7 = new JLabel("Búsqueda por carnet :");
        label7.setBounds(50, 100, 200, 30);
        panelEstSalidas.add(label7);

        JTextField txtCarnet = new JTextField();
        txtCarnet.setBounds(50, 130, 200, 30);
        panelEstSalidas.add(txtCarnet);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(260, 130, 100, 30);
        btnBuscar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnBuscar.setBackground(new java.awt.Color(0xFF054FBE));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(e -> {
            
            String carnet = txtCarnet.getText();
            for (int i = 0; i < vistaOficiales.controlador.getEstudiantes().size(); i++) {
                if (carnet.equals(vistaOficiales.controlador.getEstudiantes().get(i).getCarnet())) {
                    vistaOficiales.comboEstudiantes.setSelectedIndex(i);
                    return;
                }
            }
            vistaOficiales.comboEstudiantes.setSelectedIndex(-1);
            JOptionPane.showMessageDialog(null, "No se encontro el estudiante con ese número de carnet: " + carnet);
        });
            
        
        panelEstSalidas.add(btnBuscar);

        JLabel label2 = new JLabel("Seleccione el estudiante:");
        label2.setBounds(400, 100, 500, 30);
        panelEstSalidas.add(label2);

        vistaOficiales.comboEstudiantes = new JComboBox<>();
        vistaOficiales.comboEstudiantes.setBounds(400, 130, 280, 30);
        panelEstSalidas.add(vistaOficiales.comboEstudiantes);

        vistaOficiales.comboEstudiantes.addActionListener(e->{     
            if (vistaOficiales.comboEstudiantes.getSelectedIndex() != -1) {
                txtCarnet.setText(vistaOficiales.controlador.getEstudiantes().get(vistaOficiales.comboEstudiantes.getSelectedIndex()).getCarnet());
            } else {
                txtCarnet.setText("");
            }
        });

        JLabel label3 = new JLabel("Motivo de salida:");
        label3.setBounds(700, 100, 200, 30);
        panelEstSalidas.add(label3);

        JComboBox<String> comboBoxMotivo = new JComboBox<>(
                new String[] { "Salida por cita médica o dental", "Salida por problemas de salud",
                        "Salida por llamado de padres o encargados", "Fallecimiento de familiar o allegado",
                        "Salida de almuerzo",
                        "Salida por tarde libre", "Salida por ausencia de profesor", "Salida por otro motivo" });
        comboBoxMotivo.setBounds(700, 130, 280, 30);
        panelEstSalidas.add(comboBoxMotivo);

        JButton btnRegistrar = new JButton("Aprobar salida");
        btnRegistrar.setBounds(1020, 130, 200, 30);
        btnRegistrar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnRegistrar.setBackground(new java.awt.Color(0xFF2BA76B));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.addActionListener(e -> {

            Estudiante estudiante = vistaOficiales.controlador.getEstudiantes().get(vistaOficiales.comboEstudiantes.getSelectedIndex());
            String motivoSalida = (String) comboBoxMotivo.getSelectedItem();
            LocalTime horaSalida = LocalTime.now();
            LocalDate fechaSalida = LocalDate.now();
            Guarda guarda = vistaOficiales.controlador.buscarGuardaPorID(vistaOficiales.controlador.getIdOficialActual());

            Salida salida = new Salida(fechaSalida, horaSalida, motivoSalida, estudiante, guarda);
            vistaOficiales.controlador.getSalidasEstudiantes().add(salida);
            vistaOficiales.generarTablaSalidasEstudiantes();
            vistaOficiales.generarJComboEstudiantes();

            // Limpiar los campos después de registrar
            vistaOficiales.comboEstudiantes.setSelectedIndex(0);
            comboBoxMotivo.setSelectedIndex(0);

            JOptionPane.showMessageDialog(null, "Salida registrada exitosamente");
        });
        panelEstSalidas.add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane(vistaOficiales.tablaSalidasEstudiantes);
        panelEstSalidas.add(scrollPane);
        vistaOficiales.tablaSalidasEstudiantes.setModel(vistaOficiales.modeloTablaSalidasEstudiantes);
        scrollPane.setBounds(25, 200, 1300, 400);
        vistaOficiales.tablaSalidasEstudiantes.setBounds(0, 0, 100, 500);

        vistaOficiales.generarJComboEstudiantes();
        vistaOficiales.generarTablaSalidasEstudiantes();

        return panelEstSalidas;
    }

}
