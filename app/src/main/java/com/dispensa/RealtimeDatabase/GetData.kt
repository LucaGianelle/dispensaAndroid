package com.dispensa.RealtimeDatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dispensa.R
import com.dispensa.DatabaseModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_getdata.*

class Getdata : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getdata)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("User")
        getData()
    }

    private fun getData() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var list = ArrayList<DatabaseModel>()
                for (data in snapshot.children) {
                    val model = data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)
                }
                if (list.size > 0) {
                    val adapter = DataAdapter(list)
                    recyclereview.adapter = adapter
                }
            }

        })
    }
}
