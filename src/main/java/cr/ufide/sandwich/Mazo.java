package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private final List<Carta> cartas = new ArrayList<>();

    // Carga las cartas desde una lista y las mezcla
    public void cargarDesdeListaAleatoria(List<Carta> origen) {
        cartas.clear();
        List<Carta> copia = new ArrayList<>(origen);
        Collections.shuffle(copia);
        cartas.addAll(copia);
    }

    // Roba la última carta del mazo
    public Carta robar() {
        if (cartas.isEmpty()) return null;
        return cartas.remove(cartas.size() - 1);
    }

    // Devuelve la lista interna (para visualización)
    public List<Carta> getCartas() {
        return cartas;
    }

    public int size() {
        return cartas.size();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    // 👉 IMPORTANTE: Este método es el que faltaba y provocaba el error
    public void vaciar() {
        cartas.clear();
    }

    // Para guardar en XML
    public List<Carta> copiarCartas() {
        return new ArrayList<>(cartas);
    }

    // Para cargar desde XML
    public void cargarCartas(List<Carta> nuevas) {
        cartas.clear();
        cartas.addAll(nuevas);
    }
}