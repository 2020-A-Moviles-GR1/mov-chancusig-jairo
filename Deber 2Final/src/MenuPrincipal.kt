//MenuUniversal
class MenuPrincipal{

    fun Encabezado(){
        println("*********MENU PRINCIPAL**********")
        println("1. Ingreso al menu Biblioteca")
        println("2. Ingreso al menu Libro")
        println("3. Salir")
    }

    fun opcionesMenuPrincipal() {

        var opcion: Int
        var salir = false
        while (!salir) {
            Encabezado()
            println("Selecciona una opcion: ")
            opcion = readLine()?.toInt() as Int

            when (opcion) {
                1 -> llamarMenuBiblioteca()
                2 -> llamarMenuLibro()
                3 -> salir = true
                else -> {
                    print("Elije una opción válida!")
                }
            }
        }

    }


    fun llamarMenuBiblioteca(){

        val menu1=MenuBiblioteca()
        menu1.opcionesMenu()

    }

    fun llamarMenuLibro(){

        val menu1=MenuLibro()
        menu1.opcionesMenu()

    }





}
