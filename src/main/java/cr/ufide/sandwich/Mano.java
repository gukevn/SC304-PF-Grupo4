package cr.ufide.sandwich;


import java.util.ArrayList;
import java.util.List;


/** Lista circular de hasta 8 cartas. */
public class Mano {
private final List<Carta> cartas = new ArrayList<>(8);


public void limpiar() { cartas.clear(); }


public void agregarCarta(Carta carta) {
if (cartas.size() < 8) cartas.add(carta);
}

public Carta getCartaCircular(int i) {
if (cartas.isEmpty()) throw new IllegalStateException("Mano vacía");
int idx = Math.floorMod(i, cartas.size());
return cartas.get(idx);
}

public Carta removerIndice(int i) { return cartas.remove(i); }
public int size() { return cartas.size(); }
public boolean isEmpty() { return cartas.isEmpty(); }
public List<Carta> getCartas(){ return new ArrayList<>(cartas); }
}