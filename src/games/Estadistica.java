package games;

public class Estadistica {
    private String nombreJuego;
    private Jugador ganador; // Solo guardamos al ganador o null si es empate
    private PuntuacionComparable puntuacion;

    public Estadistica(String nombreJuego, Jugador ganador, PuntuacionComparable puntuacion) {
        this.nombreJuego = nombreJuego;
        this.ganador = ganador;
        this.puntuacion = puntuacion;
    }

    public Jugador getGanador() { return ganador; }
    public PuntuacionComparable getPuntuacion() { return puntuacion; }

    public void mostrar() {
        if (ganador != null && puntuacion != null) {
            System.out.println("🏆 " + ganador.getNombre() + " | Juego: " + nombreJuego + " | Puntos: " + puntuacion.getDesglose());
        } else {
            System.out.println("🤝 Empate en " + nombreJuego + " (Sin puntos)");
        }
    }
}