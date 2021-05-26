package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dispensa.*

class DispensaActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {

    private var exampleList = generateDummyList()
    private lateinit var displayList: ArrayList<Aliment>
    private val adapter = FoodAdapter(exampleList, this)
    lateinit var food_view : RecyclerView

    private lateinit var database: DatabaseReference
    //private lateinit var result : Map<String,String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispensa)

        database = Firebase.database.reference

        val idUtente = DbCommunication.getId()
        //per il cibo
        //reference.child("User").child(idUtente).child("Dispensa_personale").setValue(alimentoPersonale)
        database.child("User").child(idUtente).child("Dispensa_personale").get().addOnSuccessListener {

          val result = it.value as Map<String,String>
            exampleList = DbCommunication.createPersonaList(result)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

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

        val list = ArrayList<Aliment>()
        val templist = DbCommunication.getAliment()
        val size = templist.size

        for (i in 0 until size) {

            val tempitem = templist[i]
            val tnameAliment = tempitem.nameAliment

            val tcalAliment = tempitem.calorie
            val tquantAliment = tempitem.quantita
            val tproteinAliment = tempitem.proteine
            val tcarboAliment = tempitem.carboidrati
            val tgrassiAliment = tempitem.grassi


            val item = Aliment(tnameAliment, tcalAliment, tquantAliment, tproteinAliment, tcarboAliment, tgrassiAliment)
            list += item
        }

        return list
    }
}