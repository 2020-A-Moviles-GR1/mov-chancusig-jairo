package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_http.*
import com.github.kittinunf.result.Result


class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.100.6:1337"   // EN EL CASO DE NO FUNCIONAR, CAMBIAR localhost POR LA IP DE LA MÁQUINA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        btn_obtener.
        setOnClickListener {
            obtenerUsuario()
        }
        btn_crear
            .setOnClickListener {
                crearUsuario()
            }

    }

    fun crearUsuario(){
        val url = urlPrincipal + "/Usuario"
        val parametrosUsuario: List<Pair<String, String>> = listOf(
            "cedula" to "0550269572",
            "nombre" to "jaque",
            "correo" to "j@j.com",
            "estadoCivil" to "Casado",
            "password" to "A123456789b"
        )
        url
            .httpPost(parametrosUsuario)
            .responseString{
                    request, response, result ->
                when(result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaxon", "${usuarioString}")

                    }
                }

            }

    }

    fun obtenerUsuario(){
        val pokemonString = """
            {
            "createdAt": 1597671444841,
            "updatedAt": 1597672206086,
            "id": 1,
            "nombre": "Pikachu",
            "usuario": 1
            }
            """.trimIndent()

        val pokemonInstancia = Klaxon()
            .parse<PokemonHttp>(pokemonString)

        if(pokemonInstancia != null){
            Log.i("http-klaxon","Nombre: ${pokemonInstancia.nombre}")
            Log.i("http-klaxon","Fecha Creación: ${pokemonInstancia.fechaCreacion}")
        }

        val url = urlPrincipal + "/Usuario"
        url
            .httpGet()
            .responseString{
                    request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-klaxon","Data: ${data}")

                        val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                        if(usuarios != null){
                            usuarios.forEach{
                                Log.i("http-klaxon",
                                    "Nombre: ${it.nombre}" + "Estado civil: ${it.estadoCivil}"
                                )
                                if(it.pokemons.size > 0){
                                    it.pokemons.forEach {
                                        Log.i("http-klaxon","Nombre: ${it.nombre}")
                                    }
                                }
                            }
                        }


                    }

                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.message}")
                    }
                }
            }

    }

}
//Klaxon
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)


        btn_obtener.setOnClickListener{
            obtenerUsuario()
        }
    }


    fun obtenerUsuario(){
        //pokemon
        val pokemonString ="""
        {
            "createdAt": 1597711643258,
            "updatedAt": 1597711643258,
            "id": 2,
            "nombre": "Bulbasaur",
            "usuario": 1
        }
        """.trimIndent()
        //instancia del pokemon
        val pokemonInstancia =Klaxon().parse<PokemonHttp>(pokemonString)

        if (pokemonInstancia != null){
            Log.i("http-klaxon","nombre: ${pokemonInstancia.nombre}")
            Log.i("http-klaxon","nombre: ${pokemonInstancia.fechaCreacion}")
        }


        val url = urlPrincipal + "/Usuario"
        url.httpGet().responseString{
                request, response, result ->
            when(result){
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data: ${data}")


                    val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                    if (
                        usuarios != null
                    ) {
                        usuarios.forEach {
                            Log.i("http-klaxon","\nEl usuario: ${it.nombre} "

                                )

                                if (it.pokemons is List<*>
                                ) {
                                    if (it.pokemons!!.size > 0
                                    ) { it.pokemons!!.forEach {
                                            it as PokemonHttp

                                            Log.i("http-klaxon","Capturó al Pokemon: ${it.nombre}\n"
                                            )
                                        }
                                    }
                                }

                            }
                        }
                    }
                is Result.Failure ->{
                    val ex = result.getException()
                    Log.i("http-klaxon","Error: ${ex.message}")
                }
            }
        }

    }



    fun mostrarPokemons() {

        val url = urlPrincipal + "/pokemon"

        url
            .httpGet()
            .responseString{
                    request, response, result ->

                when(result){
                    is Result.Success ->{
                        val data = result.get()

                        val pokemons = Klaxon()
                            .converter(PokemonHttp.myConverter)
                            .parseArray<PokemonHttp>(data)


                    }
                    is Result.Failure ->{
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.message}")
                    }
                }

            }

    }



}

 */