package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance()


        buttonAccesso.setOnClickListener {
            doLogin()
        }
        button.setOnClickListener {
            val reg = Intent (this, RegisterActivity::class.java)
            startActivity(reg)
        }

    }

    override fun onBackPressed (){
        Toast.makeText(this, "Tasto disabilitato qui", Toast.LENGTH_SHORT).show()
    }

    private fun doLogin() {


        if (inputEmailLogin.text.toString().isEmpty()) {
            inputEmailLogin.error = "Inserire un indirizzo e-mail"
            inputEmailLogin.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(inputEmailLogin.text.toString()).matches()) {
            inputEmailLogin.error = "Inserire un indirizzo e-mail valido"
            inputEmailLogin.requestFocus()
            return
        }

        if (inputPasswordLogin.text.toString().isEmpty()) {
            inputPasswordLogin.error = "Inserire una password"
            inputPasswordLogin.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(inputEmailLogin.text.toString(), inputPasswordLogin.text.toString())
                .addOnCompleteListener(this) { task ->
                    val prova: Boolean = task.isSuccessful
                    Log.e("TAG","$prova")

                    if (prova) {
                        val user = auth.currentUser
                        val acc = Intent (this, HomeActivity::class.java)
                        startActivity(acc)

                        Log.e("TAG", "ok")
                    } else {
                        Log.e("TAG", "ok2")
                    }
                }
    }
}