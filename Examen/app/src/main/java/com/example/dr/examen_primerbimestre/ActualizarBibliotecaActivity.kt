package com.example.mjg70.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actualizar_biblioteca.*
import kotlinx.android.synthetic.main.activity_actualizar_biblioteca.anioFundacion
import kotlinx.android.synthetic.main.activity_actualizar_biblioteca.txtCiudad
import kotlinx.android.synthetic.main.activity_actualizar_biblioteca.txtNombreB
import kotlinx.android.synthetic.main.activity_actualizar_biblioteca.txtPais
import kotlinx.android.synthetic.main.activity_ingresar_biblioteca.*

class ActualizarBibliotecaActivity : AppCompatActivity() {
    var padreId : Int = 0
    var usuario :String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_biblioteca)
        usuario = intent.getStringExtra("usuario").toString()
        val bibliotecaRecibido = intent.getParcelableExtra<Biblioteca>("Biblioteca")//********************
        txtNombreB.setText(bibliotecaRecibido.nombre)
        txtPais.setText(bibliotecaRecibido.pais.toString())
        txtCiudad.setText(bibliotecaRecibido.ciudad.toString())
        anioFundacion.setText(bibliotecaRecibido.aniofundacion.toString())
        txtPublico.setText(bibliotecaRecibido.publico.toString())
        padreId = bibliotecaRecibido.id!!;
        btnActualizar.setOnClickListener { actualizarBiblioteca() }
        btnEliminar.setOnClickListener { eliminarBiblioteca() }
        btnCrearLibro.setOnClickListener { crearBiblioteca() }
        btnGestionarLIbros.setOnClickListener { gestionarBiblioteca() }
        btnMenuRetorno.setOnClickListener { retorno() }
    }

    fun actualizarBiblioteca(){
        val actualizarBiblioteca = Biblioteca(id = padreId,//********************
            nombre = txtNombreB.text.toString(),
            pais = txtPais.text.toString(),
            ciudad = txtCiudad.text.toString(),
            aniofundacion = anioFundacion.text.toString().toInt(),
            publico = txtPublico.text.toString()
        )
        BDBiblioteca.actualizarBiblioteca(actualizarBiblioteca)
        Toast.makeText(this, "Actualización exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun eliminarBiblioteca(){
        BDBiblioteca.eliminarBiblioteca(padreId);
        Toast.makeText(this, "Eliminación exitosa "+usuario, Toast.LENGTH_SHORT).show()
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    fun crearBiblioteca(){
        val bibliotecacRespaldo = Biblioteca(id = padreId,
            nombre = txtNombreB.text.toString(),
            pais = txtPais.text.toString(),
            ciudad = txtCiudad.text.toString(),
            aniofundacion = anioFundacion.text.toString().toInt(),
            publico = txtPublico.text.toString()
        )
        val retorno = Intent(this, IngresarLibroActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("BibliotecaRespaldo", bibliotecacRespaldo)
        startActivity(retorno)
    }

    fun gestionarBiblioteca(){
        val bibliotecaRespaldo = Biblioteca(id = padreId,
            nombre = txtNombreB.text.toString(),
            pais = txtPais.text.toString(),
            ciudad = txtCiudad.text.toString(),
            aniofundacion = anioFundacion.text.toString().toInt(),
            publico = txtPublico.text.toString()
        )
        val retorno = Intent(this, ConsultarLibroActivity::class.java)
        retorno.putExtra("usuario", usuario)
        retorno.putExtra("padreId", padreId)
        retorno.putExtra("BibliotecaRespaldo", bibliotecaRespaldo)
        startActivity(retorno)
    }

    fun retorno(){
        val retorno = Intent(this, MenuActivity::class.java)
        retorno.putExtra("usuario", usuario)
        startActivity(retorno)
    }

    override fun onBackPressed() {

        val intentMenu = Intent(this, ConsultarBibliotecaActivity::class.java)
        intentMenu.putExtra("usuario", usuario)
        startActivity(intentMenu)
    }
}
