package games;

public class Jugador {
    private String nombre;
    private int movimientos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.movimientos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void registrarMovimiento() {
        this.movimientos++;
    }

    public void resetMovimientos() {
        this.movimientos = 0;
    }
}