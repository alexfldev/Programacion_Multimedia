fun main() {
    // 1. Crear una lista mutable (modificable) para guardar texto
    // Usamos 'mutableListOf' porque la lista cambiará de tamaño (agregaremos y borraremos cosas)
    val listaTareas = mutableListOf<String>()

    var continuar = true

    // 2. Iniciar el bucle del menú principal
    // 'while' repetirá todo el código dentro de las llaves mientras 'continuar' sea true
    while (continuar) {
        println("\n--- GESTOR DE TAREAS ---")
        println("1. Agregar tarea")
        println("2. Ver todas las tareas")
        println("3. Eliminar una tarea")
        println("4. Salir")
        print("Elige una opción: ")

        val opcion = readln()

        when (opcion) {
            "1" -> {
                print("Escribe la descripción de la tarea: ")
                val nuevaTarea = readln()
                // '.add()' añade el elemento al final de la lista
                listaTareas.add(nuevaTarea)
                println("¡Tarea guardada!")
            }
            "2" -> {
                println("--- Tus Tareas ---")
                if (listaTareas.isEmpty()) {
                    println("No tienes tareas pendientes.")
                } else {
                    // Recorremos la lista con índice para mostrar el número (1, 2, 3...)
                    // 'i' es la posición (empieza en 0) y 'tarea' es el texto
                    for ((i, tarea) in listaTareas.withIndex()) {
                        println("${i + 1}. $tarea")
                    }
                }
            }
            "3" -> {
                print("Ingresa el número de la tarea a eliminar: ")
                // 'toIntOrNull()' es seguro: si el usuario escribe letras, no explota el programa
                val numero = readln().toIntOrNull()

                if (numero != null && numero > 0 && numero <= listaTareas.size) {
                    // Restamos 1 porque las listas en programación empiezan en el índice 0
                    val tareaEliminada = listaTareas.removeAt(numero - 1)
                    println("Tarea eliminada: $tareaEliminada")
                } else {
                    println("Número de tarea no válido.")
                }
            }
            "4" -> {
                println("¡Hasta luego!")
                continuar = false // Esto rompe el bucle while y termina el programa
            }
            else -> println("Opción no válida, intenta de nuevo.")
        }
    }
}