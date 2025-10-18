package cr.ufide.sandwich;

import java.util.LinkedList;
import java.util.List;

public class Caja {
    private final LinkedList<Carta> cartas = new LinkedList<>();

    public void agregarCarta(Carta carta) { cartas.addLast(carta); }
    public Carta removerPrimera() { return cartas.removeFirst(); }
    public Carta removerUltima() { return cartas.removeLast(); }
    public Carta verPrimera() { return cartas.getFirst(); }
    public Carta verUltima() { return cartas.getLast(); }
    public List<Carta> getCartas() { return new LinkedList<>(cartas); } // Copia para vista
    public int size() { return cartas.size(); }
    public boolean isEmpty() { return cartas.isEmpty(); }
}