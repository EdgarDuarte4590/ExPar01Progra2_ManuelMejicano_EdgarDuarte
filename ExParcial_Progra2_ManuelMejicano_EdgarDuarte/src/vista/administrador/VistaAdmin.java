package vista.administrador;

import javax.swing.JFrame;

public class VistaAdmin extends JFrame{
    
        public VistaAdmin() {
            setTitle("Vista Administrador");
            setSize(1366, 720);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); 
            setLayout(null); 
            
            inicializarComponentes();
        }

        public void mostrar() {
            setVisible(true);
            
        }
    
        private void inicializarComponentes() {

        }
            // Aquí puedes agregar los componentes específicos de la vista del administrador
}
