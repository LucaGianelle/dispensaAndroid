  package com.dispensa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dispensa.adapter.FoodAdapter
import com.dispensa.fragment.AlimentDialogFragment
import com.dispensa.type.Aliment
import com.dispensa.utils.DbCommunication
import kotlin.concurrent.thread
import kotlin.random.Random

  class FoodStorageActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {
      private var exampleList:  ArrayList<Aliment> = generateDummyList()
      private lateinit var displayList: ArrayList<Aliment>
      private val adapter = FoodAdapter(exampleList, this)
      lateinit var food_view : RecyclerView

      /**
       * Inizializzazione della lista e generazione della lista fittizia. Qui sono contenuti la lista ed i bottoni con i quali interagirà l'utente
       */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_storage)

        displayList= generateDummyList()
        food_view = findViewById(R.id.recycler_view) as RecyclerView

        food_view.adapter = adapter
        food_view.layoutManager = LinearLayoutManager(this)
        food_view.setHasFixedSize(true)
    }

      /**
       * Funzione che, quando si clicca su un cibo, mostra la possibilità di aggiungerlo dalla dispensa selezionando anche la quantità
       */
    override fun onItemClick(position: Int) {

        val clickedItem = exampleList [position]
        adapter.notifyItemChanged(position)
        DbCommunication.setClickedAliment(clickedItem)
        val dialog = AlimentDialogFragment()
        dialog.show(supportFragmentManager, "customDialog")
    }

      /**
       * Funzione che si occupa di generare ed inizializzare una lista fittizia di oggetti, che verrà successivamente riempita con i cibi veri e propri
       */
    private fun generateDummyList(): ArrayList<Aliment> {

        val list = ArrayList<Aliment>()
        var templist: ArrayList<Aliment> = ArrayList()
        templist.clear()
        templist = DbCommunication.getAliment()
        println(""+templist)
        println(""+templist.size)
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
            list.add(item)
        }

        return list
    }

      /**
       * Funzione che permette il funzionamento della barra di ricerca, permettendo all'utente di cercare con più facilità i cibi semplicemente inserendo il nome nella barra
       */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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
                    }

                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

      /**
       * Funzione che rimanda alla schermata di Home se si preme il tasto "indietro" dello smartphone se si è arrivati dalla schermata di Home poiché la dispensa era vuota
       * Se si è arrivati dalla dipensa invece, il tasto riporta alla dispensa
       */
      override fun onBackPressed (){

          if(DbCommunication.emptyStorage){
              val home = Intent (this, HomeActivity::class.java)
              startActivity(home)
          }else{

              thread (start = true){
                  DbCommunication.setMyaliment()
                  Thread.sleep(500L)
                  val data = Intent (this, DispensaActivity::class.java)
                  startActivity(data)
              }
          }
      }


}