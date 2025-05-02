package vista;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuPrincipal extends JFrame{

    public MenuPrincipal(){
        setTitle("Menu Principal");
        setSize(1366, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(null); 

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null); // Establecer el diseño nulo para el panel principal
        panelPrincipal.setBounds(433, 160,500, 400); // Establecer el tamaño del panel principal

        panelPrincipal.setBackground(Color.BLUE); // Color de fondo transparente
        super.add(panelPrincipal); // Agregar el panel principal a la ventana
        
    }
    
}

