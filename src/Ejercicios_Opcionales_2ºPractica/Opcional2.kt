package Ejercicios_Opcionales_2ºPractica
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 1. Data class Nota con fecha automática
data class Nota(
    val titulo: String,
    var contenido: String,
    // LocalDateTime.now() coge la fecha y hora actual del sistema
    val fechaCreacion: LocalDateTime = LocalDateTime.now(),
    var esImportante: Boolean = false
)

fun main() {
    val notas = mutableListOf<Nota>()

    // Formateador para que la fecha se vea bonita (Día/Mes/Año Hora:Minuto)
    val formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    var salir = false

    println("===  BLOC DE NOTAS RÁPIDAS ===")

    while (!salir) {
        println("\n--- MENÚ ---")
        println("1. Nueva nota")
        println("2. Ver todas (ordenadas por fecha)")
        println("3. Buscar (título o contenido)")
        println("4. Marcar/Desmarcar Importante")
        println("5. Eliminar nota")
        println("6. Exportar notas a fichero (.txt)")
        println("7. Salir")
        print("Elige opción: ")

        val opcion = readln().toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                print("Título: ")
                val titulo = readln()
                print("Contenido: ")
                val contenido = readln()

                // La fecha se pone sola gracias al valor por defecto en el data class
                notas.add(Nota(titulo, contenido))
                println(" Nota guardada.")
            }
            2 -> {
                if (notas.isEmpty()) println("No hay notas.")
                else {
                    println("--- Listado por fecha (Más recientes primero) ---")
                    // sortedByDescending para ver las nuevas arriba
                    notas.sortedByDescending { it.fechaCreacion }.forEach {
                        mostrarNota(it, formateador)
                    }
                }
            }
            3 -> {
                print("Texto a buscar: ")
                val query = readln()
                // filter busca en título O (||) en contenido
                val resultados = notas.filter {
                    it.titulo.contains(query, ignoreCase = true) ||
                            it.contenido.contains(query, ignoreCase = true)
                }

                if (resultados.isEmpty()) println("No se encontraron coincidencias.")
                else resultados.forEach { mostrarNota(it, formateador) }
            }
            4 -> {
                print("Escribe el título exacto de la nota: ")
                val titulo = readln()
                val nota = notas.find { it.titulo.equals(titulo, ignoreCase = true) }

                nota?.let {
                    it.esImportante = !it.esImportante // Alternar true/false
                    val estado = if (it.esImportante) "IMPORTANTE " else "Normal"
                    println(" La nota '${it.titulo}' ahora es $estado")
                } ?: println(" Nota no encontrada.")
            }
            5 -> {
                print("Escribe el título de la nota a borrar: ")
                val titulo = readln()
                val borrado = notas.removeIf { it.titulo.equals(titulo, ignoreCase = true) }
                if (borrado) println("🗑 Nota eliminada.") else println(" No encontrada.")
            }
            6 -> {
                // EXPORTAR A FICHERO
                try {
                    // StringBuilder es eficiente para crear textos largos
                    val sb = StringBuilder()
                    sb.append("=== MIS NOTAS EXPORTADAS ===\n\n")

                    notas.forEach { nota ->
                        val importante = if (nota.esImportante) "[IMPORTANTE]" else ""
                        val fecha = nota.fechaCreacion.format(formateador)

                        sb.append("Título: ${nota.titulo} $importante\n")
                        sb.append("Fecha: $fecha\n")
                        sb.append("Contenido: ${nota.contenido}\n")
                        sb.append("----------------------------\n")
                    }

                    // Escribir en disco
                    val nombreFichero = "mis_notas.txt"
                    File(nombreFichero).writeText(sb.toString())

                    println("Exportado correctamente a '$nombreFichero'")
                    // Pista: El archivo se guarda en la carpeta raíz del proyecto

                } catch (e: Exception) {
                    println(" Error al exportar: ${e.message}")
                }
            }
            7 -> {
                salir = true
                println("¡Adios! ")
            }
            else -> println("Opción incorrecta.")
        }
    }
}

// Función auxiliar para imprimir bonito
fun mostrarNota(nota: Nota, fmt: DateTimeFormatter) {
    val icono = if (nota.esImportante) "" else ""
    val fechaBonita = nota.fechaCreacion.format(fmt)
    println("$icono [${fechaBonita}] ${nota.titulo}: ${nota.contenido}")
}
