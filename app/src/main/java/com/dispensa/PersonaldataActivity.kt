package com.dispensa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.utils.DbCommunication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.personaldata.*

class PersonaldataActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    var myAuth = FirebaseAuth.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val dataUser = DbCommunication.getUser()

        nickname.text = dataUser.name
        emailPersonalData.text = dataUser.email
        height.text = "${dataUser.altezza} cm"
        weight.text = "${dataUser.peso} kg"
        age.text = "${dataUser.eta} anni"

        buttonModifica.setOnClickListener {
            val data = Intent (this, ModifyProfile::class.java)
            startActivity(data)
        }

        btnLogout.setOnClickListener {

            Toast.makeText(this,"Logout...", Toast.LENGTH_LONG).show()
            signOut()
            val back = Intent(this, LoginActivity::class.java)
            startActivity(back)
        }
    }

    private fun signOut() {
        myAuth.signOut()
    }

    override fun onBackPressed (){
        val prv1 = Intent (this, HomeActivity::class.java)
        startActivity(prv1)
    }

}