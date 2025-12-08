package cr.ufide.sandwich;

import java.util.LinkedList;
import java.util.List;

public class Caja {
    private final LinkedList<Carta> cartas = new LinkedList<>();

    public void agregarCarta(Carta carta) {
        if (carta != null) {
            cartas.addLast(carta);
        }
    }

    public Carta removerPrimera() {
        return cartas.isEmpty() ? null : cartas.removeFirst();
    }

    public Carta removerUltima() {
        return cartas.isEmpty() ? null : cartas.removeLast();
    }

    public Carta verPrimera() {
        return cartas.peekFirst();
    }

    public Carta verUltima() {
        return cartas.peekLast();
    }

    public List<Carta> getCartas() {
        return new LinkedList<>(cartas);
    }

    public int size() {
        return cartas.size();
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public void vaciar() {
        cartas.clear();
    }
}
