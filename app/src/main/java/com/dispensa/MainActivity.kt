package com.dispensa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.load_app.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    /*private lateinit var auth: FirebaseAuth*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.login)
        setContentView(R.layout.load_app)
       /* auth = FirebaseAuth.getInstance()*/

        val buttonStart = findViewById<Button>(R.id.startb)

        //vediamo se funziona --------------------------
        buttonStart.setOnClickListener{
        val intent = Intent(this, RegisterActivity::class.java)
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