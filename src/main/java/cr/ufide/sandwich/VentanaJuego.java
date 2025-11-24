package cr.ufide.sandwich;

import cr.ufide.sandwich.ui.PanelSitio;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaJuego extends JFrame {

    private final PanelSitio panelCaja = new PanelSitio("Caja (0 cartas)");
    private final PanelSitio panelMazo = new PanelSitio("Mazo (0 cartas)");
    private final PanelSitio panelMano = new PanelSitio("Mano (0 cartas)");
    private final PanelSitio panelPozo = new PanelSitio("Pozo (0 cartas)");

    private final JButton btnNueva   = new JButton("Crear partida nueva");
    private final JButton btnBarajar = new JButton("Barajar (Caja → Mazo)");

    private Caja caja;
    private Mazo mazo;
    private Mano mano;
    private Pozo pozo;

    public VentanaJuego() {
        super("The Sandwich Guy — Avance II");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        acciones.add(btnNueva);
        acciones.add(btnBarajar);
        add(acciones, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        grid.add(panelCaja);
        grid.add(panelMazo);
        grid.add(panelMano);
        grid.add(panelPozo);
        add(grid, BorderLayout.CENTER);

        btnNueva.addActionListener(e -> crearPartidaNueva());
        btnBarajar.addActionListener(e -> barajarCajaEnMazo());

        crearPartidaNueva();
    }

    private void crearPartidaNueva() {
        caja = new Caja();
        mazo = new Mazo();
        mano = new Mano();
        pozo = new Pozo();

        List<Carta> baraja = generarBaraja();
        for (Carta c : baraja) caja.agregarAlFinal(c);

        refrescarVistas();
    }

    private void barajarCajaEnMazo() {
        List<Carta> tmp = new ArrayList<>(caja.getCartas());
        mazo.cargarBarajado(tmp);
        caja = new Caja(); // mostrar Caja vacía
        refrescarVistas();
    }

    private void refrescarVistas() {
        // Las CUATRO secciones con render visual
        renderCartasVisual(panelCaja, "Caja (" + caja.size() + " cartas)", caja.getCartas());
        renderCartasVisual(panelMazo, "Mazo (" + mazo.size() + " cartas)", mazo.getCartas());
        renderCartasVisual(panelMano, "Mano (" + mano.size() + " cartas)", mano.getCartas());
        renderCartasVisual(panelPozo, "Pozo (" + pozo.size() + " cartas)", pozo.getCartas());
    }

    /** Renderiza cartas tipo “A♥ / 10♣”, con color rojo/negro, dentro del panel dado. */
    private void renderCartasVisual(JPanel panel, String titulo, List<Carta> cartas) {
        panel.removeAll();
        panel.setLayout(new BorderLayout(4, 4));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD));
        panel.add(lblTitulo, BorderLayout.NORTH);

        JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        cards.setBackground(UIManager.getColor("Panel.background"));

        for (Carta c : cartas) {
            String face = c.getNombre() + c.getPalo().getSimbolo(); // ej. "A♥"
            JLabel lbl = new JLabel(face, SwingConstants.CENTER);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 18));
            lbl.setForeground("rojo".equals(c.getColor()) ? Color.RED : Color.BLACK);
            lbl.setOpaque(true);
            lbl.setBackground(Color.WHITE);
            lbl.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            lbl.setPreferredSize(new Dimension(40, 60));
            cards.add(lbl);
        }

        JScrollPane scroller = new JScrollPane(
                cards,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        panel.add(scroller, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }

    private List<Carta> generarBaraja() {
        List<Carta> cartas = new ArrayList<>(52);
        for (Carta.Palo p : Carta.Palo.values()) {
            for (int v = 1; v <= 13; v++) cartas.add(new Carta(p, v));
        }
        return cartas;
    }
}