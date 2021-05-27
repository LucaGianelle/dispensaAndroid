  package com.dispensa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.food_storage.*
import kotlin.random.Random

  class FoodStorageActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {
    private var exampleList = generateDummyList()
      private lateinit var displayList: ArrayList<Aliment>
    private val adapter = FoodAdapter(exampleList, this)
    lateinit var food_view : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_storage)

        displayList= generateDummyList()
        food_view = findViewById(R.id.recycler_view) as RecyclerView

        food_view.adapter = adapter
        food_view.layoutManager = LinearLayoutManager(this)
        food_view.setHasFixedSize(true)


        /*recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)*/

        //popup per inserire i cibi


       /* insertItemBtn.setOnClickListener{
            /*val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.popup_insert_aliment, null)
            window.contentView = view
            val TextView = view.findViewById<TextView>(R.id.quantitaView)
            val buttonYes = view.findViewById<Button>(R.id.buttonYes)
            buttonYes.setOnClickListener {
                window.dismiss()
            }
            window.showAsDropDown(insertItemBtn)*/
        }*/
    }

    fun insertItem(view: View) {
        exampleList = DbCommunication.getAliment()
        /*val index = Random.nextInt(8)

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

        //da togliere
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList [position]
        //clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
        DbCommunication.setNameAliment(clickedItem.nameAliment)


        //popup per inserire gli alimenti nella dispensa
        val dialog = AlimentDialogFragment()
        dialog.show(supportFragmentManager, "customDialog")
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        print("?????????????????????????????????????????>>>>>>>entra11111<<<<<<<<<<<?????????????????????????????")
        menuInflater.inflate(R.menu.researchbar,menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        print("?????????????????????????????????????????>>>>>>>entra<<<<<<<<<<<?????????????????????????????")
                        exampleList.clear()

                        val search = newText.toLowerCase()
                        displayList.forEach {
                            if(it.nameAliment.toLowerCase().contains(search)){
                                exampleList.add(it)
                            }
                        }
                        food_view.adapter?.notifyDataSetChanged()

                    }else{
                        exampleList.clear()
                        exampleList.addAll(displayList)
                        food_view.adapter?.notifyDataSetChanged()

                       /* displayList.clear()
                        displayList.addAll(exampleList)
                        food_view.adapter?.notifyDataSetChanged()*/
                    }

                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

      override fun onBackPressed (){
          val data = Intent (this, DispensaActivity::class.java)
          startActivity(data)
          finish()
      }


}