package cr.ufide.sandwich.ui;

import cr.ufide.sandwich.Carta;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PanelSitio extends JPanel {
    private final JPanel cardContainer;

    public PanelSitio(String titulo) {
        super(new BorderLayout());
        setBorder(new TitledBorder(titulo));

        cardContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setCartas(List<Carta> cartas) {
        cardContainer.removeAll();
        for (Carta carta : cartas) {
            JLabel cardLabel = new JLabel(carta.toString());
            cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cardLabel.setOpaque(true);
            cardLabel.setBackground(Color.WHITE);
            cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardLabel.setPreferredSize(new Dimension(50, 70));
            cardLabel.setFont(new Font("Arial", Font.BOLD, 14));

            if ("rojo".equals(carta.getColor())) {
                cardLabel.setForeground(Color.RED);
            } else {
                cardLabel.setForeground(Color.BLACK);
            }
            cardContainer.add(cardLabel);
        }
        cardContainer.revalidate();
        cardContainer.repaint();
    }
}