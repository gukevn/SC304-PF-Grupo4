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

        public String getSimbolo() { return simbolo; }
        public String getColor()   { return color; }
    }

    private final Palo palo;
    private final int valor;   // 1=A, 11=J, 12=Q, 13=K
    private final String nombre;

    public Carta(Palo palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.nombre = generarNombre(valor);
    }

    private String generarNombre(int valor) {
        switch (valor) {
            case 1:  return "A";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(valor);
        }
    }

    public Palo getPalo()     { return palo; }
    public int  getValor()    { return valor; }
    public String getNombre() { return nombre; }
    public String getColor()  { return palo.getColor(); }

    @Override
    public String toString() {
        return nombre + palo.getSimbolo();
    }
}
