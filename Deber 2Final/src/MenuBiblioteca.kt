//MenuBiblioteca
class MenuBiblioteca {


    fun menuBiblioteca(){
        println("--------Menu Biblioteca---------")
        println("- 1. Registrar nueva Biblioteca")
        println("- 2. Buscar Biblioteca")
        println("- 3. Modificar Biblioteca")
        println("- 4. Eliminar Biblioteca")
        println("- 5. Mostrar Bibliotecas")
        println("- 6. Salir")
    }

    fun opcionesMenu() {

        var opcion: Int
        var salir = false
        while (!salir) {
            menuBiblioteca()
            println("Seleccione una opcion: ")
            opcion = readLine()?.toInt() as Int

            when (opcion) {
                1 -> creacionBiblioteca()
                2 -> buscarBiblioteca()
                3 -> modificarBiblioteca()
                4 -> eliminarBiblioteca()
                5 -> mostrarBiblioteca()
                6 -> salir = true
                else -> {
                    print("Opción inválida")
                }
            }
        }



    }

    fun creacionBiblioteca(){
        var nombreBiblioteca: String
        var pais: String
        var sede: String
        var tipo: String
        var anioFundacion:Int

        println("Crea nueva biblioteca")
        println("Ingresa el nombre de la biblioteca: ")
        nombreBiblioteca = readLine()?.toString() as String

        println("Ingresa el pais: ")
        pais = readLine()?.toString() as String

        println("Ingresa la sede: ")
        sede = readLine()?.toString() as String

        println("Ingresa el tipo: ")
        tipo = readLine()?.toString() as String

        println("Ingrese el año de fundación: ")
        anioFundacion = readLine()?.toInt() as Int

        val controlU=CrudBblioteca()
        controlU.creacionBibliotecas(nombreBiblioteca,pais,sede,tipo,anioFundacion)

    }

    fun buscarBiblioteca(){
        var nombreBiblioteca: String
        println("Busca la biblioteca")
        println("Ingresa el nombre de la biblioteca: ")
        nombreBiblioteca = readLine()?.toString() as String
        val controlU=CrudBblioteca()
        val uni=controlU.buscarBiblioteca(nombreBiblioteca)
        println(uni)
    }

    fun modificarBiblioteca(){
        var nombreBiblioteca: String
        var actualizar: String
        println("Actualizar Biblioteca")
        println("Para actualizar especifique el campo de la siguiente manera\n" +
                "Nombre:\n"+
                "Pais:\n"+
                "Sede:\n"+
                "Tipo:\n"+
                "Año:")



        println("Ingresa la actualización: ")
        actualizar = readLine()?.toString() as String


        println("Ingresa el nombre de la Biblioteca que deseas actualizar: ")
        nombreBiblioteca = readLine()?.toString() as String
        val controlU=CrudBblioteca()
        controlU.modificarBiblioteca(actualizar,nombreBiblioteca)
    }


    fun eliminarBiblioteca(){
        var nombreBiblioteca: String
        println("Eliminar Biblioteca")
        println("Ingresa el nombre de la biblioteca que deseas eliminar: ")
        nombreBiblioteca = readLine()?.toString() as String
        val controlU=CrudBblioteca()
        controlU.eliminarBiblioteca(nombreBiblioteca)
    }

    fun mostrarBiblioteca(){
        val controlU=CrudBblioteca()
        controlU.leerBiblioteca()
    }
}