package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dispensa.adapter.FoodAdapter
import com.dispensa.type.Aliment
import com.dispensa.utils.DbCommunication
import kotlin.random.Random

class DispensaActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {

    private var exampleList:ArrayList<Aliment> = generateDummyList()
    private lateinit var displayList: ArrayList<Aliment>
    private val adapter = FoodAdapter(exampleList, this)
    lateinit var food_view : RecyclerView

    /**
     * Inizializzazione della lista e generazione della lista fittizia. Qui sono contenuti la lista ed i bottoni con i quali interagirà l'utente
     * Se il'utente clicca sul bottone di aggiunta cibo, verrà reindirizzato alla schermata dove si possono selezionare tutti i cibi disponibili per aggiungerli alla dispensa (FoodStorageActivity)
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispensa)

        displayList= generateDummyList()
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

    /**
     * Funzione che, quando si clicca su un cibo, mostra la possibilità di rimuoverlo dalla dispensa selezionando anche la quantità
     */
    override fun onItemClick(position: Int) {

        val clickedItem = exampleList [position]
        adapter.notifyItemChanged(position)
        DbCommunication.setClickedAliment(clickedItem)

        val addal = Intent (this, Prendi_alimentoActivity::class.java)
        startActivity(addal)
    }

    /**
     * Funzione che si occupa di generare ed inizializzare una lista fittizia di oggetti, che verrà successivamente riempita con i cibi scelti dall'utente
     */
    private fun generateDummyList(): ArrayList<Aliment> {

        val list =  DbCommunication.createPersonaList()
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
     * Funzione che rimanda alla schermata di Home se si preme il tasto "indietro" dello smartphone
     */
    override fun onBackPressed (){
        val data = Intent (this, HomeActivity::class.java)
        startActivity(data)
    }

}