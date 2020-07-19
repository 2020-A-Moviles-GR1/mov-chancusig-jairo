//MenuLibro

class MenuLibro {

    fun menuLibro(){
        println("------Menu Libro------")
        println("- 1. Registrar Libro")
        println("- 2. Buscar Libro")
        println("- 3. Actualizar Libro")
        println("- 4. Eliminar Libro")
        println("- 5. Mostrar Libro")
        println("- 6. Salir")
    }

    fun opcionesMenu() {

        var opcion: Int
        var salir = false
        while (!salir) {
            menuLibro()
            println("Selecciona una opción: ")
            opcion = readLine()?.toInt() as Int

            when (opcion) {
                1 -> registrarLibro()
                2 -> buscarLibro()
                3 -> actualizarLibro()
                4 -> eliminarLibro()
                5 -> mostrarLibro()
                6 -> salir = true
                else -> {
                    print("La opción ingresada es inválida")
                }
            }
        }

    }

    fun registrarLibro(){

        var nombreLibro:String
        var autorLibro:String
        var generoLibro:String
        var anioLibro:Int
        var precioLibro:Float
        var nombreBiblioteca:String

        println("Registro Libro...")
        println("Ingresa el nombre del Libro: ")
        nombreLibro = readLine()?.toString() as String

        println("Ingresa el autor del Libro:")
        autorLibro = readLine()?.toString() as String

        println("Ingresa el género del libro: ")
        generoLibro = readLine()?.toString() as String

        println("Ingresa el año del libro: ")
        anioLibro = readLine()?.toInt() as Int

        println("Ingresa el precio del Libro: ")
        precioLibro = readLine()?.toFloat() as Float

        println("Ingresa la biblioteca del libro : ")
        nombreBiblioteca = readLine()?.toString() as String


        val controlLibro=CrudLibro()
        controlLibro.creacionLibro(nombreLibro,autorLibro,generoLibro,anioLibro,precioLibro,nombreBiblioteca)

    }

    fun buscarLibro(){
        var nombreLibro: String
        println("Buscar Libro...")
        println("Ingresa el nombre del libro: ")
        nombreLibro = readLine()?.toString() as String
        val controlLibro=CrudLibro()
        val lib=controlLibro.buscarLibro(nombreLibro)
        println(lib)
    }

    fun actualizarLibro(){
        var nombreLibro: String
        var actualizacion: String

        println("Actualizar Libro...")

        println("Para actualizar especifique el campo de la siguiente manera\n" +
                "Nombre:\n"+
                "Autor:\n"+
                "Género:\n"+
                "Año:\n"+
                "Precio:\n"+
                "Biblioteca:")

        println("Ingresa la actualización: ")
        actualizacion = readLine()?.toString() as String

        println("Ingresa el nombre del libro que deseas actualizar: ")
        nombreLibro = readLine()?.toString() as String
        val controlLibro=CrudLibro()
        controlLibro.modificarLibro(actualizacion,nombreLibro)
    }


    fun eliminarLibro(){
        var nombreLibro: String
        println("Eliminar Libro...")
        println("Ingresa el nombre del libro que deseas eliminar: ")
        nombreLibro = readLine()?.toString() as String
        val controlAlien=CrudLibro()
        controlAlien.eliminarLibro(nombreLibro)
    }

    fun mostrarLibro(){
        val controlLibro=CrudLibro()
        controlLibro.leerDelArchivoLibros()
    }


}
