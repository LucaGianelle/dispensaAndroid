package com.dispensa

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.type.DailyValues
import com.dispensa.type.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.register.*
import java.time.LocalDate


class RegisterActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        auth = FirebaseAuth.getInstance()


        pickerPeso.setMinValue(40);
        pickerPeso.setMaxValue(120);
        pickerPeso.wrapSelectorWheel = false

        pickerAltezza.setMinValue(150);
        pickerAltezza.setMaxValue(210);
        pickerAltezza.wrapSelectorWheel = false

        pickerEta.setMinValue(15);
        pickerEta.setMaxValue(99);
        pickerEta.wrapSelectorWheel = false

        var valAlt =""
        pickerAltezza.setOnValueChangedListener { picker, oldVal, newVal ->
            valAlt = "$newVal"
        }

        var valPeso=""
        pickerPeso.setOnValueChangedListener { picker, oldVal, newVal ->
            valPeso = "$newVal"
        }

        var valEta =""
        pickerEta.setOnValueChangedListener { picker, oldVal, newVal ->
            valEta = "$newVal"
        }


        val buttonConf = findViewById<Button>(R.id.buttonConferma)
        database= FirebaseDatabase.getInstance()
        reference=database.getReference("User")
        buttonConf.setOnClickListener{
            signUpUser(valAlt, valPeso, valEta)
        }



}

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData(valAlt: String, valPeso: String, valEta: String){
        val name = inputNome.text.toString().trim()
        val email = inputEmail.text.toString().trim()
        val password = inputPassword.text.toString().trim()
        val altezza = pickerAltezza.value.toString()
        val peso =  pickerPeso.value.toString()
        val eta =  pickerEta.value.toString()

        if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

            val model= User(name, email , password, altezza, peso, eta)
            val utente = FirebaseAuth.getInstance().currentUser
            val id : String = utente.uid

            val dataIscrizione: String = LocalDate.now().toString()
            val extradata = DailyValues("0", "0", "0", "0", dataIscrizione)
            //Qui si inviano i dati a firebase
            reference.child(id).setValue(model)
            inputNome.setText("")
            inputEmail.setText("")
            inputPassword.setText("")

            reference.child(id).child("Valori_giornalieri").setValue(extradata)
        }else{
            Toast.makeText(applicationContext, "All field required",Toast.LENGTH_LONG).show()
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
private fun signUpUser(valAlt: String, valPeso: String, valEta: String) {

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
    val lunghezzaPassword : Int = inputPassword.text.toString().length
    if (lunghezzaPassword <= 5 ) {
        inputPassword.error = "Inserire una password di almeno 6 caratteri"
        inputPassword.requestFocus()
        return
    }

    if (pickerAltezza.value.toString().isEmpty()) {
        Toast.makeText(baseContext, "Inserire l'altezza", Toast.LENGTH_SHORT).show()
        pickerAltezza.requestFocus()
        return
    }

    if (pickerPeso.value.toString().isEmpty()) {
        Toast.makeText(baseContext, "Inserire il peso", Toast.LENGTH_SHORT).show()
        pickerPeso.requestFocus()
        return
    }

    if (pickerEta.value.toString().isEmpty()) {
        Toast.makeText(baseContext, "Inserire il peso", Toast.LENGTH_SHORT).show()
        pickerEta.requestFocus()
        return
    }

    auth.createUserWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
    .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            val user = auth.currentUser
            user!!.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        sendData(valAlt, valPeso, valEta)
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                }
        } else {
            Toast.makeText(
                baseContext, "Registrazione fallita. Riprova più tardi",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
}