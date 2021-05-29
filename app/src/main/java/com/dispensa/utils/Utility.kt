package com.dispensa.utils

object Utility {

    private var kcal : Int = 0
    private var valoriMap : Map<String, Double> = createMap()

    var bLoop : Boolean = true

    var exit = false


    fun kcal_giornaliere_calcolo(age :String, peso : String) {

        val ifAge : Int = age.toInt()
        val ifPeso : Int = peso.toInt()
        var temp : Int = 0

        if(ifAge < 30){
            temp = (15 * ifPeso) + 679
        }

        if((ifAge < 60)&&(ifAge >= 30)){
            temp = (12 * ifPeso) + 879
        }

        if(ifAge >= 60){
            temp = (12 * ifPeso) + 700
        }

        kcal = temp
    }

    fun getKcal() :Int{
        return kcal
    }

    fun macronutrienti_calcolo(){

        val choPerc : Int = (kcal * 45) / 100
        val lipPerc : Int = (kcal * 25) / 100
        val proPerc : Int = kcal - (choPerc + lipPerc)

        val cho = choPerc / 3.75
        val lip : Double = lipPerc / 9.0
        val pro : Double = proPerc / 4.0

        var tempMap : Map<String, Double>? = null

        tempMap = mutableMapOf("carboidrati" to cho, "grassi" to lip, "proteine" to pro)

        valoriMap = tempMap


    }

    fun getMacros() : Map<String, Double>{
        return valoriMap
    }

    fun createMap(): Map<String, Double>{
        val map1 : Map<String, Double> = mutableMapOf("carboidrati" to 0.0, "grassi" to 0.0, "proteine" to 0.0)
        return map1
    }

}