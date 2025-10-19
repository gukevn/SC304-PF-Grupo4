package cr.ufide.sandwich.ui;

import cr.ufide.sandwich.Carta;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelSitio extends JPanel {

    private final JPanel cardContainer;

    public PanelSitio(String titulo) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
                null, titulo, TitledBorder.LEFT, TitledBorder.TOP));

        // Panel interno que contiene las “cartas” como labels
        cardContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        cardContainer.setBackground(new Color(0xEEEEEE));

        // Scroll horizontal/vertical por si se llena
        JScrollPane scroll = new JScrollPane(cardContainer,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);
    }

    public void setTitulo(String titulo) {
        setBorder(BorderFactory.createTitledBorder(
                null, titulo, TitledBorder.LEFT, TitledBorder.TOP));
        revalidate();
        repaint();
    }

    public void setCartas(List<Carta> cartas) {
        cardContainer.removeAll();
        if (cartas != null) {
            for (Carta carta : cartas) {
                cardContainer.add(crearLabelCarta(carta));
            }
        }
        cardContainer.revalidate();
        cardContainer.repaint();
    }

    private JComponent crearLabelCarta(Carta carta) {
        JLabel cardLabel = new JLabel(carta.toString());
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        cardLabel.setOpaque(true);
        cardLabel.setBackground(Color.WHITE);
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLabel.setPreferredSize(new Dimension(50, 70));
        cardLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cardLabel.setToolTipText(carta.getNombre() + " de " + carta.getPalo().name().toLowerCase());

        // Color de texto por palo
        if ("rojo".equals(carta.getColor())) {
            cardLabel.setForeground(Color.RED);
        } else {
            cardLabel.setForeground(Color.BLACK);
        }
        return cardLabel;
    }
}
