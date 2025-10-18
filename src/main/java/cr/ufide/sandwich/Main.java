package cr.ufide.sandwich;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ejecutar la creación de la GUI en el Event Dispatch Thread (EDT) de Swing
        SwingUtilities.invokeLater(() -> {
            VentanaJuego ventana = new VentanaJuego();
            ventana.setVisible(true);
        });
    }
}