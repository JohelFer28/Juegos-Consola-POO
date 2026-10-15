
Documentacion Consola de Juegos



1. COMO COMPILAR Y CORRER EL PROGRAMA

Se necesita tener instalado el JDK de Java.

En IntelliJ IDEA, abre src/Main.java y ejecuta el metodo main
con el boton verde que aparece junto a el.

Para compilar y ejecutar desde PowerShell, abre una terminal
en la carpeta principal del proyecto y ejecuta:

javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out Main

El programa funciona por consola. Escribe las opciones que
aparecen en pantalla para interactuar con los juegos.

Al iniciar, el programa carga estadisticas de ejemplo para
que la opcion de ver el Top 3 funcione sin jugar partidas.

2. EXPLICACION DE LOS PILARES DE POO
------------------------------------------------------------
* ENCAPSULAMIENTO:
Basicamente puse en 'private' todos los atributos delicados
(como el nombre de los jugadores, los puntos, o el tablero).
Para que nadie pueda modificarlos desde otra clase,
solo se pueden leer o cambiar usando los metodos publicos que
yo deje a proposito.

* HERENCIA:
Hice una clase padre abstracta que se llama 'Juego'. De ahi
heredan los 3 juegos (TresEnRaya, JuegoNim y JuegoBatalla).
Asi me ahorre repetir el codigo del bucle de turnos, calcular
el ganador o el cronometro en cada juego, todo eso lo hace el
padre y los hijos solo ponen sus propias reglas.

* ABSTRACCION E INTERFACES:
Cree una interfaz que se llama 'Jugable' con un metodo start().
La Consola solo acepta e instala cosas que sean Jugables, asi
que no le importa como funciona el juego por dentro. Tambien
hice una interfaz 'PuntuacionComparable' para poder comparar los
puntos de juegos totalmente diferentes y armar el ranking global.

* POLIMORFISMO:
Esto se ve en el Main. La consola tiene una lista
generica de objetos 'Jugable', y cuando llamo al metodo start(),
el programa sabe cual start() ejecutar (si el de
batalla naval o el de nim) dependiendo del juego que elegimos.
Tambien pasa con las puntuaciones, cada juego calcula sus puntos
a su manera pero al final se pueden comparar entre si.