package com.dispensa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        buttonMappa.setOnClickListener {

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

            val intent = Intent(Intent.ACTION_VIEW,
                    /*Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"))*/
                    Uri.parse("google.navigation:q=an+address+city"))
            startActivity(intent)
        }
    }


}