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
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.dispensa.videoSearchingBar.SearchBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_app)
        mFirebaseAuth = FirebaseAuth.getInstance()
        checkData()

    }

    override fun onStart() {
        super.onStart()

        val mFirebaseUser : FirebaseUser? = mFirebaseAuth.currentUser
        if(mFirebaseUser != null){
            val prv1 = Intent (this, HomeActivity::class.java)
            startActivity(prv1)
        }else{

            val prv2 = Intent (this, LoginActivity::class.java)
            startActivity(prv2)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun checkData() {
        val currentDate = LocalDate.now()

        // default format is DateTimeFormatter.ISO_DATE_TIME
        println(currentDate.format(DateTimeFormatter.BASIC_ISO_DATE))


        /*if(currentDate > savedDate){
            //azzera proteine giornalieri
            //azzera carboidrati giornalieri
            //azzera grassi giornalieri
        }*/
    }

}