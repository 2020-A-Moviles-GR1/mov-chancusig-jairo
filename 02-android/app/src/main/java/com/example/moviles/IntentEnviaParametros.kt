package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*

class IntentEnviaParametros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)
        //Propiedad clase intent
        //this.intent
        val numeroEncontrado = intent.getIntExtra(
            "numero",
            0
        )
        if(numeroEncontrado != 0) {
            Log.i("intents", "El numero encontrado es: ${numeroEncontrado}")
        }

        val textoCompartido = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textoCompartido != null){
            Log.i("intents", "El texto es: ${textoCompartido}")
        }

        val chetos = intent.getParcelableExtra<Mascota>("chetos")
        if(chetos != null){
            Log.i("parcelable","${chetos.nombre} ${chetos.duenio}")
        }

        val arregloMascota= intent.getParcelableArrayListExtra<Mascota>("arregloMascota")
        if(arregloMascota != null){
            arregloMascota.forEach{
                if(it != null){
                    Log.i("parcelable","EN ARREGLO")
                    Log.i("parcelable","${chetos.nombre} ${chetos.duenio}")
                }

            }

        }


        btn_devolver_respuesta
            .setOnClickListener {
                //Metodo Clase
                //this.finish()
                finish()
            }

        btn_resp_aceptar
            .setOnClickListener {
                val nombre = "Jairo"
                val edad = 23
                val intentRespuesta = Intent()
                intentRespuesta.putExtra("nombre", nombre)
                intentRespuesta.putExtra("edad", edad)
                //this.setResult()
                setResult(
                    RESULT_OK,
                    intentRespuesta
                )
                //this.finish()
                finish()

            }

        btn_resp_cancelar
            .setOnClickListener {
                val intentCancelado = Intent()
                setResult(
                    Activity.RESULT_CANCELED,
                    intentCancelado
                )
                //this.finish()
                finish()

            }

    }
}
