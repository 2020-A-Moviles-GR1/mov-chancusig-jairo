
import java.io.File
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption
import java.io.BufferedReader

class ControlBiblioteca {



    fun creacionBibliotecas(
            nombreBiblioteca: String,
            pais: String,
            sede: String,
            tipo: String,
            anioFundacion: Int
    ) {
        val biblioteca1 = Biblioteca(
            nombreBiblioteca,
            pais,
            sede,
            tipo,
            anioFundacion
        )
        //--> append
        val outString = biblioteca1.toString()
        val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt")
        write(archivo.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
        println("Biblioteca creada")
        leerBiblioteca()
    }


    fun buscarBiblioteca(nombreBiblioteca: String): List<String>? {
        var vector1: List<String>
        var vectorResult: List<String> = listOf("", "", "", "", "")
        var cont = 0

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") //reorna un ArrayList
            if (vector1[0] == nombreBiblioteca) {
                vectorResult = vector1
                println("Biblioteca encontrada")
            } else {
                cont = cont + 1
            }
        }
        if (cont == lineas.size) {
            println("La Biblioteca no existe")
            vectorResult = emptyList()
        }
        return vectorResult;
    }



    fun modificarBiblioteca(tipo: String, nombreBibl: String) {
        var vector1: MutableList<String> = mutableListOf()
        var vectorResult: List<String> = listOf("", "", "", "", "")
        val tipoDato = tipo.split(":")
        var cont = 0
        val indice: Int
        var lineaReEscrita: String
        var lineasNuevasUni: ArrayList<String> = arrayListOf()


        if (tipoDato[0] == "Nombre de la Biblioteca: ") {
            indice = 0
        } else if (tipoDato[0] == "País de la Biblioteca: ") {
            indice = 1
        } else if (tipoDato[0] == "Sede de la Biblioteca: ") {
            indice = 2
        } else if (tipoDato[0] == "Tipo de Biblioteca: ") {
            indice = 3
        } else if (tipoDato[0] == "Año de fundacion: ") { //elsee
            indice = 4
        } else{
            indice = 5
        }

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") as MutableList<String>//reorna un ArrayList
            if(vector1[0]==nombreBibl){
                for (j: Int in vector1.indices) {
                    if (j==indice) {
                        vector1[j] = tipoDato[1]
                        if (indice == 5) {
                            vector1[j] = tipoDato[1] + "\n"
                            println("Biblioteca encontrada")
                        }
                        println("Biblioteca encontrada")
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
            println("Biblioteca no encontrada")
        }
        for (iter: Int in lineasNuevasUni.indices) {
            if(iter==0){
                File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt").bufferedWriter().use { out -> out.write(lineasNuevasUni[0])}
            }else{
                val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt")
                write(archivo.toPath(), lineasNuevasUni[iter].toByteArray(), StandardOpenOption.APPEND)
            }
        }
    }


    fun eliminarBiblioteca(nombreBibl: String){

        var vector1: MutableList<String> = mutableListOf()
        val vector2: MutableList<String> = mutableListOf("","","","","")
        var cont = 0
        val indice: Int
        var lineaReEscrita: String
        var lineasNuevasUni: ArrayList<String> = arrayListOf()


        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }

        for (i: Int in lineas.indices) {
            vector1 = lineas[i].split(",") as MutableList<String>//reorna un ArrayList
            if(vector1[0]==nombreBibl){
                vector1=vector2

            } else {
                cont = cont + 1

            }
            lineaReEscrita = vector1.joinToString(separator = ",")+"\n"

            if(lineaReEscrita!=",,,,"+"\n"){
                lineasNuevasUni.add(lineaReEscrita)
            }

        }
        if(cont==lineas.size){
            println("Biblioteca no encontrada")
        }
        for (iter: Int in lineasNuevasUni.indices) {
            if(iter==0){
                File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt").bufferedWriter().use { out -> out.write(lineasNuevasUni[0])}
            }else{
                val archivo = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt")
                write(archivo.toPath(), lineasNuevasUni[iter].toByteArray(), StandardOpenOption.APPEND)
            }
        }

        val controlLibro =ControlLibro()
        controlLibro.eliminarLibroBiblioteca(nombreBibl)
    }



    fun leerBiblioteca() {

        val bufferedReader: BufferedReader = File("C:\\Users\\Jairo Chancusig\\Documents\\GitHub\\mov-chancusig-jairo\\Deber 2Final\\archivos\\biblioteca.txt").bufferedReader()
        val lineas = mutableListOf<String>()
        bufferedReader.useLines { lines -> lines.forEach { lineas.add(it) } }
        lineas.forEach { println(" $it") }

    }

}