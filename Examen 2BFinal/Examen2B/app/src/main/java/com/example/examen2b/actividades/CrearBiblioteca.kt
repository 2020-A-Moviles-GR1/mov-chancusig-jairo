package com.example.examen2b.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examen2b.R
import com.example.examen2b.modelo.Biblioteca
import com.example.examen2b.valoresEstaticos.Datos
import com.example.examen2b.valoresEstaticos.Servidor
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_biblioteca.*

class CrearBiblioteca : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_biblioteca)
        btn_ins_bli.setOnClickListener {
            try {
                val biblioteca = Biblioteca(
                    null,
                    -1,
                    txt_nom_bli.text.toString(),
                    txt_ciu_bli.text.toString(),
                    dp_fec_fun_bli.text.toString(),
                    txt_sed_bli.text.toString().toInt(),
                    sw_pub_bli.text.toString().toBoolean()
                )
                ingresarBiblioteca(biblioteca)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${Datos.nombreUsuario}: Operación exitosa", ////PILAS
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun ingresarBiblioteca(biblioteca: Biblioteca) {
        try {
            val url = Servidor.url("biblioteca")
            val json = """
            {
            "nombre": "${biblioteca.nombre}",
            "ciudad": "${biblioteca.ciudad}",
            "fechaFundacion": "${biblioteca.fechaFundacion}",
            "sedes": ${biblioteca.numSedes},
            "publica" : ${biblioteca.publica}
                                         }
                    """.trimIndent()

            Log.i("http", json)
            url.httpPost().body(json)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
                            runOnUiThread {
                                Toast.makeText(
                                    this,
                                    "${Datos.nombreUsuario}: Operación exitosa", ///PILAS
                                    Toast.LENGTH_LONG
                                ).show()
                                irListaBibliotecas()
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "${Datos.nombreUsuario}: Operación exitosa", ///PILAS
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun irListaBibliotecas() {
        val intent = Intent(
            this,
            ListaBibliotecas::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
