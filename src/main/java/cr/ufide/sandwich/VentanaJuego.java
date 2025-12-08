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

        // Activar selección manual en la mano
        panelMano.setSeleccionable(true);
        panelMano.setCartaClickListener((index, carta) -> actualizarSeleccionDesdePanel());

        JButton btnBarajar = new JButton("Barajar: Caja a Mazo");
        JButton btnOrdenar = new JButton("Ordenar mano");
        JButton btnTurnoAuto = new JButton("Probar sándwich (auto)");
        JButton btnGuardar = new JButton("Guardar partida");
        JButton btnCargar = new JButton("Cargar partida");

        btnBarajar.addActionListener(e -> barajarCajaEnMazo());
        btnOrdenar.addActionListener(e -> ordenarMano());
        btnTurnoAuto.addActionListener(e -> jugarTurnoAutomatico());
        btnGuardar.addActionListener(e -> guardarPartidaXML());
        btnCargar.addActionListener(e -> cargarPartidaXML());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnBarajar);
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnTurnoAuto);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCargar);

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
        if (!cartasCaja.isEmpty()) {
            mazo.cargarDesdeListaAleatoria(cartasCaja);
            caja.vaciar();
            repartirManoInicial();
        }
        refrescarVistas();
    }

    private void repartirManoInicial() {
        while (mano.size() < 8 && !mazo.estaVacio()) {
            Carta c = mazo.robar();
            if (c == null) break;
            mano.agregarCarta(c);
        }
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

    // Selección manual

    private void actualizarSeleccionDesdePanel() {
        seleccionActual.clear();
        List<Integer> indices = panelMano.getIndicesSeleccionados();
        List<Carta> cartasMano = mano.getCartas();

        for (Integer i : indices) {
            if (i >= 0 && i < cartasMano.size()) {
                seleccionActual.add(cartasMano.get(i));
            }
        }
    }

    // Lógica de sándwich

    private int distanciaCircular(int a, int b) {
        int directa = Math.abs(a - b);
        int circular = 13 - directa;
        return Math.min(directa, circular);
    }

    private TipoSandwich evaluarSandwich(Carta c1, Carta c2, Carta c3) {
        int v1 = c1.getValor();
        int v2 = c2.getValor();
        int v3 = c3.getValor();

        int d12 = distanciaCircular(v1, v2);
        int d23 = distanciaCircular(v2, v3);

        if (d12 != d23) return TipoSandwich.INVALIDO;

        boolean mismoPalo =
                c1.getPalo() == c2.getPalo() &&
                c2.getPalo() == c3.getPalo();

        boolean mismoColor =
                c1.getColorEnum() == c2.getColorEnum() &&
                c2.getColorEnum() == c3.getColorEnum();

        if (mismoPalo) return TipoSandwich.MISMO_PALO;
        if (mismoColor) return TipoSandwich.MISMO_COLOR;
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

    // Aplicar sándwich

    private void aplicarSandwich(TipoSandwich tipo, List<Carta> tripleta) {
        List<Carta> cartasMano = mano.getCartas();
        List<Carta> cartasPozo = pozo.getCartas();
        List<Carta> cartasMazo = mazo.getCartas();

        for (Carta c : tripleta) {
            cartasMano.remove(c);
            cartasPozo.add(c);
        }

        int cartasATomar =
                tipo == TipoSandwich.MISMO_PALO ? 4 :
                tipo == TipoSandwich.MISMO_COLOR ? 3 :
                tipo == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

        while (cartasATomar > 0 && cartasMano.size() < 8 && !cartasMazo.isEmpty()) {
            cartasMano.add(cartasMazo.remove(cartasMazo.size() - 1));
            cartasATomar--;
        }

        refrescarVistas();
        verificarVictoriaDerrota();
    }

    private void verificarVictoriaDerrota() {
        if (mazo.getCartas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ganaste: el mazo quedó vacío.");
            return;
        }
        if (!existeSandwichEnMano()) {
            JOptionPane.showMessageDialog(this, "No hay ningún sándwich posible en la mano. Perdiste.");
        }
    }

    private boolean existeSandwichEnMano() {
        List<Carta> cartas = mano.getCartas();
        int n = cartas.size();

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    for (List<Carta> trip : permutaciones(cartas.get(i), cartas.get(j), cartas.get(k)))
                        if (evaluarSandwich(trip.get(0), trip.get(1), trip.get(2)) != TipoSandwich.INVALIDO)
                            return true;

        return false;
    }

    // Turno automático

    private void jugarTurnoAutomatico() {
        List<Carta> cartasMano = mano.getCartas();

        if (seleccionActual.size() == 3) {
            Carta c1 = seleccionActual.get(0);
            Carta c2 = seleccionActual.get(1);
            Carta c3 = seleccionActual.get(2);

            procesarPermutaciones(c1, c2, c3);
            return;
        }

        if (cartasMano.size() < 3) {
            JOptionPane.showMessageDialog(this, "No hay suficientes cartas en la mano.");
            return;
        }

        procesarPermutaciones(cartasMano.get(0), cartasMano.get(1), cartasMano.get(2));
    }

    private void procesarPermutaciones(Carta c1, Carta c2, Carta c3) {
        List<List<Carta>> perms = permutaciones(c1, c2, c3);

        StringBuilder sb = new StringBuilder();
        sb.append("Permutaciones evaluadas:\n\n");

        TipoSandwich mejor = TipoSandwich.INVALIDO;
        List<Carta> mejorTripleta = null;

        for (int i = 0; i < perms.size(); i++) {
            List<Carta> t = perms.get(i);
            TipoSandwich tipo = evaluarSandwich(t.get(0), t.get(1), t.get(2));

            int cartasNuevas =
                    tipo == TipoSandwich.MISMO_PALO ? 4 :
                    tipo == TipoSandwich.MISMO_COLOR ? 3 :
                    tipo == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

            sb.append((i + 1))
              .append(". ")
              .append(t.get(0)).append("  ")
              .append(t.get(1)).append("  ")
              .append(t.get(2))
              .append("  -> ").append(tipo)
              .append("  (").append(cartasNuevas).append(" nuevas)\n");

            if (esMejor(tipo, mejor)) {
                mejor = tipo;
                mejorTripleta = t;
            }
        }

        JOptionPane.showMessageDialog(this, sb.toString());

        if (mejor == TipoSandwich.INVALIDO) {
            JOptionPane.showMessageDialog(this, "Ninguna permutación válida.");
            return;
        }

        int resp = JOptionPane.showConfirmDialog(
                this,
                "Mejor combinación: " + mejor + ". ¿Aplicar?",
                "Aplicar sándwich",
                JOptionPane.YES_NO_OPTION
        );

        if (resp == JOptionPane.YES_OPTION) {
            aplicarSandwich(mejor, mejorTripleta);
            seleccionActual.clear();
            panelMano.limpiarSeleccion();
        }
    }

    private boolean esMejor(TipoSandwich nuevo, TipoSandwich actual) {
        if (nuevo == TipoSandwich.INVALIDO) return false;
        if (actual == TipoSandwich.INVALIDO) return true;

        int scoreNuevo =
                nuevo == TipoSandwich.MISMO_PALO ? 4 :
                nuevo == TipoSandwich.MISMO_COLOR ? 3 :
                nuevo == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

        int scoreActual =
                actual == TipoSandwich.MISMO_PALO ? 4 :
                actual == TipoSandwich.MISMO_COLOR ? 3 :
                actual == TipoSandwich.DISTINTO_COLOR ? 2 : 0;

        return scoreNuevo > scoreActual;
    }

    // Auxiliares

    private void ordenarMano() {
        mano.ordenarPorPaloYValor();
        refrescarVistas();
    }

    private void guardarPartidaXML() {
        JOptionPane.showMessageDialog(
            this,
            "Función Guardar partida (XML) aún no implementada.\nGuardar XML",
            "Guardar partida",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cargarPartidaXML() {
        JOptionPane.showMessageDialog(
            this,
            "Función Cargar partida (XML) aún no implementada.\nCargar XML",
            "Cargar partida",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
