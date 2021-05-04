package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        auth = FirebaseAuth.getInstance()


        pickerPeso.setMinValue(40);
        pickerPeso.setMaxValue(120);
        pickerPeso.wrapSelectorWheel = false

        pickerAltezza.setMinValue(100);
        pickerAltezza.setMaxValue(210);
        pickerAltezza.wrapSelectorWheel = false

        var valAlt: String =""

        pickerAltezza.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            valAlt = "$newVal"

        }

        var valPeso: String =""

        pickerPeso.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            valPeso = "$newVal"

        }





        val buttonConf = findViewById<Button>(R.id.buttonConferma)
        database= FirebaseDatabase.getInstance()
        reference=database.getReference("User")
        buttonConf.setOnClickListener{
            Log.d("TAG", "prova1")
            //sendData(valAlt, valPeso)
            signUpUser(valAlt, valPeso)
           // val acc = Intent (this, HomeActivity::class.java)
            //startActivity(acc)
           //startActivity(Intent(applicationContext, LoginActivity::class.java))
        }




/*buttonConferma.setOnClickListener {
    //signUpUser()
    val acc = Intent (this, HomeActivity::class.java)
    startActivity(acc)
}*/

}
    private fun sendData(valAlt: String, valPeso: String){
        var name = inputNome.text.toString().trim()
        var email = inputEmail.text.toString().trim()
        var password = inputPassword.text.toString().trim()
        var altezza = valAlt
        var peso =  valPeso

        Log.d("TAG", "prova2")

        if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

            var model= DatabaseModel(name, email , password, altezza, peso)
            var id=reference.push().key

            //Qui si inviano i dati a firebase
            reference.child(id!!).setValue(model)
            inputNome.setText("")
            inputEmail.setText("")
            inputPassword.setText("")

            Log.d("TAG", "prova3")

        }else{
            Toast.makeText(applicationContext, "All field required",Toast.LENGTH_LONG).show()
        }
    }

private fun signUpUser(valAlt: String, valPeso: String) {


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
    var lunghezzaPassword : Int = inputPassword.text.toString().length
    Log.e("TAG", "$lunghezzaPassword")
    if (lunghezzaPassword <= 5 ) {
        inputPassword.error = "Inserire una password di almeno 6 caratteri"
        inputPassword.requestFocus()
        return
    }


    if (pickerAltezza == null) {
        Toast.makeText(baseContext, "Inserire l'altezza", Toast.LENGTH_SHORT).show()
        pickerAltezza.requestFocus()
        return
    }

    if (pickerPeso == null) {
        Toast.makeText(baseContext, "Inserire il peso", Toast.LENGTH_SHORT).show()
        pickerPeso.requestFocus()
        return
    }

    auth.createUserWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
    .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
            sendData(valAlt, valPeso)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(baseContext, "Registrazione fallita. Riprova più tardi",
                Toast.LENGTH_SHORT).show()
        }
    }
}

}