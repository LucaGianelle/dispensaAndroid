package com.dispensa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NutritionalValuesActivity: AppCompatActivity() {

    private val mappaMaxValues = Utility.getMacros()
    private val mappaDayliValues = DbCommunication.getDailyMap()

    private var carboperc : Int = 0
    private var grassiperc : Int = 0
    private var proteperc : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutritionalvalues)

        createPerc()

        print("\n @°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°" + carboperc + " " + grassiperc + " " + proteperc )

    }

    private fun createPerc(){

        //a:b = c:d
        //(b x c) / a = d

        val cho : Double? = mappaMaxValues.get("carboidrati")
        val lip : Double? = mappaMaxValues.get("grassi")
        val pro : Double? = mappaMaxValues.get("proteine")

        val cho2 : Double? = mappaDayliValues.get("carboidrati")
        val lip2: Double? = mappaDayliValues.get("grassi")
        val pro2 : Double? = mappaDayliValues.get("proteine")


        carboperc = ((100 * cho2!!) / cho!!).toInt()
        grassiperc = ((100 * lip2!!) / lip!!).toInt()
        proteperc = ((100 * pro2!!) / pro!!).toInt()


    }
}