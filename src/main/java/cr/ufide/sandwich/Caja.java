package cr.ufide.sandwich;


import java.util.LinkedList;
import java.util.List;


/** Lista doble (doblemente enlazada) de cartas. */
public class Caja {
private final LinkedList<Carta> cartas = new LinkedList<>();


public void agregarAlFinal(Carta c) { cartas.addLast(c); }
public Carta removerPrimera() { return cartas.removeFirst(); }
public Carta removerUltima() { return cartas.removeLast(); }
public Carta verPrimera() { return cartas.getFirst(); }
public Carta verUltima() { return cartas.getLast(); }
public int size() { return cartas.size(); }
public boolean isEmpty() { return cartas.isEmpty(); }


/** Copia segura para la GUI. */
public List<Carta> getCartas(){ return new LinkedList<>(cartas); }
}