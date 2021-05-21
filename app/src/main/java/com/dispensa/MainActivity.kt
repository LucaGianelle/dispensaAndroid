package com.dispensa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dispensa.videoSearchingBar.SearchBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_app)
        mFirebaseAuth = FirebaseAuth.getInstance()

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


}