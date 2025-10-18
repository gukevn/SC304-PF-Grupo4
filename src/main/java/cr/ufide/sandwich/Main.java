package cr.ufide.sandwich;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Crear la Caja con las 52 cartas ordenadas
        Caja caja = new Caja();
        String[] palos = {"♥", "♦", "♠", "♣"};
        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                caja.agregarCarta(new Carta(valor, palo));
            }
        }
        System.out.println("Caja creada con " + caja.size() + " cartas.");

        // 2. "Barajar" moviendo todas las cartas de Caja a Mazo (aleatorizando el orden)
        Mazo mazo = new Mazo();
        List<Carta> cartasParaBarajar = caja.getCartas(); // Obtener copia para barajar
        Collections.shuffle(cartasParaBarajar); // Barajar la lista
        for (Carta carta : cartasParaBarajar) {
            mazo.agregarCarta(carta);
        }
        // Vaciar la Caja (ya que las cartas se movieron por referencia)
        while (!caja.isEmpty()) {
            caja.removerPrimera();
        }
        System.out.println("Cartas barajadas y movidas al Mazo. Mazo tiene " + mazo.size() + " cartas.");

        // 3. Sacar 8 cartas del Mazo a la Mano
        Mano mano = new Mano();
        for (int i = 0; i < 8 && !mazo.isEmpty(); i++) {
            mano.agregarCarta(mazo.removerCarta());
        }
        System.out.println("8 cartas sacadas a la Mano. Mano tiene " + mano.size() + " cartas: " + mano.getCartas());

        // 4. Simular descarte de 3 cartas de la Mano al Pozo (ej. una tripleta válida)
        Pozo pozo = new Pozo();
        if (mano.size() >= 3) {
            // Remover las primeras 3 como ejemplo (en el juego real, se elegirían por sándwich)
            pozo.agregarCarta(mano.removerCarta(0));
            pozo.agregarCarta(mano.removerCarta(0));
            pozo.agregarCarta(mano.removerCarta(0));
            System.out.println("3 cartas descartadas al Pozo. Mano ahora tiene " + mano.size() + " cartas: " + mano.getCartas());
            System.out.println("Pozo tiene " + pozo.size() + " cartas: " + pozo.getCartas());
        }

        // 5. Verificar estados finales
        System.out.println("Estados finales:");
        System.out.println("- Caja: " + caja.size() + " cartas");
        System.out.println("- Mazo: " + mazo.size() + " cartas");
        System.out.println("- Mano: " + mano.size() + " cartas");
        System.out.println("- Pozo: " + pozo.size() + " cartas");

        // Nota: En el juego real, aquí se verificaría si hay sándwich válido, se rellenaría la Mano, etc.
        System.out.println("¡Juego básico simulado exitosamente!");
    }
}