package cr.ufide.sandwich;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaJuego extends JFrame {

    private JPanel panelCaja;

    public VentanaJuego() {
        super("The Sandwich Guy — Avance I");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(2, 2, 8, 8));

        add(crearPanelSitio("Caja", true));
        add(crearPanelSitio("Mazo", false));
        add(crearPanelSitio("Mano", false));
        add(crearPanelSitio("Pozo", false));

        cargarCajaConBarajaCompleta();
    }

    private JComponent crearPanelSitio(String titulo, boolean esCaja) {
        JPanel interior = new JPanel(new GridLayout(0, 11, 6, 6));
        interior.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JScrollPane sc = new JScrollPane(interior);
        sc.setBorder(BorderFactory.createTitledBorder(titulo));
        if (esCaja) panelCaja = interior;
        return sc;
    }

    private void cargarCajaConBarajaCompleta() {
        if (panelCaja == null) return;
        List<Carta> baraja = generarBaraja();
        panelCaja.removeAll();
        for (Carta c : baraja) {
            JButton b = new JButton(c.toString());
            b.setMargin(new Insets(2, 6, 2, 6));
            b.setFocusable(false);
            panelCaja.add(b);
        }
        panelCaja.revalidate();
        panelCaja.repaint();
    }

    private List<Carta> generarBaraja() {
        List<Carta> cartas = new ArrayList<>(52);
        for (Carta.Palo p : Carta.Palo.values()) {
            for (int v = 1; v <= 13; v++) {
                cartas.add(new Carta(p, v));
            }
        }
        return cartas;
    }
}
