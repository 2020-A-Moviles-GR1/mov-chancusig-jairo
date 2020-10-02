package com.example.examen2b.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2b.R
import kotlinx.android.synthetic.main.activity_gestion_bibliotecas.*

class GestionBibliotecas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_bibliotecas)
        btn_gestion_biblioteca.setOnClickListener {
            irListaBibliotecas()
        }
        btn_crear_biblioteca.setOnClickListener {
            irCrearBiblioteca()
        }

        btn_mapa2.setOnClickListener {
            obtenerLibros()
            irMapa()
        }
    }

    private fun irListaBibliotecas() {
        val intent = Intent(
            this,
            ListaBibliotecas::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun irCrearBiblioteca() {
        val intent = Intent(
            this,
            CrearBiblioteca::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        startActivity(intent)
    }

    fun obtenerLibros() {

    }

    private fun irMapa() {
        val intent = Intent(
            this,
            MapsActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
