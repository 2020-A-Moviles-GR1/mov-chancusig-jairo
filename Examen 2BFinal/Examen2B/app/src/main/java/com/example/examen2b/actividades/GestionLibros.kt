package com.example.examen2b.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2b.R
import kotlinx.android.synthetic.main.activity_gestion_libros.*

class GestionLibros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_libros)
        val idBiblioteca = this.intent.getIntExtra("idBiblioteca", -1)

        btn_gestion_lib.setOnClickListener {
            irListaLibros(idBiblioteca)
        }


        btn_crear_libro.setOnClickListener {
            irCrearLibro(idBiblioteca)
        }
        btn_mapa.setOnClickListener {
            irMapa(idBiblioteca)
        }
    }

    fun irListaLibros(idBiblioteca: Int) {
        val intent = Intent(
            this,
            ListaLibros::class.java
        )
        intent.putExtra("idBiblioteca", idBiblioteca)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irCrearLibro(idBiblioteca: Int) {
        val intent = Intent(
            this,
            CrearLibro::class.java
        )
        intent.putExtra("idBiblioteca", idBiblioteca)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun irMapa(idBiblioteca: Int) {
        val intent = Intent(
            this,
            MapsActivity::class.java
        )
        intent.putExtra("idBiblioteca", idBiblioteca)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
