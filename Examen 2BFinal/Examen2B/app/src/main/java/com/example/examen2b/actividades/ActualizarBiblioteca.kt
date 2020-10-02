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
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_biblioteca.*
import java.lang.Exception

class ActualizarBiblioteca : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_biblioteca)
        val id: Int = this.intent.getIntExtra("id", -1)
        val nombre: String = this.intent.getStringExtra("nombre")
        val ciudad: String = this.intent.getStringExtra("ciudad")
        val fechaFundacion: String = this.intent.getStringExtra("fechaFundacion")
        val numSedes: Int = this.intent.getIntExtra("sedes", -1)
        val publica: Boolean = this.intent.getBooleanExtra("publica", false)
        Log.i("http", "//////////// $id $nombre $ciudad $fechaFundacion $numSedes")

        txt_nom_bli_act.hint = nombre
        txt_ciu_bli_act.hint = ciudad
        fec_fun_bli_act.hint = fechaFundacion
        txt_sed_bli_act.hint = numSedes.toString()

        btn_act_bli_conf.setOnClickListener {
            try {
                var publica = false
                if (sw_pub_bli_act.isChecked) {
                    publica = true
                }
                val biblioteca = Biblioteca(
                    null,
                    id,
                    txt_nom_bli_act.text.toString(),
                    txt_ciu_bli_act.text.toString(),
                    fec_fun_bli_act.text.toString(),
                    txt_sed_bli_act.text.toString().toInt(),
                    publica
                )
                actualizarBiblioteca(biblioteca)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${Datos.nombreUsuario}: Operación exitosa",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun actualizarBiblioteca(biblioteca: Biblioteca) {
        try {
            val url = Servidor.url("biblioteca") + "/${biblioteca.id}"
            val json = """
            {
            "nombre": "${biblioteca.nombre}",
            "ciudad": "${biblioteca.ciudad}",
            "fechaFundacion": "${biblioteca.fechaFundacion}",
            "sedes": ${biblioteca.numSedes},
            "publica" : ${biblioteca.publica}
                                         }
                    """.trimIndent()

            url.httpPut().body(json)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                            Toast.makeText(
                                this,
                                "${Datos.nombreUsuario}: Operación fallida", ////////////////// PILAS
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
                }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "${Datos.nombreUsuario}: Operación fallida", ///////////PILAS
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
