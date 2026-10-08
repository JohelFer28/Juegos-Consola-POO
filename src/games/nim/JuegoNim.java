package games.nim;

import games.Juego;
import games.Jugador;
import java.util.Scanner;

public class JuegoNim extends Juego {
    private int piezasRestantes;

    public JuegoNim() {
        super("Juego de Nim");
    }

    @Override
    protected void iniciarTablero() {
        piezasRestantes = 20;
        System.out.println("Reglas: Hay 20 piezas en la mesa. En cada turno se pueden retirar 1, 2 o 3 piezas.");
        System.out.println("El jugador que tome la última pieza PIERDE la partida.");
    }

    @Override
    protected void jugarTurno(Scanner sc, Jugador jugadorActual) {
        System.out.println("\nPiezas en la mesa: " + piezasRestantes);
        boolean movimientoValido = false;

        while (!movimientoValido) {
            System.out.print(jugadorActual.getNombre() + ", ¿cuántas piezas deseas retirar? (1-3): ");
            try {
                int retiro = sc.nextInt();
                if (retiro >= 1 && retiro <= 3 && retiro <= piezasRestantes) {
                    piezasRestantes -= retiro;
                    movimientoValido = true;
                } else {
                    System.out.println("Cantidad no válida. Retira entre 1 y 3 piezas (sin exceder las restantes).");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Ingresa un número entero.");
                sc.nextLine();
            }
        }
    }

    @Override
    protected boolean verificarFinJuego() {
        return piezasRestantes == 0;
    }
    @Override
    protected PuntuacionComparable calcularPuntuacionFinal() {
        return new PuntuacionNim(ganador.getMovimientos());
    }
}