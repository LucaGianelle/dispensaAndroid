package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dispensa.*

class DispensaActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {

    private var exampleList:ArrayList<Aliment> = generateDummyList()
    private lateinit var displayList: ArrayList<Aliment>
    private val adapter = FoodAdapter(exampleList, this)
    lateinit var food_view : RecyclerView

    private lateinit var database: DatabaseReference
    //private lateinit var result : Map<String,String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispensa)


        food_view = findViewById(R.id.recycler_view) as RecyclerView

        food_view.adapter = adapter
        food_view.layoutManager = LinearLayoutManager(this)
        food_view.setHasFixedSize(true)


        val btnDisp = findViewById<Button>(R.id.buttonAggiuntaCibo)

        btnDisp.setOnClickListener {
            val data = Intent (this, FoodStorageActivity::class.java)
            startActivity(data)
        }
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    private fun generateDummyList(): ArrayList<Aliment> {

        var list =  DbCommunication.createPersonaList()

        println(" $/////////////////////////(((((((())))))))))))))))=============== "+ list)
        return list
    }
}