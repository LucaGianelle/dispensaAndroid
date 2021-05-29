package com.dispensa

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dispensa.utils.DbCommunication
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.home.*
import java.time.LocalDate

class HomeActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var result : ArrayList<Map<String,String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        //=========================================================================================================================

        val idUtente : String = auth.currentUser.uid
        DbCommunication.setId(idUtente)

        DbCommunication.setCurrentUser()
        DbCommunication.setFoodStorage()
        DbCommunication.setMyaliment()


        // reset data e valori
        val cancellaDailyValues : Boolean = DbCommunication.confrontaData()
        if (cancellaDailyValues) {
            val dataCorrente = LocalDate.now().toString()
            database.child("User").child(idUtente).child("Valori_giornalieri").child("calDaily").setValue("0")
            database.child("User").child(idUtente).child("Valori_giornalieri").child("carboDaily").setValue("0")
            database.child("User").child(idUtente).child("Valori_giornalieri").child("proteinDaily").setValue("0")
            database.child("User").child(idUtente).child("Valori_giornalieri").child("grassiDaily").setValue("0")
            database.child("User").child(idUtente).child("Valori_giornalieri").child("dataSalvata").setValue(dataCorrente)
        }

        DbCommunication.setDailyMap()

        //=========================================================================================================================


        val datib = findViewById<Button>(R.id.buttonDatiPersonali)
        val valnb = findViewById<Button>(R.id.buttonValoriNutrizionali)
        val foodStorageB = findViewById<Button>(R.id.buttonDispensa)

        foodStorageB.setOnClickListener {
            if(DbCommunication.emptyStorage){
                val data = Intent (this, FoodStorageActivity::class.java)
                startActivity(data)
            }else{
                val data = Intent (this, DispensaActivity::class.java)
                startActivity(data)
            }
        }

        datib.setOnClickListener {
            val data = Intent (this, PersonaldataActivity::class.java)
            startActivity(data)
        }

        valnb.setOnClickListener {
            val valNut = Intent (this, NutritionalValuesActivity::class.java)
            startActivity(valNut)
        }


        buttonMappa.setOnClickListener{
            this.openMaps(this,this.applicationContext)
        }

    }


 override fun onBackPressed (){
     Toast.makeText(this, "Tasto disabilitato qui", Toast.LENGTH_SHORT).show()
}

fun openMaps (activity: Activity, context: Context) {
    val REQUEST_POSITION_PERMISSION_ID = 1
    lateinit var fusedLocationClient: FusedLocationProviderClient

    val permissionAccessCoarseLocationApproved = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    if(permissionAccessCoarseLocationApproved){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(context.getString(R.string.query_location))
            )
            intent.setPackage("com.google.android.apps.maps")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }else{
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_POSITION_PERMISSION_ID
        )
    }
}

}

