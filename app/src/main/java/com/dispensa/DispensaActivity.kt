package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dispensa.*
import kotlin.random.Random

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

        val clickedItem = exampleList [position]
        adapter.notifyItemChanged(position)
        DbCommunication.setNameAliment(clickedItem.nameAliment)


        //popup per inserire gli alimenti nella dispensa
       /* val dialog = AlimentDialogFragment()
        dialog.show(supportFragmentManager, "customDialog")*/
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    private fun generateDummyList(): ArrayList<Aliment> {

        var list =  DbCommunication.createPersonaList()

        println(" $/////////////////////////(((((((())))))))))))))))=============== "+ list)
        return list
    }
}