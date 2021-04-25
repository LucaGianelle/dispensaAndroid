package com.dispensa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class  MapActivity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mappa)

        //carichiamo il fragment della mappa

        mapFragment = supportFragmentManager.findFragmentById(R.id.framemap) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it


            /*aggiungere la richiesta dei permessi*/
            /*googleMap.isMyLocationEnabled = true*/

            //calcoliamo la posizione

            val location1 = LatLng(45.27,9.11)
            googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))

            //zoom della camera
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 10F))
        })
    }
}