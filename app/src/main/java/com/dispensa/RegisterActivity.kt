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
import kotlinx.android.synthetic.main.register.inputEmail as inputEmail1
import kotlinx.android.synthetic.main.register.inputPassword as inputPassword1

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
    val acc = Intent (this, MainActivity::class.java)
    startActivity(acc)
}

}

private fun signUpUser () {

if (inputNome.text.toString().isEmpty()) {
    inputNome.error = "Inserire un nome"
    inputNome.requestFocus()
    return
}

if (inputEmail1.text.toString().isEmpty()) {
    inputEmail1.error = "Inserire un indirizzo e-mail"
    inputEmail1.requestFocus()
    return
}

if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail1.text.toString()).matches()) {
    inputEmail1.error = "Inserire un indirizzo e-mail valido"
    inputEmail1.requestFocus()
    return
}


if (inputPassword1.text.toString().isEmpty()) {
    inputPassword1.error = "Inserire una password"
    inputPassword1.requestFocus()
    return
}

auth.createUserWithEmailAndPassword(inputEmail1.text.toString(), inputPassword1.text.toString())
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