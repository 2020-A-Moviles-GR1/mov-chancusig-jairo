package com.example.mjg70.examen

import android.os.Parcel
import android.os.Parcelable


class Biblioteca(var id:Int?, //********************
                 var nombre:String,
                 var pais:String,
                 var ciudad:String,
                 var aniofundacion:Int,
                 var publico:String) :Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "NOMBRE: ${nombre} PAIS: ${pais} CIUDAD: ${ciudad} " +
                "AÑO DE FUNDACION:${aniofundacion} PUBLICA:${publico}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(pais)
        parcel.writeString(ciudad)
        parcel.writeInt(aniofundacion)
        parcel.writeString(publico)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Biblioteca> {//********************
        override fun createFromParcel(parcel: Parcel): Biblioteca {//********************
            return Biblioteca(parcel)//********************
        }

        override fun newArray(size: Int): Array<Biblioteca?> {//********************
            return arrayOfNulls(size)
        }
    }
}