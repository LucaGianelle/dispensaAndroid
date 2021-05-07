package com.dispensa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.dispensa.RealtimeDatabase.RealTimeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.load_app.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    /*private lateinit var auth: FirebaseAuth*/

    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.login)
        setContentView(R.layout.load_app)
       /* auth = FirebaseAuth.getInstance()*/
        mFirebaseAuth = FirebaseAuth.getInstance()

        val buttonStart = findViewById<Button>(R.id.startb)



        //vediamo se funziona --------------------------
        buttonStart.setOnClickListener{
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        }
        //-------------------------------------------------------


       /* buttonAccesso.setOnClickListener {
            val acc = Intent (this, HomeActivity::class.java)
            startActivity(acc)
        }
        button.setOnClickListener {
            val reg = Intent (this, RegisterActivity::class.java)
            startActivity(reg)
        }*/

        /*button.setOnClickListener {
            val data = Intent (this, PersonaldataActivity::class.java)
            startActivity(data)
        }*/

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

    /*public override fun onStart() {
        super.onStart()

        //vediamo se funziona --------------------------
        val log = Intent(this, LoginActivity::class.java)
        startActivity(log)
        //-------------------------------------------------------


        setContentView(R.layout.login)
            //controlla se l'utente è registrato, (valore non nullo) e aggiorna l'interfaccia di conseguenza
        val currentUser = auth.currentUser
        updateUI (currentUser)
    }*/

    fun updateUI(currentUser: FirebaseUser?) {

    }

}