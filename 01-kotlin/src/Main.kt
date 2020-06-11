import java.util.*

fun main(args:Array<String>){
    print("Hola")

    //int edad = 23;  -> así se declara una variable para Java
    //?
    //Mutable
    var edadProfesor = 31 // No especificamos el tipo de dato
                         // (;) No es necesario al final de la linea
    //Duck Typing -> Si se ve como un pato y suena como un pato, es un pato (analogía)

    //var edadCachorro: X -> neesitamos el tipo de dato
    var edadCachorro:Int
    edadCachorro = 3
    edadProfesor = 32
    edadCachorro = 4
    //Inmutable -> es preferible usar variables inmutables
    var numeroCuenta = 123456 // NO SE PUEDEN REASIGNAR
    // numeroCuenta = 1231

    //Tipo variables
    val nombreProfesor: String = "Jairo David"
    val sueldo:Double = 12.20
    val apellidoProfesor: Char = 'a' //Los char pueden ser guardados entre comillas simple
    val fechaNacimiento = Date()//new Date() -> En Java

    if(sueldo == 12.20){

    } else {

    }

    when (sueldo) { //switch en kotlin
        12.20 -> print("Sueldo normal")
        -12.20 -> print("Sueldo negativo")
        else -> print("No se reconoce el Sueldo")
    }

    val esSueldoMayorAlEstablecido = if (sueldo == 12.20) true else false
    //EXPRESION ? X : Y

    calcularSueldo(1000.00, 14.00)
    calcularSueldo(
            tasa = 16.00,
            sueldo = 800)
}//Named Parameters


fun calcularSueldo(
      sueldo: Double, //Requerido
      tasa: Double = 12.00, //Requerido; No hace falta agregarlo debido
                          // a que tiene valor por defecto
      calculoEspecial:Int? = null  //Si se quiere que este parametro sea especial se le agrega "?"

//Por lo cual tenemos PARÁMETROS: REQUERIDOS, POR DEFECTO Y OPCIONALES

): Double{
    if (calculoEspecial !=null) {
        return sueldo * tasa * calculoEspecial
    }else{
        return sueldo * tasa
    }
    }

fun imprimirMensaje(): Unit{ //Unit = Void
    print("")
}