package games;

import java.util.Scanner;

public abstract class Juego implements Jugable {
    protected String nombreJuego;
    protected Jugador j1;
    protected Jugador j2;
    protected Jugador ganador;
    protected int movimientosTotales;
    private boolean empate;

    public Juego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
        this.movimientosTotales = 0;
        this.empate = false;
    }

    @Override
    public String getNombreJuego() {
        return nombreJuego;
    }

    protected abstract void iniciarTablero();
    protected abstract void jugarTurno(Scanner sc, Jugador jugadorActual);
    protected abstract boolean verificarFinJuego();

    @Override
    public Estadistica start(Scanner sc, Jugador jugador1, Jugador jugador2) {
        this.j1 = jugador1;
        this.j2 = jugador2;
        this.ganador = null;
        this.empate = false;
        this.movimientosTotales = 0;

        j1.resetMovimientos();
        j2.resetMovimientos();

        System.out.println("\n=== INICIANDO: " + nombreJuego.toUpperCase() + " ===");
        iniciarTablero();

        long tiempoInicio = System.currentTimeMillis();
        Jugador turnoActual = j1;

        while (!verificarFinJuego()) {
            jugarTurno(sc, turnoActual);
            movimientosTotales++;
            turnoActual.registrarMovimiento();

            if (verificarFinJuego()) {
                if (!empate) ganador = turnoActual;
                break;
            }
            turnoActual = (turnoActual == j1) ? j2 : j1;
        }

        long tiempoFin = System.currentTimeMillis();
        long duracionSegundos = (tiempoFin - tiempoInicio) / 1000;

        if (ganador != null) {
            System.out.println("\n¡Felicidades " + ganador.getNombre() + ", has ganado!");
        } else {
            System.out.println("\nEl juego ha terminado en empate.");
        }

        return new Estadistica(nombreJuego, j1, j2, ganador, duracionSegundos, movimientosTotales);
    }

    protected void declararEmpate() {
        this.empate = true;
    }
}