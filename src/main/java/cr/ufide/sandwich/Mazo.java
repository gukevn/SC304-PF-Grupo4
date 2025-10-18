package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Mazo {

    private final Stack<Carta> cartas = new Stack<>();

    public Mazo() {}

    public Mazo(List<Carta> inicial) {
        if (inicial != null) {
            cartas.addAll(inicial);
        }
    }

    /** Baraja usando Random provisto (útil para pruebas). */
    public void barajar(Random rnd) {
        Collections.shuffle(cartas, rnd);
    }

    /** Baraja con Random por defecto. */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /** Pone una carta encima del mazo. */
    public void poner(Carta c) {
        if (c != null) cartas.push(c);
    }

    /** Toma la carta superior; devuelve null si está vacío. */
    public Carta tomar() {
        return cartas.isEmpty() ? null : cartas.pop();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int size() {
        return cartas.size();
    }

    /** Copia para lectura/GUI (no expone la pila real). */
    public List<Carta> getCartas() {
        return new ArrayList<>(cartas);
    }
}
