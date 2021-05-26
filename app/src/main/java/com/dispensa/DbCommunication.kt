package com.dispensa

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate


object DbCommunication {

    private lateinit var reference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    private lateinit var utenteCorrente : User
    private var listaCibi : ArrayList<Aliment> = generateList()
    private val listaDailyValues: ArrayList<String> = ArrayList()

    private var idUtente : String = ""
    private var nomeAlimento : String = ""

    private lateinit var dailyValuesMap : Map <String, Double>

    fun getDbReference(){
        reference = Firebase.database.reference
    }

    fun getDbInstance(){
        reference = database.getReference()
    }

    fun createUser(name:String, email:String, password: String, altezza: String, peso: String, eta: String){

        utenteCorrente = User(name, email, password, altezza, peso, eta)
        Utility.kcal_giornaliere_calcolo(eta, peso)
        Utility.macronutrienti_calcolo()
    }

    fun getUser() : User {
        return utenteCorrente
    }

     fun createAliment (allAliment: ArrayList<Map<String,String>>){
        val lunghezzaAliment = allAliment.size
         print("&&&&&&&&&&&&&&&&&&&&&&&&&&     questa è il numero degli alimenti presi dal db " +lunghezzaAliment+ "\n")
        var listaCibi = ArrayList<Aliment>()

        for(i in 0 until lunghezzaAliment){

            val tempMap = allAliment[i]
            println("============================$tempMap===============================")

            val tempNameAliment = tempMap.get("nome").toString()
            val tempCalorieAliment = tempMap.get("calorie").toString()
            val tempQuantitaAliment = tempMap.get("quantità").toString()
            val tempValoriNutrizionali = tempMap.get("valoriNutrizionali") as Map<String,String>

            val tempProteineAliment = tempValoriNutrizionali.get("proteine").toString()
            val tempGrassiAliment = tempValoriNutrizionali.get("grassi").toString()
            val tempCarboidratiAliment = tempValoriNutrizionali.get("carboidrati").toString()

            //visualizzazione valori nutrizionali

            val tempAliment = Aliment(tempNameAliment, tempCalorieAliment, tempQuantitaAliment, tempProteineAliment, tempGrassiAliment, tempCarboidratiAliment)

            this.listaCibi.add(tempAliment)
        }
    }

    fun getAliment() : ArrayList<Aliment> {
        listaCibi
        return listaCibi
    }



    private fun generateList(): ArrayList<Aliment> {

        val list = ArrayList<Aliment>()



        return list
    }


    fun setNutritionalValues(inizio: Map<String, String>) {

        var carboidratiUtente =  inizio.get("carboDaily")
        if (carboidratiUtente != null) {
            listaDailyValues.add(carboidratiUtente)
        }
        var proteineUtente = inizio.get("proteinDaily")
        if (proteineUtente != null) {
            listaDailyValues.add(proteineUtente)
        }
        var grassiUtente = inizio.get("grassiDaily")
        if (grassiUtente != null) {
            listaDailyValues.add(grassiUtente)
        }
        var calorieUtente = inizio.get("calDaily")
        if (calorieUtente != null) {
            listaDailyValues.add(calorieUtente)
        }
    }

    fun getDailyMap() : Map<String, Double>{

        dailyValuesMap =  mutableMapOf("calorie" to 1500.0,"carboidrati" to 150.0, "grassi" to 10.0, "proteine" to 70.0)
        return dailyValuesMap
    }

    fun getNutritionalValues() : ArrayList<String> {
        return listaDailyValues
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun confrontaData(inizio: Map<String, String>): Boolean {
        var eraseData: Boolean = true
        val dData: String = inizio.get("dataSalvata").toString()
        val dataCorrente = LocalDate.now()

        if (dataCorrente.equals(dData)) {
            eraseData = false
            //azzerare i valori nel database e cambiare la data
        }
        return eraseData
    }

    fun calcoloMacroGiornalieri(){
        //calcolare le calorie giornaliere dell'utente
    }

    fun inserimentoAlimentoPersonale (quantAl: String){

        reference = Firebase.database.reference
        reference = database.getReference()
        val alimentoPersonale = AlimentoPersonale(quantAl, nomeAlimento)
        reference.child("User").child(idUtente).child("Dispensa_personale").setValue(alimentoPersonale)
    }

    fun setId (idUser: String){
        this.idUtente = idUser
    }

    fun getId (): String {
        return idUtente
    }

    fun setNameAliment (nameAliment:String){
        this.nomeAlimento = nameAliment
    }

    fun getNameAliment (): String {
        return nomeAlimento
    }


    fun createPersonaList(mieAliment : Map<String,String>) : ArrayList<Aliment>{

        val myAlimentList : ArrayList<Aliment> = ArrayList()
        //class AlimentoPersonale (val idAlimento: String, val quantita: String){
        val ricercaid : String = mieAliment.get("idAlimento").toString()
        val size = listaCibi.size

        for (i in 0 until size){
            val temp = listaCibi[i].nameAliment
            if (temp.equals(ricercaid)){
                myAlimentList.add(listaCibi[i])
            }
        }

        return myAlimentList

    }
}