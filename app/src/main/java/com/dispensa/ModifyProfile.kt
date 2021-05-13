package com.dispensa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.modify_profile.*
import kotlinx.android.synthetic.main.modify_profile.buttonConferma

class ModifyProfile: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_profile)
        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val cambiaUtente = DbCommunication.getUser()

        val idUserCorrente : String = auth.currentUser.uid
        val altezzaUtente = cambiaUtente.altezza
        val pesoUtente = cambiaUtente.peso
        val etaUtente = cambiaUtente.eta
        val nameUtente = cambiaUtente.name
        val passwordUtente = cambiaUtente.password


        modificaPickerPeso.setMinValue(40);
        modificaPickerPeso.setMaxValue(120);
        modificaPickerPeso.wrapSelectorWheel = false

        modificaPickerAltezza.setMinValue(150);
        modificaPickerAltezza.setMaxValue(210);
        modificaPickerAltezza.wrapSelectorWheel = false

        modificaPickerEta.setMinValue(15);
        modificaPickerEta.setMaxValue(99);
        modificaPickerEta.wrapSelectorWheel = false


        modificaNome.setText(nameUtente)
        modificaPassword.setText(passwordUtente)
        modificaPickerAltezza.value = altezzaUtente.toInt()
        modificaPickerPeso.value = pesoUtente.toInt()
        modificaPickerEta.value = etaUtente.toInt()


        buttonConferma.setOnClickListener {
            var temp : String = modificaNome.text.toString()
            if(nameUtente.equals(temp) == false){
                database.child("User").child(idUserCorrente).child("name").setValue(temp)
            }

            temp = modificaPassword.text.toString()
            if(passwordUtente.equals(temp) == false){
                database.child("User").child(idUserCorrente).child("password").setValue(temp)
            }

            temp = modificaPickerAltezza.value.toString()
            if(!altezzaUtente.equals(temp)){
                database.child("User").child(idUserCorrente).child("altezza").setValue(temp)
            }

            temp = modificaPickerPeso.value.toString()
            if(!pesoUtente.equals(temp)){
                database.child("User").child(idUserCorrente).child("peso").setValue(temp)
            }

            temp = modificaPickerEta.value.toString()
            if(!etaUtente.equals(temp)){
                database.child("User").child(idUserCorrente).child("eta").setValue(temp)
            }

            val data = Intent (this, HomeActivity::class.java)
            startActivity(data)
        }
    }
}