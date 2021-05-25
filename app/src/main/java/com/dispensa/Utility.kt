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

        /*

        %CHO = 2000 * 55% = 1100 kcal;
        %L = 2000 * 25% = 500 kcal;
        %P = 2000 – 1100 – 500 = 400 kcal.


        quantità CHO = 1100 / 3,75 = 293,3 g;
        quantità L = 500 / 9 = 55,6 g;
        quantità P = 400 / 4 = 100 g.

         */

        var choPerc : Int = (kcal * 45) / 100
        var lipPerc : Int = (kcal * 25) / 100
        var proPerc : Int = kcal - (choPerc + lipPerc)

        var tempList : ArrayList<Double> = ArrayList()

        var cho = choPerc / 3.75
        tempList.add(cho)
        var lip = choPerc / 3.75
        tempList.add(lip)
        var pro = choPerc / 3.75
        tempList.add(pro)


        return tempList

    }
}