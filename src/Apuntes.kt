// Constantes
const val PI = 3.14
const val LIMITE_EDAD = 18

fun main() {

    // --- VARIABLES INMUTABLES (val) ---
    val nombre1: String = "María"
    val edad1: Int = 25
    val altura1: Double = 1.75

    // --- INFERENCIA DE TIPOS ---
    val ciudad = "Madrid"     // Kotlin deduce String
    val contador = 42         // Kotlin deduce Int

    // --- VARIABLES MUTABLES (var) ---
    var nombre2: String = "María"
    var edad2: Int = 25
    var altura2: Double = 1.75

    // Reasignamos valores
    nombre2 = "María López"
    edad2 = 24
    altura2 = 1.80

    // --- CONVERSIONES DE TIPOS ---
    val textoNumero = "123"
    val numeroEntero = textoNumero.toInt()       // String -> Int
    val doubleDeEdad = edad2.toDouble()          // Int -> Double
    val enteroDesdeDouble = altura2.toInt()      // Double -> Int

    // --- CONDICIONALES ---
    val edad = 17

    if (edad <= LIMITE_EDAD) {
        println("Eres menor de edad")
    } else {
        println("Eres mayor de edad")
    }

    // --- IMPRIMIR TODO ---
    println("------- RESULTADOS -------")
    println("Nombre1 = $nombre1")
    println("Edad1 = $edad1")
    println("Altura1 = $altura1")
    println("Ciudad = $ciudad")
    println("Contador = $contador")

    println("Nombre2 actualizado = $nombre2")
    println("Edad2 actualizada = $edad2")
    println("Altura2 actualizada = $altura2")

    println("textoNumero convertido a entero = $numeroEntero")
    println("edad2 convertida a double = $doubleDeEdad")
    println("altura2 convertida a entero = $enteroDesdeDouble")
}
