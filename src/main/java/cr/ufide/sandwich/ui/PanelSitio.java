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

        cardContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JScrollPane scroll = new JScrollPane(cardContainer);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll, BorderLayout.CENTER);
    }

    public void setTitulo(String titulo) {
        if (getBorder() instanceof TitledBorder) {
            TitledBorder tb = (TitledBorder) getBorder();
            tb.setTitle(titulo);
            repaint();
        }
    }

    public void setCartas(List<Carta> cartas) {
        cardContainer.removeAll();
        if (cartas != null) {
            for (Carta c : cartas) {
                cardContainer.add(crearLabelCarta(c));
            }
        }
        revalidate();
        repaint();
    }

    private JLabel crearLabelCarta(Carta carta) {
        String texto = "";
        if (carta != null) {
            texto = carta.toString();
        }

        JLabel cardLabel = new JLabel(texto);
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLabel.setPreferredSize(new Dimension(50, 70));
        cardLabel.setFont(new Font("Arial", Font.BOLD, 14));

        if (carta != null) {
            cardLabel.setToolTipText(
                    carta.getNombre() + " de " + carta.getPalo().name().toLowerCase()
            );

            if ("rojo".equals(carta.getColor())) {
                cardLabel.setForeground(Color.RED);
            } else {
                cardLabel.setForeground(Color.BLACK);
            }
        }

        return cardLabel;
    }
}
