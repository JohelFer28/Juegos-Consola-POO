package games.batalla;

import games.Juego;
import games.Jugador;
import java.util.Scanner;

public class JuegoBatalla extends Juego {
    private char[][] tablero;
    private boolean[][] barcosOcultos;
    private int[] puntajes;
    private int barcosHundidos;
    private final int TOTAL_BARCOS = 4; // Reducido a 4 para partidas más ágiles por consola

    public JuegoBatalla() {
        super("Batalla Naval");
        tablero = new char[5][5];
        barcosOcultos = new boolean[5][5];
        puntajes = new int[2]; // Jugador 1 y Jugador 2
    }

    @Override
    protected void iniciarTablero() {
        barcosHundidos = 0;
        puntajes[0] = 0;
        puntajes[1] = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = '~';
                barcosOcultos[i][j] = false;
            }
        }

        // Colocación estratégica de barcos sin usar Random (2 barcos por jugador)
        Scanner sc = new Scanner(System.in);
        System.out.println("--- FASE DE PREPARACIÓN (Estrategia sin azar) ---");
        colocarBarcosJugador(sc, j1, 0, 2);
        colocarBarcosJugador(sc, j2, 1, 2);

        System.out.println("\n¡Todos los barcos han sido desplegados en el mapa!");
    }

    private void colocarBarcosJugador(Scanner sc, Jugador jugador, int idJugador, int cantidadBarcos) {
        System.out.println("\n" + jugador.getNombre() + ", vas a esconder " + cantidadBarcos + " barcos en el tablero.");
        int colocados = 0;

        while (colocados < cantidadBarcos) {
            imprimirTableroPrivado();
            System.out.println("Ingresa posición para tu barco #" + (colocados + 1) + ":");
            System.out.print("Fila (0-4): ");
            int f = leerNumero(sc);
            System.out.print("Columna (0-4): ");
            int c = leerNumero(sc);

            if (f >= 0 && f < 5 && c >= 0 && c < 5 && !barcosOcultos[f][c]) {
                barcosOcultos[f][c] = true;
                colocados++;
                System.out.println("✅ Barco colocado.");
            } else {
                System.out.println("⚠️ Coordenada inválida o ya ocupada. Intenta de nuevo.");
            }
        }

        // "Limpiar" pantalla de consola simulada con saltos de línea
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    @Override
    protected void jugarTurno(Scanner sc, Jugador jugadorActual) {
        int idJugador = (jugadorActual == j1) ? 0 : 1;

        System.out.println("-------------------------------------------------");
        System.out.println("Turno de " + jugadorActual.getNombre() + " | Puntos: " + puntajes[idJugador]);
        imprimirTablero();

        boolean disparoValido = false;

        while (!disparoValido) {
            System.out.print("Ingresa FILA a atacar (0-4): ");
            int fila = leerNumero(sc);
            System.out.print("Ingresa COLUMNA a atacar (0-4): ");
            int col = leerNumero(sc);

            if (fila >= 0 && fila < 5 && col >= 0 && col < 5) {
                if (tablero[fila][col] == '~') {
                    disparoValido = true;
                    if (barcosOcultos[fila][col]) {
                        System.out.println("\n💥 ¡KABOOM! Impacto directo a un barco.");
                        tablero[fila][col] = 'X';
                        puntajes[idJugador]++;
                        barcosHundidos++;
                    } else {
                        System.out.println("\n🌊 ¡Splash! Cayó al agua.");
                        tablero[fila][col] = 'O';
                    }
                } else {
                    System.out.println("⚠️ Ya atacaste esta casilla anteriormente. Elige otra.");
                }
            } else {
                System.out.println("⚠️ Coordenadas fuera de rango. Deben ser de 0 a 4.");
            }
        }
        imprimirTablero();
    }

    @Override
    protected boolean verificarFinJuego() {
        if (barcosHundidos == TOTAL_BARCOS) {
            if (puntajes[0] > puntajes[1]) {
                ganador = j1;
            } else if (puntajes[1] > puntajes[0]) {
                ganador = j2;
            } else {
                declararEmpate();
            }
            return true;
        }
        return false;
    }

    private void imprimirTablero() {
        System.out.println("\n  0 1 2 3 4  (Cols)");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void imprimirTableroPrivado() {
        System.out.println("\n  0 1 2 3 4  (Cols)");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 5; j++) {
                char vis = barcosOcultos[i][j] ? 'B' : '~';
                System.out.print(vis + " ");
            }
            System.out.println();
        }
    }

    private int leerNumero(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (Exception e) {
            sc.nextLine();
            return -1;
        }
    }
    @Override
    protected PuntuacionComparable calcularPuntuacionFinal() {
        // Asumiendo que sabes cuántos tiros falló el ganador
        int fallos = (ganador == j1) ? tirosAlAguaJ1 : tirosAlAguaJ2;
        return new PuntuacionBatalla(4, fallos);
    }
}