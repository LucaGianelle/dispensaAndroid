package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.login.inputEmail as inputEmail1
import kotlinx.android.synthetic.main.login.inputPassword as inputPassword1

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        auth = FirebaseAuth.getInstance()


        pickerPeso.setMinValue(40);
        pickerPeso.setMaxValue(120);
        pickerPeso.wrapSelectorWheel = true

        pickerAltezza.setMinValue(100);
        pickerAltezza.setMaxValue(210);
        pickerAltezza.wrapSelectorWheel = true

        buttonConferma.setOnClickListener {
            signUpUser()
            /*val acc = Intent (this, MainActivity::class.java)
            startActivity(acc)*/
        }

    }

    private fun signUpUser () {

        if (inputNome.text.toString().isEmpty()) {
            inputNome.error = "Inserire un nome"
            inputNome.requestFocus()
            return
        }

        if (inputEmail.text.toString().isEmpty()) {
            inputEmail.error = "Inserire un indirizzo e-mail"
            inputEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()) {
            inputEmail.error = "Inserire un indirizzo e-mail valido"
            inputEmail.requestFocus()
            return
        }


        if (inputPassword.text.toString().isEmpty()) {
            inputPassword.error = "Inserire una password"
            inputPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Registrazione fallita. Riprova più tardi",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}