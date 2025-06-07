package vista;

import javax.swing.*;
import java.awt.*;

public class SoporteFrame extends JFrame {

    private String nombreDesarrollador1 = "Ing. Manuel Mejicano Ortiz";
    private String contactoDesarrollador1 = "Correo electrónico: manuelmexican1928@outlook.com";
    private String fotoDesarrollador1 = "src/resources/ing_mejicano.jpg";
    private String carnetDesarrollador1 = "Carné: C4H071";
    private String carreraDesarrollador1 = "Carrera: Informática Empresarial";

    private String nombreDesarrollador2 = "Ing. Edgar Duarte Alemán";
    private String contactoDesarrollador2 = "Correo electrónico: emmanuelduale2006@gmail.com";
    private String fotoDesarrollador2 = "src/resources/ing_duarte.jpg";
    private String carnetDesarrollador2 = "Carné: C4E773";
    private String carreraDesarrollador2 = "Carrera: Informática Empresarial";

    public SoporteFrame() {
        setTitle("Información de Soporte");
        setSize(1000, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(245, 245, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Soporte del Sistema y Derechos de Autor", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(0, 123, 255));

        // Panel para Desarrollador 1
        JPanel panelDev1 = new JPanel(new GridBagLayout());
        panelDev1.setBackground(Color.WHITE);
        panelDev1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel foto1 = new JLabel();
        ImageIcon icono1 = new ImageIcon(fotoDesarrollador1);
        Image imagen1 = icono1.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
        foto1.setIcon(new ImageIcon(imagen1));

        GridBagConstraints gbcFoto1 = new GridBagConstraints();
        gbcFoto1.gridx = 0;
        gbcFoto1.gridy = 1;
        gbcFoto1.anchor = GridBagConstraints.CENTER;

        JLabel nombre1 = new JLabel(nombreDesarrollador1, JLabel.CENTER);
        nombre1.setFont(new Font("Arial", Font.BOLD, 18));
        nombre1.setForeground(new Color(33, 37, 41));

        GridBagConstraints gbcNombre1 = new GridBagConstraints();
        gbcNombre1.gridx = 0;
        gbcNombre1.gridy = 0;
        gbcNombre1.fill = GridBagConstraints.HORIZONTAL;

        JLabel contacto1 = new JLabel(contactoDesarrollador1, JLabel.CENTER);
        contacto1.setFont(new Font("Arial", Font.PLAIN, 14));
        contacto1.setForeground(new Color(108, 117, 125));

        GridBagConstraints gbcContacto1 = new GridBagConstraints();
        gbcContacto1.gridx = 0;
        gbcContacto1.gridy = 2;
        gbcContacto1.fill = GridBagConstraints.HORIZONTAL;

        JLabel carnet1 = new JLabel(carnetDesarrollador1, JLabel.CENTER);
        carnet1.setFont(new Font("Arial", Font.PLAIN, 14));
        carnet1.setForeground(new Color(108, 117, 125));

        GridBagConstraints gbcCarnet1 = new GridBagConstraints();
        gbcCarnet1.gridx = 0;
        gbcCarnet1.gridy = 3;
        gbcCarnet1.fill = GridBagConstraints.HORIZONTAL;

        JLabel carrera1 = new JLabel(carreraDesarrollador1, JLabel.CENTER);
        carrera1.setFont(new Font("Arial", Font.PLAIN, 14));
        carrera1.setForeground(new Color(108, 117, 125));

        GridBagConstraints gbcCarrera1 = new GridBagConstraints();
        gbcCarrera1.gridx = 0;
        gbcCarrera1.gridy = 4;
        gbcCarrera1.fill = GridBagConstraints.HORIZONTAL;

        panelDev1.add(nombre1, gbcNombre1);
        panelDev1.add(foto1, gbcFoto1);
        panelDev1.add(contacto1, gbcContacto1);
        panelDev1.add(carnet1, gbcCarnet1);
        panelDev1.add(carrera1, gbcCarrera1);

        // Panel para Desarrollador 2
        JPanel panelDev2 = new JPanel(new GridBagLayout());
        panelDev2.setBackground(Color.WHITE);
        panelDev2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel foto2 = new JLabel();
        ImageIcon icono2 = new ImageIcon(fotoDesarrollador2);
        Image imagen2 = icono2.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
        foto2.setIcon(new ImageIcon(imagen2));

        GridBagConstraints gbcFoto2 = new GridBagConstraints();
        gbcFoto2.gridx = 0;
        gbcFoto2.gridy = 1;
        gbcFoto2.anchor = GridBagConstraints.CENTER;

        JLabel nombre2 = new JLabel(nombreDesarrollador2, JLabel.CENTER);
        nombre2.setFont(new Font("Arial", Font.BOLD, 18));
        nombre2.setForeground(new Color(33, 37, 41));

        GridBagConstraints gbcNombre2 = new GridBagConstraints();
        gbcNombre2.gridx = 0;
        gbcNombre2.gridy = 0;
        gbcNombre2.fill = GridBagConstraints.HORIZONTAL;

        JLabel contacto2 = new JLabel(contactoDesarrollador2, JLabel.CENTER);
        contacto2.setFont(new Font("Arial", Font.PLAIN, 14));
        contacto2.setForeground(new Color(108, 117, 125));

        GridBagConstraints gbcContacto2 = new GridBagConstraints();
        gbcContacto2.gridx = 0;
        gbcContacto2.gridy = 2;
        gbcContacto2.fill = GridBagConstraints.HORIZONTAL;

        JLabel carnet2 = new JLabel(carnetDesarrollador2, JLabel.CENTER);
        carnet2.setFont(new Font("Arial", Font.PLAIN, 14));
        carnet2.setForeground(new Color(108, 117, 125));

        GridBagConstraints gbcCarnet2 = new GridBagConstraints();
        gbcCarnet2.gridx = 0;
        gbcCarnet2.gridy = 3;
        gbcCarnet2.fill = GridBagConstraints.HORIZONTAL;

        JLabel carrera2 = new JLabel(carreraDesarrollador2, JLabel.CENTER);
        carrera2.setFont(new Font("Arial", Font.PLAIN, 14));
        carrera2.setForeground(new Color(108, 117, 125));

        GridBagConstraints gbcCarrera2 = new GridBagConstraints();
        gbcCarrera2.gridx = 0;
        gbcCarrera2.gridy = 4;
        gbcCarrera2.fill = GridBagConstraints.HORIZONTAL;

        panelDev2.add(nombre2, gbcNombre2);
        panelDev2.add(foto2, gbcFoto2);
        panelDev2.add(contacto2, gbcContacto2);
        panelDev2.add(carnet2, gbcCarnet2);
        panelDev2.add(carrera2, gbcCarrera2);

        // Organizar componentes con GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(titulo, gbc);

        // Desarrollador 1
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(panelDev1, gbc);

        // Desarrollador 2
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelPrincipal.add(panelDev2, gbc);

   
        add(panelPrincipal);

       
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SoporteFrame::new);
    }
}