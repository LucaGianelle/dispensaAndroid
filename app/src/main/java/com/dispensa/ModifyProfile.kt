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
import kotlinx.android.synthetic.main.modify_profile.buttonConferma
import kotlinx.android.synthetic.main.personaldata.*
import kotlinx.android.synthetic.main.register.*

class ModifyProfile: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_profile)
        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        modificaPickerPeso.setMinValue(40);
        modificaPickerPeso.setMaxValue(120);
        modificaPickerPeso.wrapSelectorWheel = false

        modificaPickerAltezza.setMinValue(100);
        modificaPickerAltezza.setMaxValue(210);
        modificaPickerAltezza.wrapSelectorWheel = false

        modificaPickerEta.setMinValue(15);
        modificaPickerEta.setMaxValue(99);
        modificaPickerEta.wrapSelectorWheel = false

        var valAlt=""

        modificaPickerAltezza.setOnValueChangedListener { picker, oldVal, newVal ->
            //Display the newly selected number to text view
            valAlt = "$newVal"
        }

        var valPeso=""
        modificaPickerPeso.setOnValueChangedListener { picker, oldVal, newVal ->
            //Display the newly selected number to text view
            valPeso = "$newVal"
        }

        var valEta=""
        modificaPickerEta.setOnValueChangedListener { picker, oldVal, newVal ->
            //Display the newly selected number to text view
            valEta = "$newVal"
        }

        val user = FirebaseAuth.getInstance().currentUser
        val id : String = user.uid

        var idUserCorrente : String = auth.currentUser.uid
        var nameMap = ""
        var pwMap = ""
        var altezzaMap = ""
        var pesoMap = ""
        var etaMap = ""


        //per gli utenti
        /*database.child("User").child(idUserCorrente).get().addOnSuccessListener {

            val mappaProfilo = it.value as Map<String, String>

            //prove stampa
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firebase","${mappaProfilo}")
            nameMap = mappaProfilo.get("name").toString()
            pwMap = mappaProfilo.get("password").toString()
            altezzaMap = mappaProfilo.get("altezza").toString()
            pesoMap = mappaProfilo.get("peso").toString()
            etaMap = mappaProfilo.get("eta").toString()



            modificaNome.setText(nameMap)
            modificaPassword.setText(pwMap)
            //height.text = "$altezzaMap cm"
            //weight.text = "$pesoMap kg"



            //Creare un user per caricare i dati nell'oggetto User
            //User(var name:String, var email:String, var password: String, var altezza: String, var peso: String)

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }*/

        var cambiaUtente = DbCommunication.getUser()
        modificaNome.setText(cambiaUtente.name)
        modificaPassword.setText(cambiaUtente.password)


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