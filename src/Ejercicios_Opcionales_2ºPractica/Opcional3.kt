package Ejercicios_Opcionales_2ºPractica
// Usamos un data class para guardar cada medición
data class RegistroIMC(
    val peso: Double,
    val altura: Double,
    val imc: Double,
    val categoria: String
)

fun main() {
    // Lista para guardar el historial de mediciones
    val historial = mutableListOf<RegistroIMC>()
    var salir = false

    println("===  CALCULADORA DE IMC CON HISTORIAL  ===")

    while (!salir) {
        println("\n--- MENÚ ---")
        println("1. Nueva medición")
        println("2. Ver historial completo")
        println("3. Salir")
        print("Elige una opción: ")

        val opcion = readln().toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("\n--- Nueva Medición ---")

                // Validación de entrada: Peso positivo
                print("Introduce tu peso (kg): ")
                val peso = readln().toDoubleOrNull()
                if (peso == null || peso <= 0) {
                    println(" Error: El peso debe ser un número positivo.")
                    continue // Vuelve al inicio del while
                }

                // Validación de entrada: Altura positiva
                print("Introduce tu altura (metros, ej: 1.75): ")
                val altura = readln().toDoubleOrNull()
                if (altura == null || altura <= 0) {
                    println(" Error: La altura debe ser un número positivo.")
                    continue
                }

                // 1. Calcular IMC: peso / altura²
                val imc = peso / (altura * altura)

                // 2. Clasificar según la OMS (uso de rangos en when)
                val categoria = when {
                    imc < 18.5 -> "Bajo peso"
                    imc < 25.0 -> "Peso normal" // 18.5 a 24.9
                    imc < 30.0 -> "Sobrepeso"   // 25.0 a 29.9
                    else -> "Obesidad"          // >= 30
                }

                // 3. Mostrar tendencias (Comparar con el último registro)
                // Usamos historial.last() para obtener el último elemento añadido
                if (historial.isNotEmpty()) {
                    val anterior = historial.last()
                    val diferenciaPeso = peso - anterior.peso

                    print("Resultado: IMC %.2f ($categoria)".format(imc))

                    // Lógica para mostrar si subió o bajó
                    when {
                        diferenciaPeso > 0 -> println(" |  Has subido %.1f kg".format(diferenciaPeso))
                        diferenciaPeso < 0 -> println(" |  Has bajado %.1f kg".format(Math.abs(diferenciaPeso)))
                        else -> println(" | = Mantienes el mismo peso")
                    }
                } else {
                    // Si es el primero, no hay con qué comparar
                    println("Resultado: IMC %.2f ($categoria)".format(imc))
                }

                // 4. Guardar en historial
                historial.add(RegistroIMC(peso, altura, imc, categoria))
            }

            2 -> {
                if (historial.isEmpty()) {
                    println("El historial está vacío.")
                } else {
                    println("\n--- Historial de Mediciones ---")
                    // mapIndexed nos da el índice (i) y el objeto (reg)
                    historial.forEachIndexed { i, reg ->
                        println("${i + 1}. Peso: ${reg.peso}kg | IMC: %.2f | Estado: ${reg.categoria}".format(reg.imc))
                    }
                }
            }

            3 -> {
                salir = true
                println("¡Cuídate! ")
            }
            else -> println("Opción no válida.")
        }
    }
}
