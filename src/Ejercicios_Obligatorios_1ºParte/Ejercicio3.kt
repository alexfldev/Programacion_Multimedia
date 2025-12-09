import java.util.Locale

// 1. Data class para resultados
data class EstadisticasTexto(
    val totalCaracteres: Int,
    val totalPalabras: Int,
    val totalLineas: Int,
    val palabraMasFrecuente: String,
    val longitudPromedio: Double
)

fun main() {
    val textoEjemplo = """
        Kotlin es un lenguaje moderno, conciso y seguro.
        Kotlin es interoperable con Java.
        ¡Aprender Kotlin es divertido y útil para Android!
    """.trimIndent()

    println("--- Analizando Texto ---")
    val resultados = analizarTexto(textoEjemplo)

    // Imprimir resultados de forma legible
    with(resultados) {
        println("Total de líneas: $totalLineas")
        println("Total de palabras: $totalPalabras")
        println("Total de caracteres (sin espacios): $totalCaracteres")
        println("Palabra más frecuente: '$palabraMasFrecuente'")
        println("Longitud promedio de palabra: %.2f letras".format(longitudPromedio))
    }

    // Desafío Extra: Buscar palabras que empiezan con 'A'
    println("\n--- Desafío Extra: Patrón (Empiezan con 'A') ---")
    val palabrasConA = buscarPalabrasConPatron(textoEjemplo, "^a.*")
    println("Palabras encontradas: $palabrasConA")
}

// 8. Función principal que combina todo
fun analizarTexto(texto: String): EstadisticasTexto {
    // Pre-procesamiento
    val textoNormalizado = normalizarTexto(texto)
    val listaPalabras = obtenerListaPalabras(textoNormalizado)

    return EstadisticasTexto(
        totalCaracteres = contarCaracteres(texto),
        totalPalabras = contarPalabras(listaPalabras),
        totalLineas = contarLineas(texto),
        palabraMasFrecuente = encontrarPalabraMasFrecuente(listaPalabras),
        longitudPromedio = longitudPromedioPalabras(listaPalabras)
    )
}

// 2. Normalizar texto (Minúsculas + remover puntuación)
fun normalizarTexto(texto: String): String {
    // Regex: Reemplaza todo lo que NO sea letra, número o espacio en blanco
    val regex = Regex("[^a-z0-9\\s]")
    return texto.lowercase(Locale.getDefault()).replace(regex, "")
}

// Helper: Convierte string normalizado en lista limpia
fun obtenerListaPalabras(textoNormalizado: String): List<String> {
    // Split por espacio y filter para evitar strings vacíos (Error común)
    return textoNormalizado.split("\\s+".toRegex()).filter { it.isNotEmpty() }
}

// 3. Contar caracteres (Sin espacios en blanco)
fun contarCaracteres(texto: String): Int {
    return texto.replace("\\s".toRegex(), "").length
}

// 4. Contar palabras (Usando la lista ya procesada)
fun contarPalabras(palabras: List<String>): Int {
    return palabras.size
}

// 5. Contar líneas
fun contarLineas(texto: String): Int {
    if (texto.isEmpty()) return 0
    return texto.split("\n").size
}

// 6. Encontrar palabra más frecuente
fun encontrarPalabraMasFrecuente(palabras: List<String>): String {
    return palabras
        .groupBy { it }             // Agrupa: { "kotlin"=[kotlin, kotlin, kotlin], "es"=[es, es]... }
        .maxByOrNull { it.value.size } // Busca la entrada con la lista más larga
        ?.key                       // Retorna la palabra (key)
        ?: "N/A"
}

// 7. Longitud promedio
fun longitudPromedioPalabras(palabras: List<String>): Double {
    if (palabras.isEmpty()) return 0.0
    return palabras
        .map { it.length }          // Transforma lista de palabras a lista de enteros
        .average()                  // Calcula promedio
}

// --- Desafío Extra ---
fun buscarPalabrasConPatron(texto: String, patronRegex: String): List<String> {
    val palabras = obtenerListaPalabras(normalizarTexto(texto))
    val regex = Regex(patronRegex)
    return palabras.filter { regex.matches(it) }
}