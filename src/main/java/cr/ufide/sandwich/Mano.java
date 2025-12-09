package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mano {

    private final List<Carta> cartas = new ArrayList<>();

    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    public void eliminarCarta(Carta c) {
        cartas.remove(c);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int size() {
        return cartas.size();
    }

    public void vaciar() {
        cartas.clear();
    }

    public void ordenarPorPaloYValor() {
        Collections.sort(cartas, (a, b) -> {
            if (a.getPalo() != b.getPalo()) {
                return a.getPalo().ordinal() - b.getPalo().ordinal();
            }
            return a.getValor() - b.getValor();
        });
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

