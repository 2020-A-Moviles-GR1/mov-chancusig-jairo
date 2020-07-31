
class Biblioteca(
    var nombreBiblioteca: String,
    var paisBiblioteca: String,
    var sedeBiblioteca: String,
    var tipoBiblioteca: String,
    var anioFundacion:Int
){

    override fun toString ():String{
        return "${nombreBiblioteca},${paisBiblioteca},${sedeBiblioteca},${tipoBiblioteca},${anioFundacion}\n"
    }

}

