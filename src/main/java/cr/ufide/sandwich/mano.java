package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private final List<Carta> cartas = new ArrayList<>(8);

    public void agregarCarta(Carta carta) {
        if (cartas.size() < 8) cartas.add(carta);
    }
    public Carta getCarta(int indice) { return cartas.get(indice % cartas.size()); }
    public Carta removerCarta(int indice) { return cartas.remove(indice); }
    public List<Carta> getCartas() { return new ArrayList<>(cartas); }
    public int size() { return cartas.size(); }
    public boolean isEmpty() { return cartas.isEmpty(); }
}