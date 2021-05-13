package com.dispensa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.personaldata.*
import kotlinx.android.synthetic.main.register.*

class PersonaldataActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    var myAuth = FirebaseAuth.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser
        val id : String = user.uid

       database.child("User").child(id).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        //per il cibo
        database.child("aliment").get().addOnSuccessListener {
            val result = it.value as ArrayList<String>
                    // Map<String,Map<String, String>>
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firbase", "${result}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        /*var idprova : String = auth.currentUser.uid

        //per gli utenti
        database.child("User").child(idprova).get().addOnSuccessListener {

            val mappaProfilo = it.value as Map<String, String>

            //prove stampa
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firebase","${mappaProfilo}")

            val nameMap: String = mappaProfilo.get("name").toString()
            val emailMap : String = mappaProfilo.get("email").toString()
            var pwMap : String = mappaProfilo.get("password").toString()
            val altezzaMap : String = mappaProfilo.get("altezza").toString()
            val pesoMap : String = mappaProfilo.get("peso").toString()

            nickname.text = nameMap
            emailPersonalData.text = emailMap
            height.text = "$altezzaMap cm"
            weight.text = "$pesoMap kg"

            //Creare un user per caricare i dati nell'oggetto User
            //User(var name:String, var email:String, var password: String, var altezza: String, var peso: String)

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }*/

        var dataUser = DbCommunication.getUser()

        nickname.text = dataUser.name
        emailPersonalData.text = dataUser.email
        height.text = "${dataUser.altezza} cm"
        weight.text = "${dataUser.peso} kg"
        age.text = "${dataUser.eta} anni"

        buttonModifica.setOnClickListener {
            val data = Intent (this, ModifyProfile::class.java)
            startActivity(data)
        }

        btnLogout.setOnClickListener {

            Toast.makeText(this,"Logout...", Toast.LENGTH_LONG).show()
            signOut()
            val back = Intent(this, LoginActivity::class.java)
            startActivity(back)
        }


    }

    private fun signOut() {
        myAuth.signOut()
    }

}