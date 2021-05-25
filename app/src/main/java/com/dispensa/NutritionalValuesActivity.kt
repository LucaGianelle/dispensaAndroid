package com.dispensa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NutritionalValuesActivity: AppCompatActivity() {

    private val mappaValori = Utility.getMacros()
    private val mappaDayliValues = DbCommunication.getDailyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutritionalvalues)


    }
}