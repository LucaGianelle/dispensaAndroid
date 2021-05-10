package com.dispensa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DispensaActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var result : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispensa)

        database = Firebase.database.reference

        //per il cibo
        database.child("aliment").get().addOnSuccessListener {

            result = it.value as ArrayList<String>
            //val result = it.value as ArrayList<String>
            // Map<String,Map<String, String>>
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firbase", "${result}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }
}