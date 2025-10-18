package cr.ufide.sandwich;

import cr.ufide.sandwich.ui.PanelSitio;
import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class VentanaJuego extends JFrame {
    private final Caja caja = new Caja();
    private final Mazo mazo = new Mazo();
    private final Mano mano = new Mano();
    private final Pozo pozo = new Pozo();

    private final PanelSitio panelCaja = new PanelSitio("Caja");
    private final PanelSitio panelMazo = new PanelSitio("Mazo");
    private final PanelSitio panelMano = new PanelSitio("Mano");
    private final PanelSitio panelPozo = new PanelSitio("Pozo");

    private final JButton btnBarajar, btnRepartir, btnDescartar, btnReiniciar;

    public VentanaJuego() {
        super("The Sandwich Guy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel centros = new JPanel(new GridLayout(2, 2, 8, 8));
        centros.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        centros.add(panelCaja);
        centros.add(panelMazo);
        centros.add(panelMano);
        centros.add(panelPozo);
        add(centros, BorderLayout.CENTER);

        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnBarajar = new JButton("1. Barajar");
        btnRepartir = new JButton("2. Repartir a Mano");
        btnDescartar = new JButton("3. Descartar (3)");
        btnReiniciar = new JButton("Reiniciar Juego");

        panelControles.add(btnBarajar);
        panelControles.add(btnRepartir);
        panelControles.add(btnDescartar);
        panelControles.add(btnReiniciar);
        add(panelControles, BorderLayout.SOUTH);

        btnBarajar.addActionListener(e -> barajar());
        btnRepartir.addActionListener(e -> repartir());
        btnDescartar.addActionListener(e -> descartar());
        btnReiniciar.addActionListener(e -> iniciarJuego());

        iniciarJuego();

        setSize(950, 700);
        setLocationRelativeTo(null);
    }

    private void iniciarJuego() {
        while (!caja.isEmpty()) caja.removerPrimera();
        while (!mazo.isEmpty()) mazo.removerCarta();
        while (!mano.isEmpty()) mano.removerCarta(0);
        while (!pozo.isEmpty()) pozo.removerCarta();

        String[] palos = {"♥", "♦", "♠", "♣"};
        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                caja.agregarCarta(new Carta(valor, palo));
            }
        }
        actualizarPanelesYBotones();
    }

    private void barajar() {
        if (caja.isEmpty()) return;
        List<Carta> cartasParaBarajar = caja.getCartas();
        Collections.shuffle(cartasParaBarajar);
        for (Carta carta : cartasParaBarajar) {
            mazo.agregarCarta(carta);
        }
        while (!caja.isEmpty()) {
            caja.removerPrimera();
        }
        actualizarPanelesYBotones();
    }

    private void repartir() {
        if (mazo.isEmpty() || mano.size() >= 8) return;
        int cartasARepartir = 8 - mano.size();
        for (int i = 0; i < cartasARepartir && !mazo.isEmpty(); i++) {
            mano.agregarCarta(mazo.removerCarta());
        }
        actualizarPanelesYBotones();
    }

    private void descartar() {
        if (mano.size() < 3) return;
        pozo.agregarCarta(mano.removerCarta(0));
        pozo.agregarCarta(mano.removerCarta(0));
        pozo.agregarCarta(mano.removerCarta(0));
        actualizarPanelesYBotones();
    }

    private void actualizarPanelesYBotones() {
        panelCaja.setCartas(caja.getCartas());
        panelMazo.setCartas(mazo.getCartas());
        panelMano.setCartas(mano.getCartas());
        panelPozo.setCartas(pozo.getCartas());

        btnBarajar.setEnabled(!caja.isEmpty());
        btnRepartir.setEnabled(!mazo.isEmpty() && mano.size() < 8);
        btnDescartar.setEnabled(mano.size() >= 3);
    }
}