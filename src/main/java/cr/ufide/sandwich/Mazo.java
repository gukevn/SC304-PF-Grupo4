package cr.ufide.sandwich;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/** Pila (stack) de cartas. */
public class Mazo {
private final Stack<Carta> cartas = new Stack<>();


public boolean estaVacio() { return cartas.isEmpty(); }
public int size() { return cartas.size(); }


public void push(Carta c) { cartas.push(c); }
public Carta pop() { return cartas.pop(); }
public Carta peek() { return cartas.peek(); }

/**
* Carga el mazo con todas las cartas provenientes de una lista,
* previamente barajadas aleatoriamente.
*/
public void cargarBarajado(List<Carta> origen) {
cartas.clear();
List<Carta> copia = new ArrayList<>(origen);
Collections.shuffle(copia);
for (Carta c : copia) cartas.push(c);
}

/** Copia segura para la GUI. */
public List<Carta> getCartas() { return new ArrayList<>(cartas); }
}