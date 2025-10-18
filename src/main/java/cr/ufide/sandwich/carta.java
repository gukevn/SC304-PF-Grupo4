package cr.ufide.sandwich;

import java.util.Objects;

public final class Carta {
    private final int valor; // 1-13 (A=1, J=11, Q=12, K=13)
    private final String palo; // "♥", "♦", "♠", "♣"
    private final String color; // "rojo" o "negro"
    private final String nombre; // "A", "2", ..., "K"
    private final String simbolo; // "A♥", etc.

    public Carta(int valor, String palo) {
        if (valor < 1 || valor > 13) throw new IllegalArgumentException("Valor debe ser 1-13");
        this.valor = valor;
        this.palo = Objects.requireNonNull(palo);
        this.color = (palo.equals("♥") || palo.equals("♦")) ? "rojo" : "negro";
        this.nombre = getNombreFromValor(valor);
        this.simbolo = nombre + palo;
    }

    private String getNombreFromValor(int valor) {
        switch (valor) {
            case 1: return "A";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(valor);
        }
    }

    // Getters
    public int getValor() { return valor; }
    public String getPalo() { return palo; }
    public String getColor() { return color; }
    public String getNombre() { return nombre; }
    public String getSimbolo() { return simbolo; }

    // Método para calcular diferencia (útil para sándwiches, considerando vuelta)
    public int getDiferencia(Carta otra) {
        int diff = Math.abs(this.valor - otra.valor);
        if (diff > 6) diff = 13 - diff; // Vuelta K-A
        return diff;
    }

    @Override
    public String toString() { return simbolo; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carta carta = (Carta) obj;
        return valor == carta.valor && palo.equals(carta.palo);
    }

    @Override
    public int hashCode() { return Objects.hash(valor, palo); }
}