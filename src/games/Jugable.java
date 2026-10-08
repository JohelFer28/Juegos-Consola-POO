package games;

import java.util.Scanner;

public interface Jugable {
    // Garantiza que todo elemento que se instale en la Consola tenga este método
    Estadistica start(Scanner sc, Jugador j1, Jugador j2);

    // Método para obtener el nombre del juego y mostrarlo dinámicamente en el menú
    String getNombreJuego();
}