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


        numberPicker.setMinValue(40);
        numberPicker.setMaxValue(120);
        numberPicker.wrapSelectorWheel = true

        buttonConferma.setOnClickListener {
            val acc = Intent (this, MainActivity::class.java)
            startActivity(acc)
        }

    }

}