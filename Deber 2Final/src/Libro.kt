
class Libro(
    var nombre:String,
    var autor:String,
    var genero:String,
    var anio:Int,
    var precio:Float,
    var nombreBiblioteca: String


){


    override fun toString ():String{
        return "${nombre},${autor},${genero},${anio},${precio}\n"
    }

}
