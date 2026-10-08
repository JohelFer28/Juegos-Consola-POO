package games.tictactoe;

import games.Juego;
import games.Jugador;
import java.util.Scanner;

public class TresEnRaya extends Juego {
    private char[][] tablero;

    public TresEnRaya() {
        super("Tres en Raya");
        tablero = new char[3][3];
    }

    @Override
    protected void iniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
        imprimirTablero();
    }

    @Override
    protected void jugarTurno(Scanner sc, Jugador jugadorActual) {
        boolean movimientoValido = false;
        char simbolo = (jugadorActual == j1) ? 'X' : 'O';

        while (!movimientoValido) {
            System.out.print(jugadorActual.getNombre() + " (" + simbolo + "), ingresa fila y columna (0-2) separados por espacio: ");
            try {
                int fila = sc.nextInt();
                int col = sc.nextInt();

                if (fila >= 0 && fila < 3 && col >= 0 && col < 3 && tablero[fila][col] == '-') {
                    tablero[fila][col] = simbolo;
                    movimientoValido = true;
                } else {
                    System.out.println("Movimiento inválido. Casilla ocupada o fuera de rango.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingresa números enteros.");
                sc.nextLine();
            }
        }
        imprimirTablero();
    }

    @Override
    protected boolean verificarFinJuego() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != '-' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) return true;
            if (tablero[0][i] != '-' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) return true;
        }
        if (tablero[0][0] != '-' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) return true;
        if (tablero[0][2] != '-' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) return true;

        if (movimientosTotales == 9) {
            declararEmpate();
            return true;
        }
        return false;
    }

    @Override
    protected PuntuacionComparable calcularPuntuacionFinal() {
        return new PuntuacionTresEnRaya(ganador.getMovimientos());
    }

    private void imprimirTablero() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}