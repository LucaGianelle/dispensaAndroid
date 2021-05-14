package com.dispensa

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object DbCommunication {

    private lateinit var reference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var utenteCorrente : User
    private lateinit var listaCibi : ArrayList<Aliment>

    fun getDbReference(){
        reference = Firebase.database.reference
    }

    fun getDbInstance(){
        reference = database.getReference()
    }

    fun createUser(name:String, email:String, password: String, altezza: String, peso: String, eta: String){

        utenteCorrente = User(name, email, password, altezza, peso, eta)
    }

    fun getUser() : User {
        return utenteCorrente
    }

     fun stampaAliment (allAliment: ArrayList<Map<String,String>>){
        val lunghezzaAliment = allAliment.size
        var listaCibi = ArrayList<Aliment>()

        for(i in 0 until lunghezzaAliment){

            val tempMap = allAliment[i]
            println("============================$tempMap===============================")

            val tempNameAliment = tempMap.get("nome").toString()
            val tempCalorieAliment = tempMap.get("calorie").toString()
            val tempQuantitaAliment = tempMap.get("quantita").toString()
            //valori nutrizionali

            var tempAliment = Aliment(tempNameAliment, tempCalorieAliment, tempQuantitaAliment, 10.0,10.0,10.0)

            this.listaCibi.add(tempAliment)
        }
    }


}