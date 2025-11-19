fun main() {
    // 1. Creamos una lista de números (inmutable, solo lectura)
    val numeros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("Lista original: $numeros")

    // 2. Procesamiento en cadena (Pipeline)
    val resultado = numeros
        // PASO A: FILTRAR
        // 'filter' revisa cada número. Si la condición es verdadera, el número pasa.
        // 'it' se refiere al elemento actual que se está revisando.
        .filter { it % 2 == 0 } // Nos quedamos solo con los pares (resto de dividir por 2 es 0)

        // PASO B: TRANSFORMAR
        // 'map' toma cada número que pasó el filtro y lo convierte en otra cosa.
        .map { it * it } // Elevamos el número al cuadrado

    // 3. Mostrar el resultado final
    println("Pares elevados al cuadrado: $resultado")


    // --- EJEMPLO EXTRA: Caso de la vida real (Precios e Impuestos) ---
    val precios = listOf(10.0, 5.0, 20.0, 100.0, 2.5)

    // Filtramos precios mayores a 5 y les agregamos el 21% de IVA
    val preciosFinales = precios
        .filter { it > 5.0 }
        .map { precio -> precio * 1.21 } // Aquí le puse nombre 'precio' en vez de usar 'it'

    println("\nPrecios procesados con IVA: $preciosFinales")
}