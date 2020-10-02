package com.example.examen2b.adaptador

import com.example.examen2b.R
import com.example.examen2b.actividades.ListaBibliotecas
import com.example.examen2b.modelo.Biblioteca
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView


class AdaptadorListaBibliotecas(
    private val listaBibliotecas: ArrayList<Biblioteca>,
    private val contexto: ListaBibliotecas,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorListaBibliotecas.MyViewHolder>() {

    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var infoTextView: TextView
        var idTextView: TextView
        var nombreTextView: TextView
        var ciudadTextView: TextView
        var fechaFundacionTextView: TextView
        var numSedesTextView: TextView
        var PublicaTextView: TextView
        var eliminarBoton: Button
        var actualizarBoton: Button

        init {

            infoTextView = view.findViewById(R.id.txv_info_pac) as TextView
            idTextView = view.findViewById(R.id.txv_id_bli) as TextView
            nombreTextView = view.findViewById(R.id.txv_nombre_bli) as TextView
            ciudadTextView = view.findViewById(R.id.txv_ciudad_bli) as TextView
            fechaFundacionTextView = view.findViewById(R.id.txv_fecha_fundacion_bli) as TextView
            numSedesTextView = view.findViewById(R.id.txv_numero_sedes_bli) as TextView
            PublicaTextView = view.findViewById(R.id.txv_publica_bli) as TextView
            eliminarBoton = view.findViewById(R.id.btn_eliminar_bli) as Button
            actualizarBoton = view.findViewById(R.id.btn_actualizar_bli) as Button

            val layout = view.findViewById(R.id.lay_biblioteca) as LinearLayout

            layout.setOnClickListener {
                val biblioteca: Biblioteca? = listaBibliotecas.find { biblioteca ->
                    idTextView.text.toString().toInt() == biblioteca.id
                }
                contexto.irGestionarLibros(idTextView.text.toString().toInt(), biblioteca!!.libroDeBiblioteca)
            }

            actualizarBoton.setOnClickListener {
                var publica: Boolean = false
                if (PublicaTextView.text.toString() == "SI") {
                    publica = true
                }
                val biblioteca = Biblioteca(
                    null,
                    idTextView.text.toString().toInt(),
                    nombreTextView.text.toString(),
                    ciudadTextView.text.toString(),
                    fechaFundacionTextView.text.toString(),
                    numSedesTextView.text.toString().toInt(),
                    publica
                )
                contexto.irActualizarBiblioteca(biblioteca)
            }

            eliminarBoton.setOnClickListener {
                // crear paciente
                contexto.eliminarBiblioteca(idTextView.text.toString().toInt())

            }
        }

    }

    //Esta función define el template que vamos a utilizar.
    // El template esta en la carpeta de recursos res/layout -> layout
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorListaBibliotecas.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_biblioteca,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    //Devuelve el número de items de la lista
    override fun getItemCount(): Int {
        return listaBibliotecas.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorListaBibliotecas.MyViewHolder, position: Int) {
        val biblioteca: Biblioteca = listaBibliotecas[position]
        myViewHolder.idTextView.text = biblioteca.id.toString()
        myViewHolder.infoTextView.text = "Biblioteca #${biblioteca.id}"
        myViewHolder.nombreTextView.text = biblioteca.nombre
        myViewHolder.ciudadTextView.text = biblioteca.ciudad
        myViewHolder.fechaFundacionTextView.text = biblioteca.fechaFundacion
        myViewHolder.numSedesTextView.text = biblioteca.numSedes.toString()
        if (biblioteca.publica) {
            myViewHolder.PublicaTextView.text = "SI"
        } else {
            myViewHolder.PublicaTextView.text = "NO"
        }


    }

}