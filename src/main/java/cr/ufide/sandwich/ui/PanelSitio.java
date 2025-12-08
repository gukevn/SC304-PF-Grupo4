package cr.ufide.sandwich.ui;

import cr.ufide.sandwich.Carta;
import cr.ufide.sandwich.Carta.ColorCarta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelSitio extends JPanel {

    public interface CartaClickListener {
        void onCartaClick(int index, Carta carta);
    }

    private final JLabel lblTitulo;
    private final JPanel panelCartas;
    private final JScrollPane scroll;

    private List<Carta> cartas = new ArrayList<>();
    private boolean seleccionable = false;
    private CartaClickListener clickListener;
    private final List<Integer> indicesSeleccionados = new ArrayList<>();

    public PanelSitio(String titulo) {
        setLayout(new BorderLayout());

        lblTitulo = new JLabel(titulo);
        lblTitulo.setBorder(new EmptyBorder(4, 4, 4, 4));

        panelCartas = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        scroll = new JScrollPane(panelCartas);
        scroll.setBorder(null);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void setTitulo(String texto) {
        lblTitulo.setText(texto);
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = (cartas == null) ? new ArrayList<>() : new ArrayList<>(cartas);
        refrescarCartas();
    }

    public void setSeleccionable(boolean seleccionable) {
        this.seleccionable = seleccionable;
        limpiarSeleccion();
    }

    public void setCartaClickListener(CartaClickListener listener) {
        this.clickListener = listener;
    }

    public List<Integer> getIndicesSeleccionados() {
        return new ArrayList<>(indicesSeleccionados);
    }

    public void limpiarSeleccion() {
        indicesSeleccionados.clear();
        refrescarCartas();
    }

    private void refrescarCartas() {
        panelCartas.removeAll();

        if (cartas != null) {
            for (int i = 0; i < cartas.size(); i++) {
                Carta carta = cartas.get(i);
                JLabel lbl = crearLabelCarta(carta, i);
                panelCartas.add(lbl);
            }
        }

        panelCartas.revalidate();
        panelCartas.repaint();
    }

    private JLabel crearLabelCarta(Carta carta, int index) {
        JLabel lbl = new JLabel(carta.toString(), SwingConstants.CENTER);
        lbl.setBorder(new EmptyBorder(2, 4, 2, 4));

        // *** CORRECCIÓN IMPORTANTE ***
        Color colorTexto = (carta.getColorEnum() == ColorCarta.ROJO)
                ? Color.RED
                : Color.BLACK;

        lbl.setForeground(colorTexto);

        if (seleccionable) {
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    toggleSeleccion(lbl, index);
                    if (clickListener != null) {
                        clickListener.onCartaClick(index, carta);
                    }
                }
            });
        }

        return lbl;
    }

    private void toggleSeleccion(JLabel lbl, int index) {
        if (indicesSeleccionados.contains(index)) {
            indicesSeleccionados.remove((Integer) index);
            lbl.setBorder(new EmptyBorder(2, 4, 2, 4));
        } else {
            if (indicesSeleccionados.size() >= 3) {
                return; // máx 3 seleccionadas
            }
            indicesSeleccionados.add(index);
            lbl.setBorder(new LineBorder(Color.BLUE, 2));
        }
    }
}
