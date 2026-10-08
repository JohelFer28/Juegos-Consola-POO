package games;

/**
 * Interfaz que define el comportamiento de cualquier puntuación en el sistema.
 * Permite que puntuaciones de juegos completamente distintos se puedan comparar
 * numéricamente de forma genérica para armar un Top Global.
 */
public interface PuntuacionComparable {
    // Método solicitado para comparar esta puntuación con otra
    int comparar(PuntuacionComparable otra);

    // Método para obtener el valor numérico base
    int getValorNumerico();

    // Método para explicar cómo se obtuvieron esos puntos (opcional pero útil)
    String getDesglose();
}