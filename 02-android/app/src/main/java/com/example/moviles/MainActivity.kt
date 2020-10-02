package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity","OnCreate")

        btn_ciclo_vida
            .setOnClickListener{boton ->

                //this.irCicloVida()
                irCicloVida();

            }

        btn_list_view
            .setOnClickListener{boton ->

                //this.irCicloVida()
                irListView();

            }
        btn_intent_respuesta
            .setOnClickListener {
                irAIntentConRespuesta()

            }

       /* bnt_intent_implicito
            .setOnClickListener {
                enviarenviarIntentConRespuesta()
            }*/

        btn_intent_implicito
            .setOnClickListener {
                enviarIntentConRespuesta()
            }

        btn_resp_propia
            .setOnClickListener {
                enviarIntentConRespuestaPropia()
            }
        btn_http
            .setOnClickListener {
                abrirActividadHttp()
            }
        btn_recycler
            .setOnClickListener {
                abrirRecyclerViewActvity()
            }
        btn_mapa
            .setOnClickListener {
                abrirMapaActivity()
            }
    }

    fun abrirMapaActivity(){
        val intentExplicito = Intent(
            this,
            MapsActivity::class.java       //nos dirige a la actividad CicloVida
        )
        startActivity(intentExplicito)
    }

    fun abrirRecyclerViewActvity(){
        val intentExplicito = Intent(
            this,
            RecyclerVIewActivity::class.java       //nos dirige a la actividad CicloVida
        )
        startActivity(intentExplicito)
    }

    fun abrirActividadHttp(){
        val intentExplicito = Intent(
            this,
            HttpActivity::class.java       //nos dirige a la actividad CicloVida
        )
        startActivity(intentExplicito)
    }

    fun enviarIntentConRespuestaPropia(){
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java       //nos dirige a la actividad CicloVida
        )
        startActivityForResult(intentExplicito,305)
    }

    fun enviarIntentConRespuesta(){
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        //this.startActivityForResult(intent, codigoDeRespuesta)
        //304 lo establecemos nosotros, no es ningun nuemero especial
        startActivityForResult(intentConRespuesta,304)
    }

    override fun onActivityResult(
        requestCode: Int, //Numero que enviamos
        resultCode: Int,
        data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            RESULT_OK -> {
                Log.i("resultado","OK")

                when(requestCode){
                    304 ->{ //Contactos
                    val uri = data?.data
                        if(uri != null) {
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado","Telefono: ${telefono}")

                        }
                    }
                    305 ->{
                        if(data != null){
                            val nombre = data.getStringExtra("nombre")
                            val edad= data.getIntExtra("edad",0)
                            Log.i("resultado", "Nombre: ${nombre} Edad: ${edad}")

                        }
                    }
                }


            }
            RESULT_CANCELED ->{
                Log.i("resultado","=(")
            }

        }
    }


    fun irAIntentConRespuesta(){
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java       //nos dirige a la actividad CicloVida
        )
        intentExplicito.putExtra("numero",2)

        val jairo = Usuario(
            "Jairo",
            23,
            Date(),
            1.0
        )

        val chetos = Mascota(
            "Chetos",
            jairo
        )
        val arregloMascotas = arrayListOf<Mascota>(chetos)

        intentExplicito.putExtra("chetos", chetos)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)

        startActivity(intentExplicito)
    }




    fun irCicloVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java       //nos dirige a la actividad CicloVida
        )
            //this.startActivity(intentExplicito)
        startActivity(intentExplicito)
    }





    fun irListView(){
        val intentExplicito = Intent(
            this,
            BListViewActivity::class.java       //nos dirige a la actividad CicloVida
        )
        //this.startActivity(intentExplicito)
        startActivity(intentExplicito)
    }

}
