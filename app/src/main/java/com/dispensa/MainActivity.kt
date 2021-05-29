package com.dispensa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dispensa.utils.DbCommunication
import com.dispensa.utils.Utility
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var exit1 : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_app)

        database = Firebase.database.reference
        mFirebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(this, " LOADING APP... ", Toast.LENGTH_SHORT).show()

        if(Utility.exit){
            Utility.exit = false
            finish()
        }

        val mFirebaseUser : FirebaseUser? = mFirebaseAuth.currentUser
        if(mFirebaseUser != null){

            DbCommunication.setCurrentUser()
            DbCommunication.setDailyMap()


            thread(start = true){
                Thread.sleep(3000L)

                if(exit1){
                Utility.exit = false

            }else{
                val prv1 = Intent (this, HomeActivity::class.java)
                startActivity(prv1)
            }
            }

        }else{
            val prv2 = Intent (this, LoginActivity::class.java)
            startActivity(prv2)
        }
    }
}