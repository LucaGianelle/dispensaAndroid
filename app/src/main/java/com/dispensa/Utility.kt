package com.dispensa

object Utility {

    private var kcal : Int = 0
    //private var valoriMap : Map<String, Double> = macronutrienti_calcolo()
    private var valoriMap : ArrayList<Double> = macronutrienti_calcolo()

    fun setData(){

    }

    fun kcal_giornaliere_calcolo(age :String, peso : String) {

        var ifAge : Int = age.toInt()
        var ifPeso : Int = peso.toInt()
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

    fun macronutrienti_calcolo() : ArrayList<Double>{

        kcal = 2000
        print("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°")
        /*

        %CHO = 2000 * 55% = 1100 kcal;
        %L = 2000 * 25% = 500 kcal;
        %P = 2000 – 1100 – 500 = 400 kcal.


        quantità CHO = 1100 / 3,75 = 293,3 g;
        quantità L = 500 / 9 = 55,6 g;
        quantità P = 400 / 4 = 100 g.

         */

        val choPerc : Int = (kcal * 45) / 100
        val lipPerc : Int = (kcal * 25) / 100
        val proPerc : Int = kcal - (choPerc + lipPerc)

        val tempList : ArrayList<Double> = ArrayList()

        val cho = choPerc / 3.75
        tempList.add(cho)
        val lip : Double = lipPerc / 9.0
        tempList.add(lip)
        val pro : Double = proPerc / 4.0
        tempList.add(pro)

        var tempMap : Map<String, Double>? = null

        tempMap = mutableMapOf("carboidrati" to cho, "grassi" to lip, "proteine" to pro)

        print("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°"+tempMap)



        return tempList

    }
}