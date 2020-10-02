package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2b.actividades.GestionBibliotecas
import com.example.examen2b.valoresEstaticos.Datos
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_entrar.setOnClickListener {
            Datos.nombreUsuario = txt_usuario.text.toString()
            irGestion()
        }
    }

    private fun irGestion() {
        val intent = Intent(
            this,
            GestionBibliotecas::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
