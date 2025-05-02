package vista.oficiales;

public class VistaOficiales extends javax.swing.JFrame {

    public VistaOficiales() {
        initComponents();
        setLocationRelativeTo(null); 
        setResizable(false); 
    }

    private void initComponents() {
        setTitle("Vista Oficiales");
        setSize(1366, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
    }
    public void mostrar() {
        setVisible(true);
    }
    public void ocultar() {
        setVisible(false);
    }

}
