package com.dispensa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.utils.ValueListenerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.personaldata.*

class PersonaldataActivity : AppCompatActivity() {

   /* private lateinit var nameValue: String
    private lateinit var emailValue: String
    private var weightValue: Int = 0
    private var heightValue: Int = 0*/


    private val TAG = "Profileactivity"
    private lateinit var mUser: User
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference


        fun currentUserReference(): DatabaseReference =
                mDatabase.child("user").child(mAuth.currentUser!!.uid)

        currentUserReference().addListenerForSingleValueEvent(
                ValueListenerAdapter{
                    //mUser = it.asUser()!!
                    nickname.setText(mUser.name)

                }
        )
    }

   /* fun readProfile(nome: String, mail: String, peso: Int, altezza: Int){
        nameValue = nome
        emailValue = mail
        weightValue = peso
        heightValue = altezza
    }

    fun writeProfile(){

        val pes = "$weightValue kg"
        val alt = "$heightValue cm"

        nickname.text = nameValue
        email.text = emailValue
        weight.text = pes
        height.text = alt
    }*/
}