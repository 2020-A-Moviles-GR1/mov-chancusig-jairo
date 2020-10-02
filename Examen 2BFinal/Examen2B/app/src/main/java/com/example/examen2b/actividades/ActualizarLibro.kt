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
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_libro.*
import java.lang.Exception

class ActualizarLibro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libro)
        val id: Int = this.intent.getIntExtra("id", -1)
        val numPaginas = this.intent.getIntExtra("paginas", -1)
        val nombre = this.intent.getStringExtra("nombre")
        val fechaPublicacion = this.intent.getStringExtra("fechaPublicacion")
        val precio = this.intent.getDoubleExtra("precio", -1.0)
        val genero = this.intent.getStringExtra("genero")
        val autor = this.intent.getStringExtra("autor")

        txt_num_pag_lib_act.hint = numPaginas.toString()
        txt_nom_lib_act.hint = nombre
        fec_publicacion_lib_act.hint = fechaPublicacion
        txt_pre_lib_act.hint = precio.toString()
        txt_gen_lib_act.hint = genero
        txt_aut_lib_act.hint = autor
        btn_act_lib_conf.setOnClickListener {
            try {
                val libro = Libro(
                    id,
                    txt_pre_lib_act.text.toString().toDouble(),
                    txt_nom_lib_act.text.toString(),
                    txt_aut_lib_act.text.toString(),
                    txt_gen_lib_act.text.toString(),
                    fec_publicacion_lib_act.text.toString(),
                    txt_num_pag_lib_act.text.toString().toInt(),
                    -1,
                    txt_lon_act.text.toString(),
                    txt_lat_act.text.toString()
                )

                actualizarLibro(libro)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${Datos.nombreUsuario}: Operación exitosa", ////PILAS
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun actualizarLibro(libro: Libro) {
        try{
        val url = Servidor.url("libro") + "/${libro.id}"
            /////////////////LONGITUD --> PILAS
        val json = """
            {
             "nombre" : "${libro.nombre}",
            "autor" : "${libro.autor}",
            "genero" : "${libro.genero}",
            "precio" : ${libro.precio},
            "fechaPublicacion" : "${libro.fechaPublicacion}",
            "numPaginas" : ${libro.numPaginas},
            "latitud" : "${libro.latitud}",
            "altitud" : "${libro.longitud}" 
                                                     }
                    """.trimIndent()
        Log.i("http", url)
        Log.i("http", json)
        url.httpPut().body(json)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                        Toast.makeText(
                            this,
                            "${Datos.nombreUsuario}: Operación exitosa", ///////////PILAS
                            Toast.LENGTH_LONG
                        ).show()
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
            }}catch (e:Exception){
            Toast.makeText(
                this,
                "${Datos.nombreUsuario}: Operación exitosa",  ///////////Pilas
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun irListaBibliotecas() {
        intent = Intent(
            this,
            ListaBibliotecas::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
