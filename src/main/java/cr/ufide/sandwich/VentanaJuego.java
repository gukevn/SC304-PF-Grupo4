package cr.ufide.sandwich;

import cr.ufide.sandwich.ui.PanelSitio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaJuego extends JFrame {

    private PanelSitio panelCaja;
    private PanelSitio panelMazo;
    private PanelSitio panelMano;
    private PanelSitio panelPozo;

    public VentanaJuego() {
        super("The Sandwich Guy — Avance I");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        // Grid 2x2 para las cuatro secciones
        JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCaja = new PanelSitio("Caja (0 cartas)");
        panelMazo = new PanelSitio("Mazo (boca abajo, 0)");
        panelMano = new PanelSitio("Mano (máx 8, 0)");
        panelPozo = new PanelSitio("Pozo (descartes, 0)");

        grid.add(panelCaja);
        grid.add(panelMazo);
        grid.add(panelMano);
        grid.add(panelPozo);

        // Barra superior (placeholder)
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton btnBarajar = new JButton("Barajar (placeholder)");
        toolbar.add(btnBarajar);

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);

        // Generar baraja y mostrarla en Caja
        List<Carta> baraja = generarBaraja();
        panelCaja.setCartas(baraja);
        panelCaja.setTitulo("Caja (" + baraja.size() + " cartas)");
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
