package com.dispensa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.personaldata.*

class PersonaldataActivity : AppCompatActivity() {

    private lateinit var nameValue: String
    private lateinit var emailValue: String
    private var weightValue: Int = 0
    private var heightValue: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        writeProfile()
    }

    fun readProfile(nome: String, mail: String, peso: Int, altezza: Int){
        nameValue = nome
        emailValue = mail
        weightValue = peso
        heightValue = altezza
    }

    fun writeProfile(){

        val pes = "$weightValue kg"
        val alt = "$heightValue cm"

        nickname.text = nameValue
        email.text = emailValue
        weight.text = pes
        height.text = alt
    }
}