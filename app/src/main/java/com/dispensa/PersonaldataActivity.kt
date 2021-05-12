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

    private lateinit var auth: FirebaseAuth
    //private lateinit var firestore: FirebaseFirestore

    private lateinit var database: DatabaseReference
    //private lateinit var mapArray : Map<String, Map<String, String>>
    //Map<String, Map<String,String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personaldata)

        database = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser
        val id : String = user.uid

       database.child("User").child(id).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        //per il cibo
        database.child("aliment").get().addOnSuccessListener {
            val result = it.value as ArrayList<String>
                    // Map<String,Map<String, String>>
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firbase", "${result}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        var idprova : String = auth.currentUser.uid
        println("=========================== " + idprova)

        //per gli utenti
        database.child("User").child(idprova).get().addOnSuccessListener {

            val mappaProfilo = it.value as Map<String, String>
            Log.i("firebase", "Got value ${it.value}")
            Log.i("firebase","${mappaProfilo}")

            val nameMap: String = mappaProfilo.get("name").toString()
            val emailMap : String = mappaProfilo.get("email").toString()
            var pwMap : String = mappaProfilo.get("password").toString()
            val altezzaMap : String = mappaProfilo.get("altezza").toString()
            val pesoMap : String = mappaProfilo.get("peso").toString()

            nickname.text = nameMap
            emailPersonalData.text = emailMap
            height.text = altezzaMap + " cm"
            weight.text = pesoMap + " kg"

            //Creare un user per caricare i dati nell'oggetto User
            //User(var name:String, var email:String, var password: String, var altezza: String, var peso: String)

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }

        //Gienelle's loop for -----------------------------------------------
        /*val arrayprova : ArrayList<String>
        for ((id, x) in mappa  ) {

            val mapProva = x
            for((x, generalità) in mapProva){
                println("===============" + generalità)
            }
        }*/


        //val nameprova : String = mapArray?.get(id).toString()
        //Log.d("TAG", "$nameprova prova del nick")
        //Queste cose non si possono fare
        /*var actName = userarray.get(2)
        nickname.text = actName

        var actMail = userarray.get(1)
        emailPersonalData.text = actMail

        var actPeso = userarray.get(4)
        weight.text = actPeso

        var actAltezza = userarray.get(0)
        height.text = actAltezza*/

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