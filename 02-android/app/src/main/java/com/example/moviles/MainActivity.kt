package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

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
