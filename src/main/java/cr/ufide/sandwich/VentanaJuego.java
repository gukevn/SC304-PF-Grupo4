package cr.ufide.sandwich;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaJuego extends JFrame {
    private final Caja caja = new Caja();
    private final Mazo mazo = new Mazo();
    private final Mano mano = new Mano();
    private final Pozo pozo = new Pozo();

    private final ui.PanelSitio panelCaja = new ui.PanelSitio("Caja");
    private final ui.PanelSitio panelMazo = new ui.PanelSitio("Mazo");
    private final ui.PanelSitio panelMano = new ui.PanelSitio("Mano");
    private final ui.PanelSitio panelPozo = new ui.PanelSitio("Pozo");

    public VentanaJuego() {
        super("The Sandwich Guy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear las 52 cartas y agregar a Caja
        String[] palos = {"♥", "♦", "♠", "♣"};
        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                caja.agregarCarta(new Carta(valor, palo));
            }
        }

        // Configurar paneles
        JPanel centros = new JPanel(new GridLayout(2, 2, 8, 8));
        centros.add(panelCaja);
        centros.add(panelMazo);
        centros.add(panelMano);
        centros.add(panelPozo);
        add(centros, BorderLayout.CENTER);

        // Mostrar cartas iniciales en Caja
        actualizarPaneles();

        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private void actualizarPaneles() {
        panelCaja.setCartas(caja.getCartas());
        panelMazo.setCartas(mazo.getCartas());
        panelMano.setCartas(mano.getCartas());
        panelPozo.setCartas(pozo.getCartas());
    }

    // Getters para acceder a las estructuras (útil para futuras integraciones, ej. botones)
    public Caja getCaja() { return caja; }
    public Mazo getMazo() { return mazo; }
    public Mano getMano() { return mano; }
    public Pozo getPozo() { return pozo; }
}