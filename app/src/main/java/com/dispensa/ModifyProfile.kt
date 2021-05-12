package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.modify_profile.*
import kotlinx.android.synthetic.main.personaldata.*

class ModifyProfile: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_profile)
        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser
        val id : String = user.uid

        var idUserCorrente : String = auth.currentUser.uid
        var nameMap = ""
        var emailMap = ""
        var pwMap = ""
        var altezzaMap = ""
        var pesoMap = ""


        //per gli utenti
        database.child("User").child(idUserCorrente).get().addOnSuccessListener {

            val mappaProfilo = it.value as Map<String, String>

            //prove stampa
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firebase","${mappaProfilo}")
            nameMap = mappaProfilo.get("name").toString()
            emailMap = mappaProfilo.get("email").toString()
            pwMap = mappaProfilo.get("password").toString()
            altezzaMap = mappaProfilo.get("altezza").toString()
            pesoMap = mappaProfilo.get("peso").toString()


            modificaNome.setText(nameMap)
            //togliere mail
            modificaEmail.setText(emailMap)
            modificaPassword.setText(pwMap)
            //height.text = "$altezzaMap cm"
            //weight.text = "$pesoMap kg"



            //Creare un user per caricare i dati nell'oggetto User
            //User(var name:String, var email:String, var password: String, var altezza: String, var peso: String)

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }


        buttonConferma.setOnClickListener {
            var temp : String = modificaNome.text.toString()
            if(nameMap.equals(temp) == false){
                database.child("User").child(idUserCorrente).child("name").setValue(temp)
            }

            temp = modificaPassword.text.toString()
            if(nameMap.equals(temp) == false){
                database.child("User").child(idUserCorrente).child("password").setValue(temp)
            }

            /*temp = modificaPickerAltezza.text.toString()
            if(nameMap.equals(temp)){
                database.child("User").child(idUserCorrente).child("name").setValue(name)
            }

            temp = modificaNome.text.toString()
            if(nameMap.equals(temp)){
                database.child("User").child(idUserCorrente).child("name").setValue(name)
            }*/

            val data = Intent (this, PersonaldataActivity::class.java)
            startActivity(data)
        }
    }
}