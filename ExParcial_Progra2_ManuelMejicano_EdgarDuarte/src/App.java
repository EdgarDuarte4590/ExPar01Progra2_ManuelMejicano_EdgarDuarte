import javax.swing.UIManager;


import com.formdev.flatlaf.themes.FlatMacLightLaf;

import vista.MenuPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
           System.out.println("Algo salio mal");
        }
        MenuPrincipal main = new MenuPrincipal();
        main.setVisible(true);
        main.setLocationRelativeTo(null); 
        main.setResizable(false); 
    }

}
