package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable

class Libro(var id:Int?,
            var ISBN:Int,
            var nombreLibro:String,
            var numeroPaginas:Int,
            var editorial:String,
            var fechaPublicacion:String,
            var numEdicion:Int,
            var bibliotecaId:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "ISBN: ${ISBN} Nombre Libro: ${nombreLibro} Numero Paginas: ${numeroPaginas} Editorial: ${editorial} " +
                "Fecha Publicacion:${fechaPublicacion} Numero Edicion:${numEdicion}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(ISBN)
        parcel.writeString(nombreLibro)
        parcel.writeInt(numeroPaginas)
        parcel.writeString(editorial)
        parcel.writeString(fechaPublicacion)
        parcel.writeInt(numEdicion)
        parcel.writeInt(bibliotecaId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}
