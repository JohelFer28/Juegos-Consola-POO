import games.Estadistica;
import games.Jugable;
import games.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {
    // La lista de juegos está FUERTEMENTE CONDICIONADA a la interfaz Jugable
    private List<Jugable> juegosInstalados;
    private List<Estadistica> historialEstadisticas;

    public Consola() {
        this.juegosInstalados = new ArrayList<>();
        this.historialEstadisticas = new ArrayList<>();
    }

    // "Instalar" juego: Solo acepta objetos de clases que implementen Jugable
    public void instalarJuego(Jugable juego) {
        juegosInstalados.add(juego);
        System.out.println("🕹️ Juego '" + juego.getNombreJuego() + "' instalado con éxito.");
    }

    public List<Jugable> getJuegosInstalados() {
        return juegosInstalados;
    }

    public void lanzarJuego(int indice, Scanner sc, Jugador j1, Jugador j2) {
        if (indice >= 0 && indice < juegosInstalados.size()) {
            Jugable juegoSeleccionado = juegosInstalados.get(indice);
            // Polimorfismo: Ejecuta el start() del juego correspondiente
            Estadistica stat = juegoSeleccionado.start(sc, j1, j2);
            historialEstadisticas.add(stat);
        } else {
            System.out.println("⚠️ Selección inválida.");
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("\n=================================");
        System.out.println("📊 HISTORIAL DE ESTADÍSTICAS 📊");
        System.out.println("=================================");
        if (historialEstadisticas.isEmpty()) {
            System.out.println("No hay partidas registradas aún.");
        } else {
            for (Estadistica est : historialEstadisticas) {
                est.mostrar();
            }
        }
    }
}