package cr.ufide.sandwich;

public class Carta {

    public enum Palo {
        CORAZONES("♥", "rojo"),
        DIAMANTES("♦", "rojo"),
        PICAS("♠", "negro"),
        TREBOLES("♣", "negro");

        private final String simbolo;
        private final String color;

        Palo(String simbolo, String color) {
            this.simbolo = simbolo;
            this.color = color;
        }

        public String getSimbolo() {
            return simbolo;
        }

        public String getColor() {
            return color;
        }
    }

    private final Palo palo;
    private final int valor; // 1..13 (A..K)

    public Carta(Palo palo, int valor) {
        if (valor < 1 || valor > 13) {
            throw new IllegalArgumentException("Valor inválido: " + valor);
        }
        this.palo = palo;
        this.valor = valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return palo.getColor();
    }

    // switch clásico, compatible con Java 8, 11, 17, 21...
    public String getNombre() {
        switch (valor) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(valor);
        }
    }

    @Override
    public String toString() {
        return "[" + getNombre() + "|" + palo.getSimbolo() + "]";
    }
}
