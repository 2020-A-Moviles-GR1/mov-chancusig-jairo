package com.example.examen2b.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.examen2b.R
import com.example.examen2b.adaptador.AdaptadorListaBibliotecas
import com.example.examen2b.modelo.*
import com.example.examen2b.valoresEstaticos.Datos
import com.example.examen2b.valoresEstaticos.Servidor
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_lista_bibliotecas.*

class ListaBibliotecas : AppCompatActivity() {
    private var listaBibliotecas: ArrayList<Biblioteca> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        obtenerBibliotecas()
        setContentView(R.layout.activity_lista_bibliotecas)
    }

    fun iniciarRecyclerView(
        listaBibliotecas: ArrayList<Biblioteca>,
        actividad: ListaBibliotecas,
        recyclerView: androidx.recyclerview.widget.RecyclerView
    ) {
        val adaptadorCliente = AdaptadorListaBibliotecas(listaBibliotecas, actividad, recyclerView)
        rv_bibliotecas.adapter = adaptadorCliente
        rv_bibliotecas.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_bibliotecas.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptadorCliente.notifyDataSetChanged()
    }

    private fun obtenerBibliotecas() {

        val url = Servidor.url("biblioteca")
        Log.i("http", url)
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        listaBibliotecas = Klaxon()
                            .parseArray<Biblioteca>(data)!! as ArrayList<Biblioteca>
                        Datos.listaLibroCompleta = arrayListOf()
                        listaCompletaLibros(listaBibliotecas)

                        Log.i("http", "$data")
                        runOnUiThread {
                            iniciarRecyclerView(listaBibliotecas, this, rv_bibliotecas)
                        }
                    }
                }
            }
    }

    fun eliminarBiblioteca(idBiblioteca: Int) {
        val url = Servidor.url("biblioteca") + "?id=${idBiblioteca}"
        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            startActivity(this.intent)
                        }
                    }
                }
            }
    }

    fun irGestionarLibros(idBiblioteca: Int, listaLibros: ArrayList<Libro>?) {
        val intent = Intent(
            this,
            GestionLibros::class.java
        )
        Datos.listaLibro = listaLibros!!


        intent.putExtra("idBiblioteca", idBiblioteca)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun listaCompletaLibros(listaBibliotecas: ArrayList<Biblioteca>) {
        listaBibliotecas.forEach { biblioteca ->
            biblioteca.libroDeBiblioteca?.forEach { libro ->
                Datos.listaLibroCompleta.add(libro)
            }
        }
    }

    fun irActualizarBiblioteca(biblioteca: Biblioteca) {
        val intent = Intent(
            this,
            ActualizarBiblioteca::class.java
        )
        intent.putExtra("id", biblioteca.id)
        intent.putExtra("nombre", biblioteca.nombre)
        intent.putExtra("ciudad", biblioteca.ciudad)
        intent.putExtra("fechaFundacion", biblioteca.fechaFundacion)
        intent.putExtra("sedes", biblioteca.numSedes)
        intent.putExtra("publica", biblioteca.publica)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

}
