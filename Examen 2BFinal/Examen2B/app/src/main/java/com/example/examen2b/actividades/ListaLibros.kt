package com.example.examen2b.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.examen2b.R
import com.example.examen2b.adaptador.AdaptadorListaLibros
import com.example.examen2b.modelo.Libro
import com.example.examen2b.valoresEstaticos.Datos
import com.example.examen2b.valoresEstaticos.Servidor
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_libros.*

class ListaLibros : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_libros)
        val idBiblioteca = this.intent.getIntExtra("idBiblioteca", -1)
        iniciarRecyclerView(Datos.listaLibro, this, rv_libros)

    }

    fun iniciarRecyclerView(
        listaLibros: ArrayList<Libro>,
        actividad: ListaLibros,
        recyclerView: androidx.recyclerview.widget.RecyclerView
    ) {
        val adaptadorLibro = AdaptadorListaLibros(listaLibros, actividad, recyclerView)
        rv_libros.adapter = adaptadorLibro
        rv_libros.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_libros.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptadorLibro.notifyDataSetChanged()
    }

    fun eliminarLibro(idLibro: Int) {
        val url = Servidor.url("libro") + "?id=${idLibro}"
        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        Datos.listaLibroCompleta = arrayListOf()
                        runOnUiThread {
                            irListaBibliotecas()
                        }
                    }
                }
            }
    }

    fun irListaBibliotecas() {
        val intent = Intent(
            this,
            ListaBibliotecas::class.java
        )
        startActivity(intent)
    }


    fun irActualizarLibro(libro: Libro) {
        val intent = Intent(
            this,
            ActualizarLibro::class.java
        )
        intent.putExtra("id", libro.id)
        intent.putExtra("genero", "${libro.genero}")
        intent.putExtra("precio", libro.precio)
        intent.putExtra("autor", "${libro.autor}")
        intent.putExtra("nombre", "${libro.nombre}")
        intent.putExtra("fechaPublicacion", "${libro.fechaPublicacion}")
        intent.putExtra("numPaginas", libro.numPaginas)
        startActivity(intent)
    }

}
