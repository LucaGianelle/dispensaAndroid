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

        var cambiaUtente = DbCommunication.getUser()
        val user = FirebaseAuth.getInstance().currentUser
        val id : String = user.uid

        var idUserCorrente : String = auth.currentUser.uid
        var altezzaMap = cambiaUtente.altezza
        var pesoMap = cambiaUtente.peso
        var etaMap = cambiaUtente.eta
        var nameMap = cambiaUtente.name
        var pwMap = cambiaUtente.password


        modificaPickerPeso.setMinValue(40);
        modificaPickerPeso.setMaxValue(120);
        modificaPickerPeso.wrapSelectorWheel = false

        modificaPickerAltezza.setMinValue(150);
        modificaPickerAltezza.setMaxValue(210);
        modificaPickerAltezza.wrapSelectorWheel = false

        modificaPickerEta.setMinValue(15);
        modificaPickerEta.setMaxValue(99);
        modificaPickerEta.wrapSelectorWheel = false


        modificaNome.setText(nameMap)
        modificaPassword.setText(pwMap)
        modificaPickerAltezza.value = altezzaMap.toInt()
        modificaPickerPeso.value = pesoMap.toInt()
        modificaPickerEta.value = etaMap.toInt()


        buttonConferma.setOnClickListener {
            var temp : String = modificaNome.text.toString()
            if(nameMap.equals(temp) == false){
                database.child("User").child(idUserCorrente).child("name").setValue(temp)
            }

            temp = modificaPassword.text.toString()
            if(pwMap.equals(temp) == false){
                database.child("User").child(idUserCorrente).child("password").setValue(temp)
            }

            temp = modificaPickerAltezza.value.toString()
            if(!altezzaMap.equals(temp)){
                database.child("User").child(idUserCorrente).child("altezza").setValue(temp)
            }

            temp = modificaPickerPeso.value.toString()
            if(!pesoMap.equals(temp)){
                database.child("User").child(idUserCorrente).child("peso").setValue(temp)
            }

            temp = modificaPickerEta.value.toString()
            if(!etaMap.equals(temp)){
                database.child("User").child(idUserCorrente).child("eta").setValue(temp)
            }

            val data = Intent (this, HomeActivity::class.java)
            startActivity(data)
        }
    }
}