package com.example.moviles

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//Para añadir Recyler View necesitamos migrar a Androidx y añadir la dependecia:
//implementation 'com.android.support:recyclerview-v7:28.0.0'

class RecyclerAdaptador(
    private val listaUsuarios: List<UsuarioHttp>,
//    private val contexto
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    }
}