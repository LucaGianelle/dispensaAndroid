package com.dispensa

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)


        buttonDatiPersonali.setOnClickListener {
            val data = Intent (this, PersonaldataActivity::class.java)
            startActivity(data)
        }

        buttonValoriNutrizionali.setOnClickListener {
            val valNut = Intent (this, NutritionalValuesActivity::class.java)
            startActivity(valNut)
        }

        /*buttonMappa.setOnClickListener {

            /*val latitude: Double = location.getLatitude()
            val longitude: Double = location.getLongitude()

            val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)*/

            /*
            *
            *
            *
            */

           /* val intent = Intent(Intent.ACTION_VIEW,
                    /*Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"))*/
                    Uri.parse("google.navigation:q=an+address+city"))
            startActivity(intent)*/
        }*/
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

    fun callmap(view: View) {

    }


}