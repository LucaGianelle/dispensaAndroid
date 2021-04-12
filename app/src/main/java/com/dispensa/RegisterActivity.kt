package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)


        pickerPeso.setMinValue(40);
        pickerPeso.setMaxValue(120);
        pickerPeso.wrapSelectorWheel = true

        pickerAltezza.setMinValue(100);
        pickerAltezza.setMaxValue(210);
        pickerAltezza.wrapSelectorWheel = true

        buttonConferma.setOnClickListener {
            val acc = Intent (this, MainActivity::class.java)
            startActivity(acc)
        }

    }

}