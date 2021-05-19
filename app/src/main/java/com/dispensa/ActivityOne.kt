package com.dispensa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_one.*
import kotlin.random.Random

class ActivityOne : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private var exampleList = generateDummyList(/*500*/)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        //=*=*=*=**=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=**=*=*= >> per il cibo

        /*database = Firebase.database.reference

        database.child("aliment").get().addOnSuccessListener {

            result = it.value as ArrayList<Map<String,String>>

            DbCommunication.createAliment(result)

           /*Log.i("firebase", "Got value ${it.value}")
            Log.i("firebase", "${result}")*/
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }*/

    }

    fun insertItem(view: View) {
        exampleList = DbCommunication.getAliment()
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+exampleList.size)
        /*val index = Random.nextInt(8)

        //dove verranno inseriti i dati dal db
      val newItem = ExampleItem(

            R.drawable.ic_android_black_24dp,
            "New item at position $index",
            "Line 2"
        )


        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)*/
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList [position]
        //clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(/*size: Int*/): ArrayList<Aliment> {

        val list = ArrayList<Aliment>()
        val templist = DbCommunication.getAliment()
        val size = templist.size
        print("((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))" + size)

        for (i in 0 until size) {

            val tempitem = templist[i]
            val tnameAliment = tempitem.nameAliment
            val tcalAliment = tempitem.calorie
            val tquantAliment = tempitem.quantita
           /* val tproteinAliment = tempitem.proteine
            val tcarboAliment = tempitem.carboidrati
            val tgrassiAliment = tempitem.grassi*/


            val item = Aliment(tnameAliment, tcalAliment, tquantAliment)
            list += item
        }

        print("((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))" + list.size)
        return list
    }



}