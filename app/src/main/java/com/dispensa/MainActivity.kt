package com.dispensa

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.os.HandlerCompat.postDelayed
import com.dispensa.videoSearchingBar.SearchBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var exit1 : Boolean = Utility.exitOk()


    override fun onCreate(savedInstanceState: Bundle?) {
        exit1 = Utility.exitOk()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_app)

        database = Firebase.database.reference
        mFirebaseAuth = FirebaseAuth.getInstance()




    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(this, " LOADING APP... ", Toast.LENGTH_LONG).show()

        if(Utility.exit){
            Utility.exit = false
            finish()
        }

        val mFirebaseUser : FirebaseUser? = mFirebaseAuth.currentUser
        if(mFirebaseUser != null){

            val idUtente : String = mFirebaseAuth.currentUser.uid
            DbCommunication.setId(idUtente)

            //per gli utenti
            database.child("User").child(idUtente).get().addOnSuccessListener {

                val mappaProfilo = it.value as Map<String, String>

                Log.i("firebase", "Got value ${it.value}")
                Log.i("firebase","${mappaProfilo}")

                val nameMap: String = mappaProfilo.get("name").toString()
                val emailMap : String = mappaProfilo.get("email").toString()
                val pwMap : String = mappaProfilo.get("password").toString()
                val altezzaMap : String = mappaProfilo.get("altezza").toString()
                val pesoMap : String = mappaProfilo.get("peso").toString()
                val etaMap : String = mappaProfilo.get("eta").toString()

                DbCommunication.createUser(nameMap,emailMap,pwMap,altezzaMap,pesoMap,etaMap)

            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }

            DbCommunication.setDailyMap()


            thread(start = true){
                logOK().postDelayed(Runnable {
                    //anything you want to start after 3s
                }, 3000)
            }

        }else{

            val prv2 = Intent (this, LoginActivity::class.java)
            startActivity(prv2)
        }
    }

    fun logOK(){
        if(exit1){
            Utility.exit = false
            finish()
        }else{
            val prv1 = Intent (this, HomeActivity::class.java)
            startActivity(prv1)
        }
    }

}