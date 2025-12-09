package Ejercicios_Obligatorios_1ºParte

data class Libro(
    val titulo: String,
    val autor: String,
    val anio: Int,
    val disponible: Boolean
)

fun main() {
    // Creamos lista de libros (Usamos mutableListOf )
    val biblioteca = mutableListOf(
        Libro("El Quijote", "Cervantes", 1605, true),
        Libro("Cien años de soledad", "Gabriel Garcia Marquez", 1967, false),
        Libro("1984", "George Orwell", 1949, true),
        Libro("El Hobbit", "J.R.R. Tolkien", 1937, true),
        Libro("Crónica de una muerte anunciada", "Gabriel Garcia Marquez", 1981, true)
    )

    println("--- 1. Buscar por Autor (Garcia Marquez) ---")
    val librosGabo = buscarPorAutor(biblioteca, "garcia")
    // .forEach
    librosGabo.forEach { println(it) }

    println("\n--- 2. Buscar por Rango de Años (1900 - 1950) ---")
    val librosClasicos = buscarPorAnio(biblioteca, 1900, 1950)
    librosClasicos.forEach { println(it) }

    println("\n--- 3. Libros Disponibles ---")
    val disponibles = obtenerDisponibles(biblioteca)
    println("Hay ${disponibles.size} libros para llevarse ahora mismo.")

    println("\n--- 4. Estadísticas ---")
    calcularEstadisticas(biblioteca)

    //  Añadir un libro nuevo
    println("\n--- EXTRA: Añadiendo libro nuevo ---")
    biblioteca.add(Libro("Harry Potter", "J.K. Rowling", 1997, false))
    println("Total libros ahora: ${biblioteca.size}")
}



// 3. Implementa buscarPorAutor
fun buscarPorAutor(libros: List<Libro>, nombreAutor: String): List<Libro> {
    // Usamos filter. Importante: lowercase() para que no importen las mayúsculas/minúsculas
    return libros.filter { libro ->
        libro.autor.lowercase().contains(nombreAutor.lowercase())
    }
}

// 4. Implementa buscarPorAño
fun buscarPorAnio(libros: List<Libro>, min: Int, max: Int): List<Libro> {

    return libros.filter { it.anio in min..max }
}

// Implementamos librosDisponibles
fun obtenerDisponibles(libros: List<Libro>): List<Libro> {
    // Solo devolvemos los que tienen disponible == true
    return libros.filter { it.disponible }
}

// 6. Implementamos calcularEstadisticas
fun calcularEstadisticas(libros: List<Libro>) {
    // a) Total de libros
    println("Total de libros: ${libros.size}")

    //
    // Usamos ? (safe call) porque si la lista está vacía, esto devuelve null
    val masNuevo = libros.maxByOrNull { it.anio }
    println("Libro más nuevo: ${masNuevo?.titulo} (${masNuevo?.anio})")

    //
    // Esto crea un mapa donde la Clave es el Autor y el Valor es la lista de sus libros
    val librosPorAutor = libros.groupBy { it.autor }

    println("Conteo de libros por autor:")
    librosPorAutor.forEach { (autor, listaLibros) ->
        println(" - $autor: ${listaLibros.size} libros")
    }
}