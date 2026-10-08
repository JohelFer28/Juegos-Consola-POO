import games.Jugador;
import games.batalla.JuegoBatalla; // <-- Importamos el nuevo juego
import games.nim.JuegoNim;
import games.tictactoe.TresEnRaya;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Consola consola = new Consola();

        System.out.println("=================================");
        System.out.println("🎮 ENCENDIENDO CONSOLA DE JUEGOS 🎮");
        System.out.println("=================================");

        // Instalación de juegos (se agrega la Batalla Naval)
        consola.instalarJuego(new TresEnRaya());
        consola.instalarJuego(new JuegoNim());
        consola.instalarJuego(new JuegoBatalla()); // <-- INSTALADO CON UNA SOLA LÍNEA

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Lanzar juego");
            System.out.println("2. Ver estadísticas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            String opcion = sc.next();

            switch (opcion) {
                case "1":
                    menuLanzarJuego(consola, sc);
                    break;
                case "2":
                    consola.mostrarEstadisticas();
                    break;
                case "3":
                    salir = true;
                    System.out.println("\nApagando la consola... ¡Nos vemos!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        sc.close();
    }

    private static void menuLanzarJuego(Consola consola, Scanner sc) {
        if (consola.getJuegosInstalados().isEmpty()) {
            System.out.println("No hay juegos instalados en la consola.");
            return;
        }

        System.out.println("\n--- SELECCIONAR JUEGO ---");
        for (int i = 0; i < consola.getJuegosInstalados().size(); i++) {
            System.out.println((i + 1) + ". " + consola.getJuegosInstalados().get(i).getNombreJuego());
        }
        System.out.println((consola.getJuegosInstalados().size() + 1) + ". Volver al menú principal");
        System.out.print("Elige una opción: ");

        try {
            int seleccion = sc.nextInt();
            int totalJuegos = consola.getJuegosInstalados().size();

            if (seleccion >= 1 && seleccion <= totalJuegos) {
                System.out.print("Ingresa el nombre del Jugador 1: ");
                Jugador j1 = new Jugador(sc.next());
                System.out.print("Ingresa el nombre del Jugador 2: ");
                Jugador j2 = new Jugador(sc.next());

                consola.lanzarJuego(seleccion - 1, sc, j1, j2);
            } else if (seleccion == totalJuegos + 1) {
                System.out.println("Volviendo al menú principal...");
            } else {
                System.out.println("Opción fuera de rango.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Ingresa un número entero.");
            sc.nextLine();
        }
    }
}