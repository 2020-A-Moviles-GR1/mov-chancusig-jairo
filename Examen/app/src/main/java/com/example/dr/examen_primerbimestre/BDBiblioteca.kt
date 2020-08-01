package com.example.mjg70.examen

class BDBiblioteca{
    companion object {
        val LST_BIBLIOTECA:ArrayList<Biblioteca> = ArrayList(); //***********
        var serial:Int= 1;
        var nombreUsuario:String = "";

        fun guardarUsuario(nombre:String){
            this.nombreUsuario = nombre;
        }

        fun agregarBiblioteca(biblioteca: Biblioteca):ArrayList<Biblioteca>{//***********
            biblioteca.id = serial //***********
            serial++
            LST_BIBLIOTECA.add(biblioteca) //***********
            return LST_BIBLIOTECA
        }

        fun mostrarBiblioteca(): List<Biblioteca> {//***********
            return this.LST_BIBLIOTECA
        }

        fun eliminarBiblioteca(id:Int){
            this.LST_BIBLIOTECA.removeAll{ it.id == id }
        }

        fun actualizarBiblioteca(biblioteca: Biblioteca){//***********
            val indice = this.LST_BIBLIOTECA.indexOfFirst { it.id == biblioteca.id }//***********
            this.LST_BIBLIOTECA[indice] = biblioteca//***********
        }

    }

}