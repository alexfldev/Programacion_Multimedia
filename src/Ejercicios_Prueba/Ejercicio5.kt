package Ejercicios_Prueba

fun main() {
    println("--- REGISTRO DE USUARIO ---")

    // Llamamos a funciones separadas para validar cada dato
    // Esto hace el código más limpio y organizado
    val nombre = pedirNombre()
    val edad = pedirEdad()
    val email = pedirEmail()

    println("\n--- REGISTRO COMPLETADO ---")
    println("Usuario: $nombre")
    println("Edad: $edad años")
    println("Contacto: $email")
}

// FUNCION 1: Validar que no esté vacío
fun pedirNombre(): String {
    while (true) {
        print("Ingresa tu nombre: ")
        val input = readln().trim() // .trim() quita espacios al inicio y final

        if (input.isNotEmpty()) {
            return input // Si es válido, devolvemos el valor y salimos de la función
        }

        println("Error: El nombre no puede estar vacío.")
    }
}

// FUNCION 2: Validar números y capturar errores (Try-Catch)
fun pedirEdad(): Int {
    while (true) {
        print("Ingresa tu edad: ")
        try {
            val input = readln()
            val numero = input.toInt() // Esto lanza un error si escriben "veinte"

            // Validación lógica (rango de edad)
            if (numero in 18..100) {
                return numero
            } else {
                println("Error: Debes tener entre 18 y 100 años.")
            }

        } catch (e: NumberFormatException) {
            // Aquí caemos si el usuario escribe texto en lugar de números
            println("Error: Eso no es un número válido. Intenta de nuevo.")
        }
    }
}

// FUNCION 3: Validación de formato simple
fun pedirEmail(): String {
    while (true) {
        print("Ingresa tu correo electrónico: ")
        val input = readln().trim()

        // Verificamos que tenga '@' y un punto '.'
        if (input.contains("@") && input.contains(".")) {
            return input
        }

        println("Error: El formato del correo no parece válido (falta '@' o '.').")
    }
}