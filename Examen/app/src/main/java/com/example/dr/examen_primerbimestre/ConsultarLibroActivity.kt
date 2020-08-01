package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar_libro.*

class ConsultarLibroActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    var bibliotecaRespaldo : Biblioteca? = null//********************
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuario = intent.getStringExtra("usuario").toString()
        bibliotecaRespaldo = intent.getParcelableExtra<Biblioteca>("BibliotecaRespaldo")//********************
        padreId = intent.getIntExtra("padreId", -1)
        setContentView(R.layout.activity_consultar_libro)
        val adapter = ArrayAdapter<Libro>(
            this,
            android.R.layout.simple_list_item_1,
            BDLibros.mostrarLibro(padreId)
        )
        lstLibro.adapter = adapter;
        lstLibro.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val libroSeleccionado = parent.getItemAtPosition(position) as Libro
            val intentLibroSeleccionado = Intent(this, ActualizarLibroActivity::class.java)
            intentLibroSeleccionado.putExtra("usuario", usuario)
            intentLibroSeleccionado.putExtra("Libro", libroSeleccionado)
            intentLibroSeleccionado.putExtra("BibliotecaRespaldo", bibliotecaRespaldo)
            startActivity(intentLibroSeleccionado)
        }
    }
}
