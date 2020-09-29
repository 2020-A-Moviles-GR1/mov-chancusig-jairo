package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerVIewActivity : AppCompatActivity() {

    var numeroLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val listaUsuarios = arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
            UsuarioHttp(
                1,
                123123123,
                123123123,
                "0550269528",
                "Jairo",
            "a@a.com",
            "Soltero",
            "123456",
                arrayListOf<PokemonHttp>()
            )

        )
        listaUsuarios.add(
            UsuarioHttp(
                2,
                223123123,
                223123123,
                "1850269528",
                "Juan",
                "j@j.com",
                "Casado",
                "123456",
                arrayListOf<PokemonHttp>()
            )

        )
        listaUsuarios.add(
            UsuarioHttp(
                3,
                333123123,
                333123123,
                "7895269528",
                "Jaime",
                "j@a.com",
                "Divorciado",
                "123456",
                arrayListOf<PokemonHttp>()
            )
        )


        iniciarRecyclerView(
            listaUsuarios,
            this,
            rv_usuarios
        )
    }
    fun iniciarRecyclerView(
        lista: List<UsuarioHttp>,
        actividad: RecyclerVIewActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
                lista,
        actividad,
        recycler_view
        )
        rv_usuarios.adapter = adaptadorUsuario
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()
    }

    fun anadirLikesEnActividad(numero: Int){
        this.numeroLikes = this.numeroLikes + numero
        tv_titulo_rv.text = numeroLikes.toString()
    }

}