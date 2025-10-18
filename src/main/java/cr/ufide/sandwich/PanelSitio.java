package cr.ufide.sandwich.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import cr.ufide.sandwich.Carta;

public class PanelSitio extends JPanel {
    private final JLabel titulo = new JLabel();
    private final JPanel grid = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public PanelSitio(String nombre) {
        setLayout(new BorderLayout());
        titulo.setText(nombre);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);
        grid.setName("grid");
        add(new JScrollPane(grid), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    }

    public void setCartas(List<Carta> cartas) {
        grid.removeAll();
        for (Carta c : cartas) {
            grid.add(new JLabel(c.toString()));
        }
        grid.revalidate();
        grid.repaint();
    }
}