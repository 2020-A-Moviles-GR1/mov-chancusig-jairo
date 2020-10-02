package com.example.examen2b.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examen2b.R
import com.example.examen2b.modelo.Libro
import com.example.examen2b.valoresEstaticos.Datos
import com.example.examen2b.valoresEstaticos.Servidor
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_libro.*
import java.lang.Exception

class CrearLibro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_libro)
        val idBiblioteca: Int = this.intent.getIntExtra("idBiblioteca", -1)
        btn_ins_lib.setOnClickListener {
            try {
                val libro = Libro(
                    -1,
                    txt_pre_lib.text.toString().toDouble(),
                    txt_nom_lib.text.toString(),
                    txt_aut_lib.text.toString(),
                    txt_gen_lib.text.toString(),
                    fec_publicacion_lib.text.toString(),
                    txt_num_pag_lib.text.toString().toInt(),
                    idBiblioteca,
                    txt_longitud.text.toString(), ////////altitud
                    txt_latitud.text.toString()
                )

                ingresarLibro(libro)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${Datos.nombreUsuario}: Operación exitosa", /////PILAS
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun ingresarLibro(libro: Libro) {

        try {
            val url = Servidor.url("libro")
            /////////////LONGITUD ----> PILAS
            val json = """
            {
            "nombre" : "${libro.nombre}",
            "autor" : "${libro.autor}",
            "genero" : "${libro.genero}",
            "precio" : ${libro.precio},
            "fechaPublicacion" : "${libro.fechaPublicacion}",
            "numPaginas" : ${libro.numPaginas},
            "latitud" : "${libro.latitud}",
            "altitud" : "${libro.longitud}",
            "idBiblioteca": ${libro.idBiblioteca}
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
                                    "${Datos.nombreUsuario}: Operación exitosa",
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
                "${Datos.nombreUsuario}: Operación exitosa",  ////PILAS
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun irListaBibliotecas() {
        val intent = Intent(
            this,
            ListaBibliotecas()::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
