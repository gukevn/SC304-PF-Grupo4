package cr.ufide.sandwich;

import java.util.Stack;
import java.util.List;

public class Mazo {
    private final Stack<Carta> cartas = new Stack<>();

    public void agregarCarta(Carta carta) { cartas.push(carta); }
    public Carta removerCarta() { return cartas.pop(); }
    public Carta verTope() { return cartas.peek(); }
    public List<Carta> getCartas() { return new Stack<>(cartas); } // Copia para vista
    public int size() { return cartas.size(); }
    public boolean isEmpty() { return cartas.isEmpty(); }
}