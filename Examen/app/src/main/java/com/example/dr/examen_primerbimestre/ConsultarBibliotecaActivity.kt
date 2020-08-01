package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consultar_biblioteca.*

class ConsultarBibliotecaActivity : AppCompatActivity() {
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_biblioteca)
        usuario = intent.getStringExtra("usuario").toString()
        val adapter = ArrayAdapter<Biblioteca>(//********************
            this,
            android.R.layout.simple_list_item_1,
            BDBiblioteca.mostrarBiblioteca()
        )
        lstView.adapter = adapter;
        lstView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val bibliotecaSeleccionada = parent.getItemAtPosition(position) as Biblioteca//********************
            val intentBibliotecaSeleccionada = Intent(this, ActualizarBibliotecaActivity::class.java)
            intentBibliotecaSeleccionada.putExtra("Biblioteca", bibliotecaSeleccionada)
            intentBibliotecaSeleccionada.putExtra("usuario", usuario)
            startActivity(intentBibliotecaSeleccionada)
        }




    }

    override fun onBackPressed() {

        val intentMenu = Intent(this, MenuActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
