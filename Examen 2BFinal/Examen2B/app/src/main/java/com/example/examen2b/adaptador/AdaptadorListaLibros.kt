package com.example.examen2b.adaptador

import com.example.examen2b.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.examen2b.actividades.ListaLibros
import com.example.examen2b.modelo.Libro


class AdaptadorListaLibros(
    private val listaLibros: ArrayList<Libro>,
    private val contexto: ListaLibros,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorListaLibros.MyViewHolder>() {

    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var idBibliotecaTextView: TextView
        var idTextView: TextView
        var nombreTextView: TextView
        var autorTextView: TextView
        var precioTextView: TextView
        var numPaginasTextView: TextView
        var generoTextView: TextView
        var fechaPublicacionTextView: TextView
        var eliminarBoton: Button
        var actualizarBoton: Button

        init {

            idBibliotecaTextView = view.findViewById(R.id.txv_id_bli_lib) as TextView
            idTextView = view.findViewById(R.id.txv_id_lib) as TextView
            nombreTextView = view.findViewById(R.id.txv_nombre_lib) as TextView
            autorTextView = view.findViewById(R.id.txv_autor_lib) as TextView
            precioTextView = view.findViewById(R.id.txv_precio_lib) as TextView
            numPaginasTextView = view.findViewById(R.id.txv_numero_paginas_ib) as TextView
            generoTextView = view.findViewById(R.id.txv_genero_lib) as TextView
            fechaPublicacionTextView = view.findViewById(R.id.txv_fecha_publicacion_lib) as TextView
            eliminarBoton = view.findViewById(R.id.btn_eliminar_lib) as Button
            actualizarBoton = view.findViewById(R.id.btn_actualizar_lib) as Button

            actualizarBoton.setOnClickListener {
                val libro = Libro(
                    idTextView.text.toString().toInt(),
                    precioTextView.text.toString().toDouble(),
                    nombreTextView.text.toString(),
                    autorTextView.text.toString(),
                    generoTextView.text.toString(),
                    fechaPublicacionTextView.text.toString(),
                    numPaginasTextView.text.toString().toInt(),
                    idBibliotecaTextView.text.toString().toInt(),
                    "0", "0"
                )
                contexto.irActualizarLibro(libro)

            }

            eliminarBoton.setOnClickListener {
                contexto.eliminarLibro(idTextView.text.toString().toInt())

            }
        }

    }

    //Esta función define el template que vamos a utilizar.
    // El template esta en la carpeta de recursos res/layout -> layout
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorListaLibros.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_libro,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    //Devuelve el número de items de la lista
    override fun getItemCount(): Int {
        return listaLibros.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorListaLibros.MyViewHolder, position: Int) {
        val libro: Libro = listaLibros[position]
        myViewHolder.idTextView.text = libro.id.toString()
        myViewHolder.idBibliotecaTextView.text = libro.idBiblioteca.toString()
        myViewHolder.nombreTextView.text = libro.nombre
        myViewHolder.autorTextView.text = libro.autor
        myViewHolder.precioTextView.text = libro.precio.toString()
        myViewHolder.fechaPublicacionTextView.text = libro.fechaPublicacion
        myViewHolder.numPaginasTextView.text = libro.numPaginas.toString()
        myViewHolder.generoTextView.text = libro.genero
    }

//    fun crearCliente(id: Int, nombre: String, apellido: String, cedula: String): Cliente {
//        val cliente = Cliente(
//            null,
//            null,
//            null,
//            id,
//            nombre,
//            apellido,
//            cedula
//        )
//        return cliente
//    }
}