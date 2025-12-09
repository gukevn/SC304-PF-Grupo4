package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.List;

public class Pozo {

    private final List<Carta> cartas = new ArrayList<>();

    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    public int size() {
        return cartas.size();
    }

    public void vaciar() {
        cartas.clear();
    }

    // ========== NECESARIO PARA XML ==========
    public List<Carta> copiarCartas() {
        return new ArrayList<>(cartas);
    }

    public void cargarCartas(List<Carta> nuevas) {
        cartas.clear();
        cartas.addAll(nuevas);
    }
}
