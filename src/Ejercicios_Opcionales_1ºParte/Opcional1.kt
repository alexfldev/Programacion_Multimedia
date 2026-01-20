package EjercicioOpcional1

fun main() {
    // Variable para guardar el mejor récord (mínimos intentos).
    // Lo iniciamos con el valor máximo posible de un entero.
    // Así, la primera vez que ganemos, seguro que tendremos menos intentos que esto.
    var record = Int.MAX_VALUE

    var seguirJugando = true

    println("=== 🎲 JUEGO DE ADIVINANZA 🎲 ===")

    // Bucle principal del juego (para poder jugar varias partidas)
    while (seguirJugando) {

        // 1. Generar número aleatorio (Rango del 1 al 100)
        val numeroSecreto = (1..100).random()
        var intentos = 0
        var adivinado = false

        println("\nHe pensado un número del 1 al 100. ¡Intenta adivinarlo!")
        if (record != Int.MAX_VALUE) {
            println("🏆 Récord actual a batir: $record intentos")
        }

        // Bucle de la partida actual
        while (!adivinado) {
            print("Introduce tu número: ")

            // Leemos y convertimos a int de forma segura (si falla, devuelve null)
            val input = readln().toIntOrNull()

            if (input == null) {
                println("⚠️ Por favor, introduce un número válido.")
                continue // Vuelve al inicio del while
            }

            // Sumamos un intento
            intentos++

            // Lógica de comparación
            when {
                input < numeroSecreto -> println("🔼 Es MAYOR que $input.")
                input > numeroSecreto -> println("🔽 Es MENOR que $input.")
                else -> {
                    // Si entra aquí, es que es igual
                    adivinado = true
                    println("🎉 ¡CORRECTO! El número era $numeroSecreto.")
                    println("Has necesitado $intentos intentos.")
                }
            }
        }

        // Al terminar la partida, comprobamos si hay nuevo récord
        if (intentos < record) {
            record = intentos
            println("🌟 ¡NUEVO RÉCORD! Eres el más rápido del oeste.")
        } else {
            println("No has superado el récord de $record intentos.")
        }

        // Preguntar si quiere jugar otra vez
        print("\n¿Quieres jugar otra vez? (s/n): ")
        val respuesta = readln()

        // Si no escribe 's' o 'S', salimos del bucle principal
        if (!respuesta.equals("s", ignoreCase = true)) {
            seguirJugando = false
            println("¡Gracias por jugar! 👋")
        }
    }
}