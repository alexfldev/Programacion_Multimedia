package Ejercicios_Prueba

fun main() {
    println("--- Calculadora Básica ---")

    // 1. Pedir el primer número
    print("Ingresa el primer número: ")
    // 'toDouble()' convierte el texto que escribe el usuario en un número con decimales
    val numero1 = readln().toDouble()

    // 2. Pedir la operación aritmética
    print("Ingresa la operación (+, -, *, /): ")
    val operacion = readln()

    // 3. Pedir el segundo número
    print("Ingresa el segundo número: ")
    val numero2 = readln().toDouble()

    // 4. Calcular el resultado usando 'when'
    // 'when' es como un switch/case mejorado. Evalúa la variable 'operacion'
    val resultado = when (operacion) {
        "+" -> numero1 + numero2
        "-" -> numero1 - numero2
        "*" -> numero1 * numero2
        "/" -> {
            // Manejo simple para evitar dividir por cero
            if (numero2 != 0.0) {
                numero1 / numero2
            } else {
                "Error: No se puede dividir por cero"
            }
        }
        else -> "Operación no válida" // Si el usuario escribe algo que no sea +, -, * o /
    }

    // 5. Mostrar el resultado final
    println("El resultado es: $resultado")
}