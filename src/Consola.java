import games.Estadistica;
import games.Jugable;
import games.Jugador;
import games.PuntuacionComparable;
import games.batalla.PuntuacionBatalla;
import games.nim.PuntuacionNim;
import games.tictactoe.PuntuacionTresEnRaya;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private List<Jugable> juegosInstalados;
    private List<Estadistica> historialEstadisticas;

    public Consola() {
        this.juegosInstalados = new ArrayList<>();
        this.historialEstadisticas = new ArrayList<>();
        cargarEscenarioFicticio(); // <-- DATOS DEL PROFE AQUÍ
    }

    public void instalarJuego(Jugable juego) {
        juegosInstalados.add(juego);
    }

    public List<Jugable> getJuegosInstalados() {
        return juegosInstalados;
    }

    public void lanzarJuego(int indice, Scanner sc, Jugador j1, Jugador j2) {
        if (indice >= 0 && indice < juegosInstalados.size()) {
            Estadistica stat = juegosInstalados.get(indice).start(sc, j1, j2);
            historialEstadisticas.add(stat);
        }
    }

    public void mostrarEstadisticasTop3() {
        System.out.println("\n=================================");
        System.out.println("🔥 TOP 3 MEJORES PUNTUACIONES 🔥");
        System.out.println("=================================");

        // 1. Filtrar solo partidas ganadas (que tienen puntuación)
        List<Estadistica> ganadas = new ArrayList<>();
        for (Estadistica e : historialEstadisticas) {
            if (e.getPuntuacion() != null) ganadas.add(e);
        }

        // 2. Ordenar usando el método comparar() de nuestra interfaz
        // Algoritmo burbuja simple para no usar librerías complejas
        for (int i = 0; i < ganadas.size() - 1; i++) {
            for (int j = 0; j < ganadas.size() - i - 1; j++) {
                PuntuacionComparable p1 = ganadas.get(j).getPuntuacion();
                PuntuacionComparable p2 = ganadas.get(j + 1).getPuntuacion();

                // Si p2 es mayor que p1, intercambiamos (para que quede de mayor a menor)
                if (p2.comparar(p1) > 0) {
                    Estadistica temp = ganadas.get(j);
                    ganadas.set(j, ganadas.get(j + 1));
                    ganadas.set(j + 1, temp);
                }
            }
        }

        // 3. Mostrar el Top 3
        int limite = Math.min(3, ganadas.size());
        for (int i = 0; i < limite; i++) {
            System.out.print("#" + (i + 1) + " ");
            ganadas.get(i).mostrar();
        }
        if (ganadas.isEmpty()) System.out.println("No hay datos de victorias registrados.");
    }

    // Escenario Ficticio para evitar jugar pruebas manuales repetitivas
    private void cargarEscenarioFicticio() {
        Jugador profe = new Jugador("Profe_Oscar");
        Jugador monse = new Jugador("Monse");
        Jugador io = new Jugador("Johel");

        // Metemos partidas como si ya se hubieran jugado
        historialEstadisticas.add(new Estadistica("Tres en Raya", profe, new PuntuacionTresEnRaya(4))); // Muy bueno
        historialEstadisticas.add(new Estadistica("Tres en Raya", monse, new PuntuacionTresEnRaya(7))); // Decente
        historialEstadisticas.add(new Estadistica("Batalla Naval", io, new PuntuacionBatalla(4, 2))); // Épico
        historialEstadisticas.add(new Estadistica("Juego de Nim", profe, new PuntuacionNim(10))); // Maestro del Nim
    }
}