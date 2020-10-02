package com.example.examen2b.valoresEstaticos

class Servidor {

    companion object {

        private val ip = "192.168.100.6"
        private val puerto = "1337"

        fun url(ruta: String): String {
            var rutaAux = ""
            when (ruta) {
                "biblioteca" -> rutaAux = "biblioteca"
                "libro" -> rutaAux = "libro"
                else -> rutaAux = ""
            }
            return "http://$ip:$puerto/${rutaAux}"
        }
    }
}