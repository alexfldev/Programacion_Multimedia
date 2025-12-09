package Ejercicios_Obligatorios_1ºParte
import kotlin.math.roundToInt

// 1. Definimos una excepción personalizada
// Heredamos de Exception para poder lanzarla cuando queramos
class TemperaturaImposibleException(mensaje: String) : Exception(mensaje)

// 2. Data class para devolver el resultado de forma elegante (Opcional, pero buena práctica)
data class ResultadoConversion(
    val valorOriginal: Double,
    val valorConvertido: Double,
    val unidadDestino: String
)

fun main() {
    menuInteractivo()
}

// 7. Implementa menuInteractivo
fun menuInteractivo() {
    var continuar = true

    while (continuar) {
        println("\n--- CONVERSOR DE TEMPERATURAS ---")
        println("1. Celsius a Fahrenheit")
        println("2. Kelvin a Celsius")
        println("3. Salir")
        print("Elige una opción: ")

        // Leemos la opción de forma segura
        val opcion = readlnOrNull()?.toIntOrNull()

        when (opcion) {
            1 -> {
                print("Introduce grados Celsius: ")
                val input = readlnOrNull()?.toDoubleOrNull()
                // Llamamos a convertir pasándole la lógica específica de C -> F
                procesarEntrada(input, "Celsius", "Fahrenheit") { c -> celsiusAFahrenheit(c) }
            }
            2 -> {
                print("Introduce grados Kelvin: ")
                val input = readlnOrNull()?.toDoubleOrNull()
                // Llamamos a convertir pasándole la lógica específica de K -> C
                procesarEntrada(input, "Kelvin", "Celsius") { k -> kelvinACelsius(k) }
            }
            3 -> {
                println("¡Hasta luego!")
                continuar = false
            }
            else -> println("Opción no válida. Intenta de nuevo.")
        }
    }
}

// Función auxiliar para manejar la entrada y el Result
// Recibe el input y una función "logicaConversion" (lambda) que hace el cálculo
fun procesarEntrada(input: Double?, origen: String, destino: String, logicaConversion: (Double) -> Double) {
    if (input == null) {
        println("Error: Debes introducir un número válido.")
        return
    }

    // 6. Implementa convertir (Usa Result)
    val resultado: Result<ResultadoConversion> = runCatching {
        // Pasos dentro del bloque "seguro":
        // 1. Validamos (si falla, lanza excepción y va directo a onFailure)
        validarTemperatura(input, origen)

        // 2. Convertimos
        val valorFinal = logicaConversion(input)

        // 3. Devolvemos el objeto datos
        ResultadoConversion(input, valorFinal, destino)
    }

    // Manejamos el éxito o el fracaso limpiamente
    resultado.onSuccess { res ->
        // %.2f formatea a 2 decimales
        println("✅ Éxito: ${res.valorOriginal} $origen son %.2f ${res.unidadDestino}".format(res.valorConvertido))
    }.onFailure { error ->
        println("❌ Error: ${error.message}")
    }
}



// 3. Implementa celsiusAFahrenheit
fun celsiusAFahrenheit(celsius: Double): Double {
    // IMPORTANTE: Usar 9.0 / 5.0. Si pones 9 / 5, Kotlin hace división entera y da 1.
    return (celsius * 9.0 / 5.0) + 32
}

// 4. Implementa kelvinACelsius
fun kelvinACelsius(kelvin: Double): Double {
    return kelvin - 273.15
}

// 5. Implementa validarTemperatura
fun validarTemperatura(valor: Double, escala: String) {
    // El cero absoluto es -273.15°C o 0K
    val esInvalido = when (escala) {
        "Celsius" -> valor < -273.15
        "Kelvin" -> valor < 0.0
        else -> false
    }

    if (esInvalido) {
        throw TemperaturaImposibleException("La temperatura $valor en $escala está por debajo del cero absoluto.")
    }
}
