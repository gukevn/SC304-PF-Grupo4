package cr.ufide.sandwich;

import cr.ufide.sandwich.ui.PanelSitio;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VentanaJuego extends JFrame {

    private final Caja caja = new Caja();
    private final Mazo mazo = new Mazo();
    private final Mano mano = new Mano();
    private final Pozo pozo = new Pozo();

    private PanelSitio panelCaja;
    private PanelSitio panelMazo;
    private PanelSitio panelMano;
    private PanelSitio panelPozo;

    public VentanaJuego() {
        setTitle("Juego de cartas PF Avance II");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        inicializarComponentes();
        inicializarDatos();
    }

    private void inicializarComponentes() {
        panelCaja = new PanelSitio("Caja");
        panelMazo = new PanelSitio("Mazo");
        panelMano = new PanelSitio("Mano");
        panelPozo = new PanelSitio("Pozo");

        JButton btnBarajar = new JButton("Barajar: Caja a Mazo");

        btnBarajar.addActionListener(e -> barajarCajaEnMazo());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnBarajar);

        JPanel panelCentros = new JPanel(new GridLayout(2, 2, 8, 8));
        panelCentros.add(panelCaja);
        panelCentros.add(panelMazo);
        panelCentros.add(panelMano);
        panelCentros.add(panelPozo);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(panelBotones, BorderLayout.NORTH);
        getContentPane().add(panelCentros, BorderLayout.CENTER);
    }

    private void inicializarDatos() {
        List<Carta> baraja = generarBaraja();
        for (Carta c : baraja) {
            caja.agregarCarta(c);
        }
        refrescarVistas();
    }

    private void barajarCajaEnMazo() {
        List<Carta> cartasCaja = caja.getCartas();
        if (cartasCaja.isEmpty()) {
            return;
        }
        mazo.cargarDesdeListaAleatoria(cartasCaja);
        caja.vaciar();
        refrescarVistas();
    }

    private void refrescarVistas() {
        panelCaja.setCartas(caja.getCartas());
        panelCaja.setTitulo("Caja (" + caja.size() + " cartas)");

        panelMazo.setCartas(mazo.getCartas());
        panelMazo.setTitulo("Mazo (" + mazo.size() + " cartas)");

        panelMano.setCartas(mano.getCartas());
        panelMano.setTitulo("Mano (" + mano.size() + " cartas)");

        panelPozo.setCartas(pozo.getCartas());
        panelPozo.setTitulo("Pozo (" + pozo.size() + " cartas)");
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
