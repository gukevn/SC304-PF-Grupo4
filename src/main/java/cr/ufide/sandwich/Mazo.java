package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Mazo {

    private final Stack<Carta> cartas = new Stack<>();

    public Mazo() {
    }

    public Mazo(List<Carta> inicial) {
        cargarDesdeLista(inicial);
    }

    public void cargarDesdeLista(List<Carta> lista) {
        cartas.clear();
        if (lista == null) {
            return;
        }
        for (Carta c : lista) {
            if (c != null) {
                cartas.push(c);
            }
        }
    }

    public void cargarDesdeListaAleatoria(List<Carta> lista) {
        cartas.clear();
        if (lista == null || lista.isEmpty()) {
            return;
        }

        List<Carta> copia = new ArrayList<>(lista);
        Collections.shuffle(copia, new Random());
        for (Carta c : copia) {
            cartas.push(c);
        }
    }

    public void push(Carta carta) {
        if (carta != null) {
            cartas.push(carta);
        }
    }

    // Robar del tope
    public Carta robar() {
        return cartas.isEmpty() ? null : cartas.pop();
    }

    // Ver tope sin sacar
    public Carta verTope() {
        return cartas.isEmpty() ? null : cartas.peek();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int size() {
        return cartas.size();
    }

    // Se expone la pila real como lista para que VentanaJuego pueda modificarla
    public List<Carta> getCartas() {
        return cartas;
    }
}
