package com.dispensa.RealtimeDatabase

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_real_time.*

class RealTimeActivity : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_real_time)

        database= FirebaseDatabase.getInstance()
        reference=database.getReference("User")
    }

    private fun sendData(){
        var name = et_name.text.toString().trim()
        var email = et_email.text.toString().trim()

        if(name.isNotEmpty() && email.isNotEmpty()){

            var model=DatabaseModel(name, email)
            var id=reference.push().key

            //Qui si inviano i dati a firebase
            reference.child(id!!).setValue(model)


            //*************************************************************//
            // minuto 5:54
            //************************************************************//
        }else{
            Toast.makeText(applicationContext, "All field required",Toast.LENGTH_LONG).show()
        }
    }
}