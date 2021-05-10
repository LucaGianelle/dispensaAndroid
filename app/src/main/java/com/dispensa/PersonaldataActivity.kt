package com.dispensa

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.utils.ValueListenerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.personaldata.*

class PersonaldataActivity : AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth
    //private lateinit var firestore: FirebaseFirestore

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        database = Firebase.database.reference


        /*val user = FirebaseAuth.getInstance().currentUser
        val id : String = user.uid
        Log.e("TAG",id )
        database = FirebaseDatabase.getInstance()
        reference = database.getReference(id)*/



       /* user?.let {
            val name:String? = user.displayName
            if (name != null) {
                Log.e("TAG", name)
            }
            nickname.text = name.toString()
            val email:String? = user.email
            if (email != null) {
                Log.e("TAG", email)
            }
            emailPersonalData.text = email.toString()

        }*/





       /* val mFirebaseUser : FirebaseUser? = auth.currentUser
        val user_id = mFirebaseUser?.uid ?: String()

        firestore.collection("User").document(user_id).get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                if(task.result.exists()){
                    val name: String? = task.getResult().getString("name")
                    val mail:String? = task.getResult().getString("email")

                    nickname.text = name
                    email.text = mail

                }
            }
        }*/

    }
}