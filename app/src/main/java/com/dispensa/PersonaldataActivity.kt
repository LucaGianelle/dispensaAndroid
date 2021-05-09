package com.dispensa

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.utils.ValueListenerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.personaldata.*

class PersonaldataActivity : AppCompatActivity() {

   /* private lateinit var nameValue: String
    private lateinit var emailValue: String
    private var weightValue: Int = 0
    private var heightValue: Int = 0*/

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        var user_id:String = auth.currentUser.uid

        firestore.collection("User").document(user_id).get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                if(task.result.exists()){
                    var name: String? = task.getResult().getString("name")
                    var mail:String? = task.getResult().getString("email")

                    nickname.text = name
                    email.text = mail

                }
            }
        }

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