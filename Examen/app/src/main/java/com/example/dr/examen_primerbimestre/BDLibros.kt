package com.example.mjg70.examen

class BDLibros {
    companion object {
        val LST_LIBRO:ArrayList<Libro> = ArrayList();
        var serial:Int= 1;

        fun agregarLibro(libro: Libro):ArrayList<Libro>{
            libro.id = serial
            serial++
            LST_LIBRO.add(libro)
            return LST_LIBRO
        }

        fun mostrarLibro(padreId:Int): List<Libro> {
            val lstFiltradaLibro = this.LST_LIBRO.filter { it.bibliotecaId ==  padreId}
            return lstFiltradaLibro
        }

        fun eliminarLibro(id:Int){
            this.LST_LIBRO.removeAll{ it.id == id }
        }

        fun actualizarLibro(libro: Libro){
            val indice = this.LST_LIBRO.indexOfFirst { it.id == libro.id }
            this.LST_LIBRO[indice] = libro
        }

    }

}