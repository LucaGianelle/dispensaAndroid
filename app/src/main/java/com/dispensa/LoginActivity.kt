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

            if(inputPasswordLogin.text.toString().isEmpty() || inputEmailLogin.text.toString().isEmpty()){
                Toast.makeText(this,"Riempire i campi",Toast.LENGTH_SHORT).show()
            }else{
                doLogin()
            }
        }
        button.setOnClickListener {
            val reg = Intent (this, RegisterActivity::class.java)
            startActivity(reg)
        }

    }

    private fun doLogin() {

        if (inputEmailLogin.text.toString().isEmpty()) {
            inputEmail.error = "Inserire un indirizzo e-mail"
            inputEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(inputEmailLogin.text.toString()).matches()) {
            inputEmail.error = "Inserire un indirizzo e-mail valido"
            inputEmail.requestFocus()
            return
        }

        if (inputPasswordLogin.text.toString().isEmpty()) {
            inputPassword.error = "Inserire una password"
            inputPassword.requestFocus()
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
                        //errore, fa loggare con profilo non loggato se se è entrati prima, bloccare undo nella home
                        Log.e("TAG", "ok2")
                    }
                }
    }
}