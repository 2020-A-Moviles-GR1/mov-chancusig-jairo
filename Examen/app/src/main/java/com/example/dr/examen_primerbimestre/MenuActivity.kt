package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        usuario = intent.getStringExtra("usuario").toString()
        btnBiblioteca.setOnClickListener {gestionarBiblioteca() }
        btnCrearBiblioteca.setOnClickListener{ crearBiblioteca()}
    }

    fun gestionarBiblioteca(){
        val intentGestionarBiblioteca = Intent(this, ConsultarBibliotecaActivity::class.java)
        intentGestionarBiblioteca.putExtra("usuario", usuario)
        startActivity(intentGestionarBiblioteca)
    }

    fun crearBiblioteca(){
        val intentCrearBiblioteca = Intent(this, IngresarBibliotecaActivity::class.java)
        intentCrearBiblioteca.putExtra("usuario", usuario)
        startActivity(intentCrearBiblioteca)
    }

    override fun onBackPressed() {
        val intentMenu = Intent(this, MainActivity::class.java)

        startActivity(intentMenu)
    }
}
