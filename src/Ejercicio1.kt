fun main() {
    // 1. Mostrar un mensaje en pantalla pidiendo el dato
    // Usamos 'print' en lugar de 'println' para que el cursor se quede en la misma línea
    print("Por favor, introduce tu nombre: ")

    // 2. Leer la entrada del usuario desde la consola
    // 'readln()' captura el texto que escribe el usuario y lo guardamos en la variable 'nombre'
    val nombre = readln()

    // 3. Mostrar el mensaje personalizado
    // Usamos el símbolo '$' (interpolación de cadenas) para insertar la variable dentro del texto
    println("¡Hola, $nombre! Bienvenido a Kotlin.")
}