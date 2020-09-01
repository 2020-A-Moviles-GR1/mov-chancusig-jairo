package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_blist_view.*
import kotlin.math.E

class BListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()

        listaEntrenadores.add(Entrenador("Jairo","Chancusig"))
        listaEntrenadores.add(Entrenador("David","Perez"))
        listaEntrenadores.add(Entrenador("Mario","Espin"))
        listaEntrenadores.add(Entrenador("Zoe","Travez"))
        listaEntrenadores.add(Entrenador("Sebas","Pineda"))
        listaEntrenadores.add(Entrenador("Fabiola","Batallas"))

        val adapator: ArrayAdapter<Entrenador> = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //Nombre Layout
            listaEntrenadores //Lista
        )

        lv_numeros.adapter = adapator

        lv_numeros
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view","Posicion $position")
        }


        btn_anadir_entrenador
            .setOnClickListener {
                anadirEntrenador(
                    adapator,
                    listaEntrenadores)

            }

    }

    fun anadirEntrenador(
        adapator: ArrayAdapter<Entrenador>,
        listaEntrenador: ArrayList<Entrenador>
    ){
        listaEntrenador.add(
            Entrenador("Fabiola","Batallas")
        )
        adapator.notifyDataSetChanged()
    }

}
