package com.dispensa

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object Utility {

    private var kcal : Int = 0
    private var valoriMap : Map<String, Double> = createMap()

    private var dailyDate : String = ""

    var exit = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(){
        val dataCorrente = LocalDate.now()
        dailyDate = dataCorrente.toString()
    }

    fun getData() : String{
        return dailyDate
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

        print("\n @°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°" + kcal )
        return kcal
    }

    fun macronutrienti_calcolo(){  //cambiare la lista nella mappa

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

        val cho = choPerc / 3.75
        val lip : Double = lipPerc / 9.0
        val pro : Double = proPerc / 4.0

        var tempMap : Map<String, Double>? = null

        tempMap = mutableMapOf("carboidrati" to cho, "grassi" to lip, "proteine" to pro)

        print("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°"+tempMap)

        valoriMap = tempMap


    }

    fun getMacros() : Map<String, Double>{
        return valoriMap
    }

    fun createMap(): Map<String, Double>{
        val map1 : Map<String, Double> = mutableMapOf("carboidrati" to 0.0, "grassi" to 0.0, "proteine" to 0.0)
        return map1
    }

    fun sureExit (){
        exit = true
    }

}