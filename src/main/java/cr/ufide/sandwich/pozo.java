package cr.ufide.sandwich;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class Pozo {
    private final Queue<Carta> cartas = new LinkedList<>();

    public void agregarCarta(Carta carta) { cartas.add(carta); }
    public Carta removerCarta() { return cartas.poll(); }
    public Carta verFrente() { return cartas.peek(); }
    public List<Carta> getCartas() { return new LinkedList<>(cartas); }
    public int size() { return cartas.size(); }
    public boolean isEmpty() { return cartas.isEmpty(); }
}