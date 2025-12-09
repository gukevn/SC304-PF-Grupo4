package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.List;

public class Caja {

    private final List<Carta> cartas = new ArrayList<>();

    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    public void vaciar() {
        cartas.clear();
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int size() {
        return cartas.size();
    }

    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    // ========== Necesario para XML ==========
    public List<Carta> copiarCartas() {
        return new ArrayList<>(cartas);
    }

    public void cargarCartas(List<Carta> nuevas) {
        cartas.clear();
        cartas.addAll(nuevas);
    }
}
