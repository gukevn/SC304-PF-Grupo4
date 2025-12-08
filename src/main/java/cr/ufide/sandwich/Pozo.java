package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.List;

public class Pozo {

    private final List<Carta> cartas = new ArrayList<>();

    public void agregarCarta(Carta carta) {
        if (carta != null) {
            cartas.add(carta);
        }
    }

    // si quieres sacar del frente tipo cola
    public Carta removerCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.remove(0);
    }

    public Carta verFrente() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.get(0);
    }

    // aquí está la diferencia clave
    public List<Carta> getCartas() {
        return cartas;   // ya no es copia
    }

    public int size() {
        return cartas.size();
    }

    public boolean isEmpty() {
        return cartas.isEmpty();
    }
}
