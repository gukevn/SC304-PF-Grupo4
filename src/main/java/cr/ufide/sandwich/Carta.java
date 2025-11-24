package cr.ufide.sandwich;


public class Carta {


public enum Palo {
CORAZONES("♥", "rojo"),
DIAMANTES("♦", "rojo"),
PICAS("♠", "negro"),
TREBOLES("♣", "negro");


private final String simbolo;
private final String color;
Palo(String simbolo, String color) { this.simbolo = simbolo; this.color = color; }
public String getSimbolo() { return simbolo; }
public String getColor() { return color; }
}


private final Palo palo;
private final int valor; // 1..13 (A..K)


public Carta(Palo palo, int valor) {
if (valor < 1 || valor > 13) throw new IllegalArgumentException("Valor inválido: " + valor);
this.palo = palo;
this.valor = valor;
}


public Palo getPalo() { return palo; }
public int getValor(){ return valor; }
public String getColor() { return palo.getColor(); }


public String getNombre() {
return switch (valor) {
case 1 -> "A";
case 11 -> "J";
case 12 -> "Q";
case 13 -> "K";
default -> String.valueOf(valor);
};
}


@Override public String toString() { return "[" + getNombre() + "|" + palo.getSimbolo() + "]"; }
}
