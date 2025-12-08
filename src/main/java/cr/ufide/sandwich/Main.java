// Proyecto final The Sandwich Guy
// Repositorio: https://github.com/gukevn/SC304-PF-Grupo4


package cr.ufide.sandwich;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaJuego v = new VentanaJuego();
            v.setVisible(true);
        });
    }
}

