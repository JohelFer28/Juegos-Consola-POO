package games;

public class Estadistica {
    private String nombreJuego;
    private Jugador j1;
    private Jugador j2;
    private Jugador ganador;
    private long tiempoSegundos;
    private int movimientosTotales;

    public Estadistica(String nombreJuego, Jugador j1, Jugador j2, Jugador ganador, long tiempoSegundos, int movimientosTotales) {
        this.nombreJuego = nombreJuego;
        this.j1 = j1;
        this.j2 = j2;
        this.ganador = ganador;
        this.tiempoSegundos = tiempoSegundos;
        this.movimientosTotales = movimientosTotales;
    }

    public void mostrar() {
        System.out.println("-------------------------------------------------");
        System.out.println("Juego: " + nombreJuego);
        System.out.println("Jugadores: " + j1.getNombre() + " vs " + j2.getNombre());
        System.out.println("Duración: " + tiempoSegundos + " segundos");
        System.out.println("Movimientos totales: " + movimientosTotales);
        System.out.println("Movimientos de " + j1.getNombre() + ": " + j1.getMovimientos());
        System.out.println("Movimientos de " + j2.getNombre() + ": " + j2.getMovimientos());
        if (ganador != null) {
            System.out.println("🏆 Ganador: " + ganador.getNombre());
        } else {
            System.out.println("🤝 Resultado: Empate");
        }
        System.out.println("-------------------------------------------------");
    }
}