package com.dispensa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_getdata.*

class PrendiDati: AppCompatActivity() {

    //singleton di java, cambiarlo in oggetto

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getdata)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Aliment")
        getData()
    }

    private fun getData() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Aliment>()
                for (data in snapshot.children) {
                    val model = data.getValue(Aliment::class.java)
                    list.add(model as Aliment)
                }
                if (list.size > 0) {
                    val adapter = DataAdapter(list)
                    recyclereview.adapter = adapter
                }
            }
        })
    }
}