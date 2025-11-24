package cr.ufide.sandwich;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/** Cola (queue) de cartas descartadas. */
public class Pozo {
private final Queue<Carta> cartas = new LinkedList<>();


public void encolar(Carta c) { cartas.add(c); }
public Carta desencolar() { return cartas.poll(); }
public Carta verFrente() { return cartas.peek(); }
public int size() { return cartas.size(); }
public boolean isEmpty() { return cartas.isEmpty(); }


public List<Carta> getCartas(){ return new LinkedList<>(cartas); }
}