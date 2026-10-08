package games.tictactoe;
import games.PuntuacionComparable;

public class PuntuacionTresEnRaya implements PuntuacionComparable {
    private int puntos;
    private int movimientosUsados;

    public PuntuacionTresEnRaya(int movimientosUsados) {
        this.movimientosUsados = movimientosUsados;
        // Lógica de puntos: Ganas 500 base, pero pierdes 20 por cada movimiento que te tomó ganar.
        this.puntos = 500 - (movimientosUsados * 20);
    }

    @Override
    public int comparar(PuntuacionComparable otra) {
        // Devuelve un entero: positivo si este es mayor, negativo si es menor, 0 si son iguales
        return Integer.compare(this.puntos, otra.getValorNumerico());
    }

    @Override
    public int getValorNumerico() { return puntos; }

    @Override
    public String getDesglose() {
        return puntos + " pts (500 base - " + movimientosUsados + " movs x 20)";
    }
}