package cr.ufide.sandwich;

public class Carta {

    public enum Palo {
        CORAZONES("♥", ColorCarta.ROJO),
        DIAMANTES("♦", ColorCarta.ROJO),
        PICAS("♠", ColorCarta.NEGRO),
        TREBOLES("♣", ColorCarta.NEGRO);

        private final String simbolo;
        private final ColorCarta color;

        Palo(String simbolo, ColorCarta color) {
            this.simbolo = simbolo;
            this.color = color;
        }

        public String getSimbolo() {
            return simbolo;
        }

        public ColorCarta getColorEnum() {
            return color;
        }
    }

    public enum ColorCarta {
        ROJO, NEGRO
    }

    private final Palo palo;
    private final int valor;
    private final String nombre;

    public Carta(Palo palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.nombre = calcularNombre(valor);
    }

    private String calcularNombre(int valor) {
        switch (valor) {
            case 1:  return "A";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(valor);
        }
    }

    public Palo getPalo() {
        return palo;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    // ESTE es el que usa PanelSitio: String "rojo"/"negro"
    public String getColor() {
        return palo.getColorEnum().name().toLowerCase();
    }

    // ESTE es el que usamos nosotros en la lógica del juego
    public ColorCarta getColorEnum() {
        return palo.getColorEnum();
    }

    @Override
    public String toString() {
        return nombre + palo.getSimbolo();
    }
}

