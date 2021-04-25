package com.dispensa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        buttonAccesso.setOnClickListener {
            val acc = Intent (this, HomeActivity::class.java)
            startActivity(acc)
        }
        button.setOnClickListener {
            val reg = Intent (this, RegisterActivity::class.java)
            startActivity(reg)
        }

    }
}