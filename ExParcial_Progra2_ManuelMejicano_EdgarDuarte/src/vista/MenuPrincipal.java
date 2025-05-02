package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuPrincipal extends JFrame{

    public MenuPrincipal(){
        setTitle("Menu Principal");
        setSize(1366, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null); // Establecer el diseño nulo para el panel principal
        panelPrincipal.setBounds(0, 0, 500, 720); // Establecer el tamaño del panel principal
        

    }
    
}

