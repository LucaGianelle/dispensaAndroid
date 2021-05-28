package com.dispensa

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.nutritionalvalues.*

class NutritionalValuesActivity: AppCompatActivity() {

    private val mappaMaxValues = Utility.getMacros()
    private lateinit var mappaDayliValues : Map<String, Double>

    private val maxKcal = Utility.getKcal().toDouble()

    private var kcalperc : Int = 0
    private var carboperc : Int = 0
    private var grassiperc : Int = 0
    private var proteperc : Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.mappaDayliValues = DbCommunication.getNutritionalValues()
        setContentView(R.layout.nutritionalvalues)


        createPerc()

        print("\n @°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°@°" + carboperc + " " + grassiperc + " " + proteperc + " " + kcalperc )

        textViewProgressCalorie.text = "${kcalperc}%"
        textViewProgressProteine.text= "${proteperc}%"
        textViewProgressCarboidrati.text= "${carboperc}%"
        textViewProgressGrassi.text= "${grassiperc}%"


        progressBarCalorie.progress = kcalperc
        progressBarCarboidrati.progress = carboperc
        progressBarProteine.progress = proteperc
        progressBarGrassi.progress = grassiperc



    }

    private fun createPerc(){

        //a:b = c:d
        //(b x c) / a = d

        val cho : Double? = mappaMaxValues.get("carboidrati")
        val lip : Double? = mappaMaxValues.get("grassi")
        val pro : Double? = mappaMaxValues.get("proteine")


        val kcal2 : Double? = mappaDayliValues.get("calorie")
        val cho2 : Double? = mappaDayliValues.get("carboidrati")
        val lip2: Double? = mappaDayliValues.get("grassi")
        val pro2 : Double? = mappaDayliValues.get("proteine")

        print(" "+ kcal2 + " $$$$$$$$$$$$$$$$$$%%%%%%%%%%%%%%%%%%%%%$$$$$$$$$$$$$$$")


       kcalperc = ((100 * kcal2!!) / maxKcal).toInt()
        carboperc = ((100 * cho2!!) / cho!!).toInt()
        grassiperc = ((100 * lip2!!) / lip!!).toInt()
        proteperc = ((100 * pro2!!) / pro!!).toInt()

    }
}