package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dispensa.*

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
            Log.i("firebase", "${result}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        val btnDisp = findViewById<Button>(R.id.buttonAggiuntaCibo)

        btnDisp.setOnClickListener {
            val data = Intent (this, FoodStorageActivity::class.java)
            startActivity(data)
        }
    }
}