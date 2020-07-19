
import java.io.File
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption
import java.io.BufferedReader

class CrudLibro{



    fun creacionLibro(
            nombre:String,
            autor:String,
            genero:String,
            anio:Int,
            precio:Float,
            nombreBiblioteca: String

    ) {
        val libro1 = Libro(
                nombre,
                autor,
                genero,
                anio,
                precio,
                nombreBiblioteca)



        val outString = libro1.toString()
        val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt")
        write(archivo.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
        leerDelArchivoLibros()
    }



    fun buscarLibro(nombre: String): List<String>? {
        var vector1: List<String>
        var vectorResult: List<String> = listOf("", "", "", "", "","")
        var cont = 0

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") //reorna un ArrayList
            if (vector1[0] == nombre) {
                vectorResult = vector1
                println("Libro encontrado")
            } else {
                cont = cont + 1
            }
        }
        if (cont == lineas.size) {
            println("El libro no existe")
            vectorResult = emptyList()
        }
        return vectorResult;
    }



    fun modificarLibro(tipo: String, nombre: String) {
        var vector1: MutableList<String> = mutableListOf()
        var vectorResult: List<String> = listOf("", "", "", "", "","")
        val tipoDato = tipo.split(":")
        var cont = 0
        val indice: Int
        var lineaReEscrita: String
        var lineasNuevasUni: ArrayList<String> = arrayListOf()



        if (tipoDato[0] == "Nombre") {
            indice = 0
        } else if (tipoDato[0] == "Autor") {
            indice = 1
        } else if (tipoDato[0] == "Género") {
            indice = 2
        }else if (tipoDato[0] == "Año") {
            indice = 3
        } else if (tipoDato[0] == "Precio") {
            indice = 4
        } else if(tipoDato[0] == "Biblioteca"){
            indice = 5
        } else {
            indice = 6
        }


        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") as MutableList<String>//reorna un ArrayList
            if(vector1[0]==nombre){
                for (j: Int in vector1.indices) {
                    if (j==indice) {
                        vector1[j] = tipoDato[1]
                        if (indice == 6) {
                            vector1[j] = tipoDato[1] + "\n"
                            println("Libro encontrado")
                        }
                        println("Libro encontrado")
                    }
                }
            } else {
                cont = cont + 1
                //print(cont)
            }
            lineaReEscrita = vector1.joinToString(separator = ",")+"\n"
            lineasNuevasUni.add(lineaReEscrita)
        }
        if(cont==lineas.size){
            println("Libro no encontrado")
        }
        for (iter: Int in lineasNuevasUni.indices) {
            if(iter==0){
                File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedWriter().use { out -> out.write(lineasNuevasUni[0])}
            }else{
                val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt")
                write(archivo.toPath(), lineasNuevasUni[iter].toByteArray(), StandardOpenOption.APPEND)
            }
        }
    }



    fun eliminarLibro(nombreLibro: String){

        var vector1: MutableList<String> = mutableListOf()
        val vector2: MutableList<String> = mutableListOf("","","","","","")
        //var vectorResult: List<String> = listOf("", "", "", "", "")
        //val tipoDato = tipo.split(":")
        var cont = 0
        val indice: Int
        var lineaReEscrita: String
        var lineasNuevasUni: ArrayList<String> = arrayListOf()

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") as MutableList<String>//reorna un ArrayList
            if(vector1[0]==nombreLibro){
                vector1=vector2

            } else {
                cont = cont + 1

            }
            lineaReEscrita = vector1.joinToString(separator = ",")+"\n"

            if(lineaReEscrita!=",,,,,"+"\n"){
                lineasNuevasUni.add(lineaReEscrita)
            }

        }
        println("Libro Eliminado")
        if(cont==lineas.size){
            println("Libro no encontrado")
        }
        for (iter: Int in lineasNuevasUni.indices) {
            if(iter==0){
                File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedWriter().use { out -> out.write(lineasNuevasUni[0])}
            }else{
                val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt")
                write(archivo.toPath(), lineasNuevasUni[iter].toByteArray(), StandardOpenOption.APPEND)
            }
        }

    }

    fun eliminarLibroBiblioteca(nombBiblioteca: String){

        var vector1: MutableList<String> = mutableListOf()
        val vector2: MutableList<String> = mutableListOf("","","","","","")

        var cont = 0
        val indice: Int
        var lineaReEscrita: String
        var lineasNuevasUni: ArrayList<String> = arrayListOf()

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") as MutableList<String>//reorna un ArrayList
            if(vector1[0]==nombBiblioteca){
                vector1=vector2

            } else {
                cont = cont + 1
                //print(cont)
            }
            lineaReEscrita = vector1.joinToString(separator = ",")+"\n"
            //print(lineaReEscrita)
            if(lineaReEscrita!=",,,,,"+"\n"){
                lineasNuevasUni.add(lineaReEscrita)
            }

        }
        if(cont==lineas.size){
            println("Biblioteca eliminada")
        }
        for (iter: Int in lineasNuevasUni.indices) {
            if(iter==0){
                File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedWriter().use { out -> out.write(lineasNuevasUni[0])}
            }else{
                val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt")
                write(archivo.toPath(), lineasNuevasUni[iter].toByteArray(), StandardOpenOption.APPEND)
            }
        }
    }

    fun leerDelArchivoLibros() {

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\libros.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }
        lineas.forEach { println(" $it") }

    }



}