package com.dispensa

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        //auth = FirebaseAuth.getInstance()


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
            sendData(valAlt, valPeso)
           //startActivity(Intent(applicationContext, LoginActivity::class.java))
        }




/*buttonConferma.setOnClickListener {
    //signUpUser()
    val acc = Intent (this, MainActivity::class.java)
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

            var model= DatabaseModel(name, email , password, peso, altezza)
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

/*private fun signUpUser () {

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
}*/

}