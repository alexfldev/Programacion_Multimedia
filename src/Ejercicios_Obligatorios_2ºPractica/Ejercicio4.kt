package Ejercicios_Obligatorios_2ºPractica
// 10. Define data class Contacto
// Usamos 'var' en esFavorito porque vamos a querer cambiar ese valor (toggle)
data class Contacto(
    val nombre: String,
    val telefono: String,
    val email: String,
    var esFavorito: Boolean = false
)

fun main() {
    // Lista mutable para guardar los contactos
    val agenda = mutableListOf<Contacto>()

    // Variables para el menú
    var salir = false

    println("=== GESTOR DE CONTACTOS ===")

    // 22. Implementa menuInteractivo
    while (!salir) {
        println("\nElige una opción:")
        println("1. Nuevo contacto")
        println("2. Buscar contacto")
        println("3. Ver favoritos")
        println("4. Ver todos (ordenados)")
        println("5. Cambiar favorito (Toggle)")
        println("6. Eliminar contacto")
        println("7. Salir")
        print("Opción: ")

        // Leemos la opción de forma segura
        val opcion = readln().toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("--- Crear Contacto ---")
                print("Nombre: ")
                val nombre = readln()
                print("Teléfono (9 dígitos): ")
                val tlf = readln()
                print("Email: ")
                val email = readln()

                // 15. Crear nuevo contacto con validación
                // Aquí llamamos a nuestra función que devuelve un Result
                val resultado = crearContacto(nombre, tlf, email)

                // onSuccess se ejecuta si todo fue bien
                resultado.onSuccess { contactoNuevo ->
                    agenda.add(contactoNuevo)
                    println("✅ Contacto guardado: ${contactoNuevo.nombre}")
                }

                // onFailure se ejecuta si hubo errores de validación
                resultado.onFailure { error ->
                    println("❌ Error: ${error.message}")
                }
            }
            2 -> {
                // 16. Implementa buscarPorNombre
                print("Introduce nombre a buscar: ")
                val busqueda = readln()
                // filter devuelve una lista con los que coincidan
                val encontrados = agenda.filter { it.nombre.contains(busqueda, ignoreCase = true) }

                if (encontrados.isEmpty()) {
                    println("No se encontraron contactos.")
                } else {
                    encontrados.forEach { mostrarContacto(it) }
                }
            }
            3 -> {
                // 17. Implementa obtenerFavoritos
                val favoritos = agenda.filter { it.esFavorito }
                if (favoritos.isEmpty()) println("No tienes favoritos.")
                else favoritos.forEach { mostrarContacto(it) }
            }
            4 -> {
                // 18. Implementa obtenerOrdenados
                if (agenda.isEmpty()) {
                    println("La agenda está vacía.")
                } else {
                    // sortedBy ordena alfabéticamente por la propiedad que le digamos
                    val ordenados = agenda.sortedBy { it.nombre }
                    ordenados.forEach { mostrarContacto(it) }
                }
            }
            5 -> {
                // 19. Implementa toggleFavorito
                print("Escribe el nombre EXACTO del contacto para cambiar favorito: ")
                val nombre = readln()

                // find devuelve el PRIMER elemento que coincida, o null si no existe
                val contacto = agenda.find { it.nombre.equals(nombre, ignoreCase = true) }

                // Usamos el operador seguro ?. let para trabajar solo si existe
                contacto?.let {
                    it.esFavorito = !it.esFavorito // Invertimos el valor (true -> false, false -> true)
                    val estado = if (it.esFavorito) "Favorito ⭐" else "Normal"
                    println("✅ Actualizado: ${it.nombre} ahora es $estado")
                } ?: println("❌ No se encontró el contacto.")
            }
            6 -> {
                // 20. Implementa eliminarContacto
                print("Escribe el nombre del contacto a eliminar: ")
                val nombre = readln()

                // removeIf devuelve true si eliminó algo
                val eliminado = agenda.removeIf { it.nombre.equals(nombre, ignoreCase = true) }

                if (eliminado) println("🗑️ Contacto eliminado correctamente.")
                else println("❌ No se encontró a nadie con ese nombre.")
            }
            7 -> {
                salir = true
                println("¡Hasta luego!")
            }
            else -> println("Opción no válida.")
        }
    }
}

// --- Funciones de Validación y Creación ---

// 12, 13, 14, 15. Función maestra para validar y crear
// Devuelve un Result<Contacto>: o tiene un Contacto (éxito) o tiene una Excepción (fallo)
fun crearContacto(nombre: String, telefono: String, email: String): Result<Contacto> {

    // Validar nombre (no vacío)
    if (nombre.isBlank()) {
        return Result.failure(Exception("El nombre no puede estar vacío."))
    }

    // Validar teléfono (solo números y longitud 9, ejemplo sencillo para España)
    // .all { it.isDigit() } revisa carácter a carácter si es un número
    if (telefono.length != 9 || !telefono.all { it.isDigit() }) {
        return Result.failure(Exception("El teléfono debe tener 9 dígitos numéricos."))
    }

    // Validar email (debe tener @ y .)
    if (!email.contains("@") || !email.contains(".")) {
        return Result.failure(Exception("El formato del email es incorrecto."))
    }

    // Si llegamos aquí, todo está bien
    return Result.success(Contacto(nombre, telefono, email))
}

// 21. Implementa mostrarContacto (Formato bonito)
fun mostrarContacto(c: Contacto) {
    val estrella = if (c.esFavorito) "⭐" else ""
    println("[$estrella] ${c.nombre} | Tlf: ${c.telefono} | Email: ${c.email}")
}