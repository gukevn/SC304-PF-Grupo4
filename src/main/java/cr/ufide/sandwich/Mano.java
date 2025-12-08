package cr.ufide.sandwich;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mano {

    // Lista interna real de la mano
    private final List<Carta> cartas = new ArrayList<>(8);

    // Agregar carta respetando máximo de 8
    public void agregarCarta(Carta carta) {
        if (carta != null && cartas.size() < 8) {
            cartas.add(carta);
        }
    }

    // Devuelve la lista real que usa la GUI
    public List<Carta> getCartas() {
        return cartas;
    }

    public int size() {
        return cartas.size();
    }

    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    // Ordenar por palo y luego por valor
    public void ordenarPorPaloYValor() {
        cartas.sort(
            Comparator
                .comparing(Carta::getPalo)
                .thenComparingInt(Carta::getValor)
        );
    }

    // Acceso circular opcional
    public Carta getCarta(int indice) {
        if (cartas.isEmpty()) {
            return null;
        }
        int idx = indice % cartas.size();
        if (idx < 0) {
            idx += cartas.size();
        }
        return cartas.get(idx);
    }

    public Carta removerCarta(int indice) {
        if (indice < 0 || indice >= cartas.size()) {
            return null;
        }
        return cartas.remove(indice);
    }
}
