
import java.io.File
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    val bibliotecas = ArrayList<Biblioteca>()
    val libros = ArrayList<Libro>()
    readBibliotecas(bibliotecas)
    readLibros(libros)
    while (true)
        menu(bibliotecas,libros)

}
class Biblioteca(


    var nombre:String,
    var pais:String,
    var sede:String,
    var tipo:String
){
    override fun toString(): String {
        return "Biblioteca(Nombre='$nombre', Pais='$pais', Sede=$sede, Tipo=$tipo.)"
    }
}
class Libro(
    var nombre:String,
    var autor:String,
    var genero:String,
    var anio:Int,
    var precio:Float

){
    override fun toString(): String {
        return "Libro(Nombre='$nombre', Autor=$autor, Genero=$genero, Anio=$anio, Precio='$precio')"
    }
}

fun createBiblioteca(bibliotecas : ArrayList<Biblioteca>){
    print("Ingrese el nombre de la biblioteca: ")
    val nombre = readLine().toString()

    print("Ingrese el pais de la biblioteca: ")
    val pais = readLine().toString()

    print("Ingrese la sede de la biblioteca: ")
    val sede = readLine().toString()

    print("Ingrese el tipo de biblioteca(nacional|publica|infantil|escolar|universitaria): ")
    val tipo = readLine().toString()

    bibliotecas.add( Biblioteca(nombre,pais,sede,tipo))
}

fun deleteBiblioteca(bibliotecas : ArrayList<Biblioteca>, libros: ArrayList<Libro>){
    print("Ingrese el nombre de la biblioteca que desea eliminar: ")
    var nombre = readLine().toString()
    var esta=bibliotecas.removeIf { iteracion : Biblioteca ->
        iteracion.nombre==nombre
    }
    if(esta){
        borrarBibliotecaLibro(nombre,libros)
    }else{
        print("No existe la biblioteca que desea eliminar")
    }

}

fun readBibliotecas(bibliotecas : ArrayList<Biblioteca>){
    println("Presione la tecla Enter para continuar ")
    val biblioteca = readLine().toString()
    if(biblioteca==""){
    for (i in bibliotecas) {
        println(i)
    }
    }else{
        val biblioteca = bibliotecas.filter { iteracion : Biblioteca ->
            iteracion.nombre==biblioteca
        }
        for (i in biblioteca) {
            println(i)
        }
    }

}


fun updateBibliotecas(bibliotecas : ArrayList<Biblioteca>){
    print("Ingrese el nombre de la biblioteca que desea actualizar: ")
    val nombre = readLine().toString()
    val esta = bibliotecas.removeIf { iteracion : Biblioteca ->
        iteracion.nombre==nombre
    }
    if(esta){
        print("Ingrese el nombre de la biblioteca: ")
        val nombre = readLine().toString()

        print("Ingrese el pais de la biblioteca: ")
        val pais = readLine().toString()

        print("Ingrese la sede de la biblioteca: ")
        val sede = readLine().toString()

        print("Ingrese el tipo de biblioteca(nacional|publica|infantil|escolar|universitaria): ")
        val tipo = readLine().toString()

        bibliotecas.add( Biblioteca(nombre,pais,sede,tipo))
    }else{
        println("No existe la biblioteca que desea actualizar")
    }
}

fun saveBibliotecas(bibliotecas : ArrayList<Biblioteca>){
    var texto:String=""
    for (i in bibliotecas) {
        texto += i.nombre+"|"+i.pais+"|"+i.sede+"|"+i.tipo+"|"+"\n"
    }
    File("Archivos\\bibliotecas.txt").writeText(texto)
}       //bibliotecas.txt
fun readBiblioteca(bibliotecas : ArrayList<Biblioteca>){
    File("Archivos\\bibliotecas.txt").forEachLine {
        var a = it.split("|")
        bibliotecas.add(Biblioteca(a[0].toString(),a[1].toString(),a[2].toString(),a[3].toString()))
    }
}

fun saveLibros(libros : ArrayList<Libro>){
    var texto:String=""
    for (i in libros) {
        texto += i.nombre+"|"+i.autor+"|"+i.genero+"|"+i.anio+"|"+i.precio+"|"+"\n"
    }
    File("Archivos\\libros.txt").writeText(texto)
}
fun readLibro(libros : ArrayList<Libro>){
    File("Archivos\\libros.txt").forEachLine {
        var a = it.split("|")
        libros.add(Libro(a[0].toString(),a[1].toString(),a[2].toString(),a[3].toInt(),a[4].toFloat()))
    }
}

fun createLibros(bibliotecas : ArrayList<Biblioteca>, libros : ArrayList<Libro>){

        print("Ingrese el nombre del libro: ")
        val nombre = readLine().toString()

        print("Ingrese el autor del libro: ")
        val autor = readLine().toString()

        print("Ingrese el género del libro: ")
        val genero = readLine().toString()

        print("Ingrese el anio del libro: ")
        val anio = readLine().toString().toInt()

        print("Ingrese el precio del libro: ")
        val precio = readLine().toString().toFloat()

        libros.add( Libro(nombre,autor,genero,anio,precio))

}

fun readLibros(libros : ArrayList<Libro>){
    println("Presione la tecla Enter para continuar ")
    val biblioteca = readLine().toString()
    if(biblioteca==""){
        for (i in libros) {
            println(i)
        }
    }else{
        val libro = libros.filter { iteracion : Libro ->
            iteracion.autor==biblioteca
        }
        for (i in libro) {
            println(i)
        }
    }
}
fun updateLibros(bibliotecas : ArrayList<Biblioteca>, libros : ArrayList<Libro>){
    print("Ingrese el nombre del libro que desea actualizar: ")
    var nombre = readLine().toString()
    var libro=libros.removeIf { iteracion : Libro ->
        iteracion.nombre==nombre
    }
    if(libro){
        createLibros(bibliotecas,libros)
    }else{
        println("No existe el libro")
    }
}
fun deleteLibros(libros : ArrayList<Libro>){
    print("Ingrese el nombre del libro que desea eliminar: ")
    val nombre = readLine().toString()
    val consulta=libros.removeIf { iteracion : Libro ->
        iteracion.nombre==nombre
    }
    if (!consulta) println("No existe el libro que desea eliminar.")
}

fun borrarBibliotecaLibro(marca: String, libros: ArrayList<Libro>){
    libros.removeIf { iteracion : Libro ->
        iteracion.autor==marca }
}

fun menu(bibliotecas : ArrayList<Biblioteca>, libros : ArrayList<Libro>){
    var texto:String

    texto ="Menu Biblioteca\n"
    texto+="1. Ir al menu de Libros\n"
    texto+="2. Registrar una nueva Biblioteca\n"
    texto+="3. Consultar las Bibliotecas registradas\n"
    texto+="4. Actualizar una Biblioteca\n"
    texto+="5. Eliminar una Biblioteca\n"
    texto+="6. Salir\n"

    println(texto)
    var opcion:Int
    while(true){
        try {
            opcion = readLine().toString()!!.toInt()
            if(opcion>6 || opcion<1){
                print("Ingrese una opción correcta\n")
            }else{
                break
            }
        }catch (e: NumberFormatException){
            print("Ingrese una opción correcta\n")
        }
    }
    when(opcion){
        1-> {while (menuBiblioteca(bibliotecas, libros)){ }}
        2-> createBiblioteca(bibliotecas)
        3-> readBibliotecas(bibliotecas)
        4-> updateBibliotecas(bibliotecas)
        5-> deleteBiblioteca(bibliotecas,libros)
        6-> {
            saveBibliotecas(bibliotecas)
            saveLibros(libros)
            System.exit(0)
        }
    }
}
fun menuBiblioteca(bibliotecas : ArrayList<Biblioteca>, libros : ArrayList<Libro>) : Boolean{
    var menuLibro=true
    var texto:String

    texto ="Menu Libros\n"
    texto+="1. Regresar al menu Biblioteca\n"
    texto+="2. Registrar un nuevo libro\n"
    texto+="3. Consultar los libros registrados\n"
    texto+="4. Actualizar los datos de un libro\n"
    texto+="5. Eliminar un libro\n"
    texto+="6. Salir\n"

    println(texto)
    var opcion:Int
    while(true){
        try {
            opcion = readLine().toString()!!.toInt()
            if(opcion>6 || opcion<1){
                print("Ingrese una opción correcta\n")
            }else{
                break
            }
        }catch (e: NumberFormatException){
            print("Ingrese una opción correcta\n")
        }
    }
    when(opcion){
        1-> menuLibro= false
        2-> createLibros(bibliotecas,libros)
        3-> readLibros(libros)
        4-> updateLibros(bibliotecas,libros)
        5-> deleteLibros(libros)

        6-> {
            saveBibliotecas(bibliotecas)
            saveLibros(libros)
            System.exit(0)
        }
    }
    return menuLibro
    //xD
}