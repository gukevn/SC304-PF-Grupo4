package cr.ufide.sandwich.ui;


import cr.ufide.sandwich.Carta;


import javax.swing.*;
import java.awt.*;
import java.util.List;


/** Panel reutilizable para mostrar cartas y un título con el conteo. */
public class PanelSitio extends JPanel {
private final JLabel titulo = new JLabel("", SwingConstants.LEFT);
private final DefaultListModel<String> modelo = new DefaultListModel<>();
private final JList<String> lista = new JList<>(modelo);


public PanelSitio(String t) {
setLayout(new BorderLayout(6,6));
titulo.setText(t);
titulo.setFont(titulo.getFont().deriveFont(Font.BOLD));
add(titulo, BorderLayout.NORTH);


lista.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
add(new JScrollPane(lista), BorderLayout.CENTER);
setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
}


public void setTitulo(String t){ titulo.setText(t); }


public void setCartas(List<Carta> cartas){
modelo.clear();
if (cartas == null) return;
for (Carta c : cartas) {
String item = String.format("%-6s palo=%-8s color=%s",
c.toString(), c.getPalo().name(), c.getColor());
modelo.addElement(item);
}
}
}