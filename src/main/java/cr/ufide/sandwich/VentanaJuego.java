package cr.ufide.sandwich;

import cr.ufide.sandwich.ui.PanelSitio;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaJuego extends JFrame {

    private final Caja caja = new Caja();
    private final Mazo mazo = new Mazo();
    private final Mano mano = new Mano();
    private final Pozo pozo = new Pozo();

    private PanelSitio panelCaja;
    private PanelSitio panelMazo;
    private PanelSitio panelMano;
    private PanelSitio panelPozo;

    private List<Carta> seleccionActual = new ArrayList<>();

    public VentanaJuego() {
        setTitle("Juego de cartas PF Avance II");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        inicializarComponentes();
        inicializarDatos();
    }

    private void inicializarComponentes() {
        panelCaja = new PanelSitio("Caja");
        panelMazo = new PanelSitio("Mazo");
        panelMano = new PanelSitio("Mano");
        panelPozo = new PanelSitio("Pozo");

        panelMano.setSeleccionable(true);
        panelMano.setCartaClickListener((i, c) -> actualizarSeleccionDesdePanel());

        JButton btnBarajar = new JButton("Barajar: Caja a Mazo");
        JButton btnOrdenar = new JButton("Ordenar mano");
        JButton btnTurnoAuto = new JButton("Probar sándwich (auto)");
        JButton btnGuardar = new JButton("Guardar partida");
        JButton btnCargar = new JButton("Cargar partida");
        JButton btnReiniciar = new JButton("Reiniciar juego");

        btnBarajar.addActionListener(e -> barajarCajaEnMazo());
        btnOrdenar.addActionListener(e -> ordenarMano());
        btnTurnoAuto.addActionListener(e -> jugarTurnoAutomatico());
        btnGuardar.addActionListener(e -> guardarPartidaXML());
        btnCargar.addActionListener(e -> cargarPartidaXML());
        btnReiniciar.addActionListener(e -> reiniciarJuego());

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(btnBarajar);
        botones.add(btnOrdenar);
        botones.add(btnTurnoAuto);
        botones.add(btnGuardar);
        botones.add(btnCargar);
        botones.add(btnReiniciar);

        JPanel centros = new JPanel(new GridLayout(2, 2, 8, 8));
        centros.add(panelCaja);
        centros.add(panelMazo);
        centros.add(panelMano);
        centros.add(panelPozo);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(botones, BorderLayout.NORTH);
        getContentPane().add(centros, BorderLayout.CENTER);
    }

    private void inicializarDatos() {
        caja.vaciar();
        mazo.vaciar();
        mano.vaciar();
        pozo.vaciar();

        for (Carta c : generarBaraja()) caja.agregarCarta(c);

        refrescarVistas();
    }

    private void reiniciarJuego() {
        inicializarDatos();
        JOptionPane.showMessageDialog(this, "El juego ha sido reiniciado.");
    }

    private void barajarCajaEnMazo() {
        if (!caja.getCartas().isEmpty()) {
            mazo.cargarDesdeListaAleatoria(caja.getCartas());
            caja.vaciar();
            repartirManoInicial();
        }
        refrescarVistas();
    }

    private void repartirManoInicial() {
        while (mano.size() < 8 && !mazo.estaVacio()) {
            mano.agregarCarta(mazo.robar());
        }
    }

    private void refrescarVistas() {
        panelCaja.setCartas(caja.getCartas());
        panelCaja.setTitulo("Caja (" + caja.size() + ")");

        panelMazo.setCartas(mazo.getCartas());
        panelMazo.setTitulo("Mazo (" + mazo.size() + ")");

        panelMano.setCartas(mano.getCartas());
        panelMano.setTitulo("Mano (" + mano.size() + ")");

        panelPozo.setCartas(pozo.getCartas());
        panelPozo.setTitulo("Pozo (" + pozo.size() + ")");
    }

    private List<Carta> generarBaraja() {
        List<Carta> cartas = new ArrayList<>();
        for (Carta.Palo p : Carta.Palo.values()) {
            for (int v = 1; v <= 13; v++) cartas.add(new Carta(p, v));
        }
        return cartas;
    }

    private void actualizarSeleccionDesdePanel() {
        seleccionActual.clear();
        List<Carta> manoActual = mano.getCartas();
        for (int idx : panelMano.getIndicesSeleccionados()) {
            if (idx >= 0 && idx < manoActual.size()) {
                seleccionActual.add(manoActual.get(idx));
            }
        }
    }

    private int distanciaCircular(int a, int b) {
        int directa = Math.abs(a - b);
        int circular = 13 - directa;
        return Math.min(directa, circular);
    }

    private TipoSandwich evaluarSandwich(Carta a, Carta b, Carta c) {
        int d1 = distanciaCircular(a.getValor(), b.getValor());
        int d2 = distanciaCircular(b.getValor(), c.getValor());
        if (d1 != d2) return TipoSandwich.INVALIDO;

        boolean palo = a.getPalo() == b.getPalo() && b.getPalo() == c.getPalo();
        boolean color = a.getColorEnum() == b.getColorEnum() && b.getColorEnum() == c.getColorEnum();

        if (palo) return TipoSandwich.MISMO_PALO;
        if (color) return TipoSandwich.MISMO_COLOR;
        return TipoSandwich.DISTINTO_COLOR;
    }

    private List<List<Carta>> permutaciones(Carta a, Carta b, Carta c) {
        return Arrays.asList(
                Arrays.asList(a, b, c),
                Arrays.asList(a, c, b),
                Arrays.asList(b, a, c),
                Arrays.asList(b, c, a),
                Arrays.asList(c, a, b),
                Arrays.asList(c, b, a)
        );
    }

    private void aplicarSandwich(TipoSandwich tipo, List<Carta> tripleta) {
        for (Carta c : tripleta) {
            mano.eliminarCarta(c);
            pozo.agregarCarta(c);
        }

        int n =
                tipo == TipoSandwich.MISMO_PALO ? 4 :
                tipo == TipoSandwich.MISMO_COLOR ? 3 :
                tipo == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

        while (n-- > 0 && mano.size() < 8 && !mazo.estaVacio()) {
            mano.agregarCarta(mazo.robar());
        }

        refrescarVistas();
        verificarVictoriaDerrota();
    }

    private void verificarVictoriaDerrota() {
        if (mazo.estaVacio()) {
            JOptionPane.showMessageDialog(this, "Ganaste: no quedan cartas.");
            return;
        }
        if (!existeSandwichEnMano()) {
            JOptionPane.showMessageDialog(this, "Perdiste: no quedan sándwiches posibles.");
        }
    }

    private boolean existeSandwichEnMano() {
        List<Carta> c = mano.getCartas();
        int n = c.size();
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    for (List<Carta> t : permutaciones(c.get(i), c.get(j), c.get(k)))
                        if (evaluarSandwich(t.get(0), t.get(1), t.get(2)) != TipoSandwich.INVALIDO)
                            return true;
        return false;
    }

    private void jugarTurnoAutomatico() {
        List<Carta> manoActual = mano.getCartas();

        if (seleccionActual.size() == 3) {
            procesarPermutaciones(seleccionActual.get(0),
                    seleccionActual.get(1),
                    seleccionActual.get(2));
            return;
        }

        if (manoActual.size() < 3) {
            JOptionPane.showMessageDialog(this, "No hay suficientes cartas.");
            return;
        }

        procesarPermutaciones(manoActual.get(0), manoActual.get(1), manoActual.get(2));
    }

    private void procesarPermutaciones(Carta c1, Carta c2, Carta c3) {
        List<List<Carta>> perms = permutaciones(c1, c2, c3);
        StringBuilder sb = new StringBuilder("Permutaciones:\n\n");

        TipoSandwich mejor = TipoSandwich.INVALIDO;
        List<Carta> mejorTripleta = null;

        for (int i = 0; i < perms.size(); i++) {
            List<Carta> t = perms.get(i);
            TipoSandwich tipo = evaluarSandwich(t.get(0), t.get(1), t.get(2));

            int sum =
                    tipo == TipoSandwich.MISMO_PALO ? 4 :
                    tipo == TipoSandwich.MISMO_COLOR ? 3 :
                    tipo == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

            sb.append((i + 1)).append(". ").append(t.get(0)).append(" ")
                    .append(t.get(1)).append(" ").append(t.get(2))
                    .append(" -> ").append(tipo)
                    .append(" (").append(sum).append(" nuevas)\n");

            if (esMejor(tipo, mejor)) {
                mejor = tipo;
                mejorTripleta = t;
            }
        }

        JOptionPane.showMessageDialog(this, sb.toString());

        if (mejor == TipoSandwich.INVALIDO) {
            JOptionPane.showMessageDialog(this, "No hay sándwich válido.");
            return;
        }

        int resp = JOptionPane.showConfirmDialog(
                this,
                "Mejor: " + mejor + ". ¿Aplicar?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (resp == JOptionPane.YES_OPTION) {
            aplicarSandwich(mejor, mejorTripleta);
            seleccionActual.clear();
            panelMano.limpiarSeleccion();
        }
    }

    private boolean esMejor(TipoSandwich nuevo, TipoSandwich actual) {
        int nv =
                nuevo == TipoSandwich.MISMO_PALO ? 4 :
                nuevo == TipoSandwich.MISMO_COLOR ? 3 :
                nuevo == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

        int ac =
                actual == TipoSandwich.MISMO_PALO ? 4 :
                actual == TipoSandwich.MISMO_COLOR ? 3 :
                actual == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

        return nv > ac;
    }

    private void ordenarMano() {
        mano.ordenarPorPaloYValor();
        refrescarVistas();
    }

    private void guardarPartidaXML() {
        try {
            GestorXML.guardar("partida.xml", caja, mazo, mano, pozo);
            JOptionPane.showMessageDialog(this, "Partida guardada.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }

    private void cargarPartidaXML() {
        try {
            GestorXML.cargar("partida.xml", caja, mazo, mano, pozo);
            refrescarVistas();
            JOptionPane.showMessageDialog(this, "Partida cargada.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage());
        }
    }
}
