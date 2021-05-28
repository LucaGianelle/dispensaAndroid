package com.dispensa

import android.os.Build
import android.util.Log
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
    private lateinit var clickedAliment : Aliment

    private lateinit var dailyValuesMap : Map <String, Double>
    private lateinit var miaDispensaMap: Map<String, String>
    var emptyStorage : Boolean = true

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

            val tempAliment = Aliment(tempNameAliment, tempCalorieAliment, tempQuantitaAliment, tempProteineAliment, tempCarboidratiAliment, tempGrassiAliment)

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

        val carboidratiUtente =  inizio.get("carboDaily")
        if (carboidratiUtente != null) {
            listaDailyValues.add(carboidratiUtente)
        }
        val proteineUtente = inizio.get("proteinDaily")
        if (proteineUtente != null) {
            listaDailyValues.add(proteineUtente)
        }
        val grassiUtente = inizio.get("grassiDaily")
        if (grassiUtente != null) {
            listaDailyValues.add(grassiUtente)
        }
        val calorieUtente = inizio.get("calDaily")
        if (calorieUtente != null) {
            listaDailyValues.add(calorieUtente)
        }
    }

    fun getDailyMap() {

        getDbReference()
        val prova : DatabaseReference = Firebase.database.reference

        var kcal : Double = 0.0
        var prote : Double = 0.0
        var gras : Double = 0.0
        var carbo : Double = 0.0



        prova.child("User").child(idUtente).child("Valori_giornalieri").get().addOnSuccessListener {

            if(it.value != null){
                val result = it.value as Map<String, String>
                kcal = result.get("calDaily")!!.toDouble()
                prote = result.get("proteinDaily")!!.toDouble()
                gras  = result.get("grassiDaily")!!.toDouble()
                carbo  = result.get("carboDaily")!!.toDouble()

                dailyValuesMap =  mutableMapOf("calorie" to kcal,"carboidrati" to carbo, "grassi" to gras, "proteine" to prote)
            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }



    }

    fun getNutritionalValues() : Map<String, Double> {
        return dailyValuesMap
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

        getDbReference()

        val alimentoPersonale = AlimentoPersonale(nomeAlimento, quantAl)
        reference.child("User").child(idUtente).child("Dispensa_personale").child(nomeAlimento).setValue(alimentoPersonale)
        var temp = setMyaliment()
    }

    fun riduzioneAlimentoPersonale (quantAl: String){

        getDbReference()
        var finalQt : Int = 0
        var qt: Int = 0

        val prova : DatabaseReference = Firebase.database.reference
        prova.child("User").child(idUtente).child("Dispensa_personale").child(nomeAlimento).get().addOnSuccessListener {

            if(it.value != null){
                val result = it.value as Map<String, String>
                val temp : String = result.get("quantita").toString()
                qt = temp.toInt()

                finalQt = qt - quantAl.toInt()

                if(finalQt >= 0){

                    reference.child("User").child(idUtente).child("Dispensa_personale").child(nomeAlimento).child("quantita").setValue(finalQt)

                }else{
                    /*messaggio per quantità errata rimossa
                     Toast.makeText(this, "Non puoi togliere $finalQt grammi",Toast.LENGTH_SHORT).show()*/
                    reference.child("User").child(idUtente).child("Dispensa_personale").child(nomeAlimento).child("quantita").setValue(qt)

                }

            }else{
                emptyStorage = true

            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    fun addDailyValues(qt : Int){
        getDbReference()
        val prova : DatabaseReference = Firebase.database.reference

        var kcla : Double = clickedAliment.calorie.toDouble()
        var prote : Double = clickedAliment.proteine.toDouble()
        var gras : Double = clickedAliment.grassi.toDouble()
        var carbo : Double = clickedAliment.carboidrati.toDouble()

        kcla = kcla*(qt / 100)
        prote = prote*(qt / 100)
        gras = gras*(qt / 100)
        carbo = carbo*(qt / 100)


        prova.child("User").child(idUtente).child("Valori_giornalieri").get().addOnSuccessListener {

            if(it.value != null){
                val result = it.value as Map<String, String>
                var tempkcal : Double = result.get("calDaily")!!.toDouble()
                var tempprote : Double = result.get("proteinDaily")!!.toDouble()
                var tempgras : Double = result.get("grassiDaily")!!.toDouble()
                var tempcarbo : Double = result.get("carboDaily")!!.toDouble()

                tempkcal += kcla
                tempprote += prote
                tempgras += gras
                tempcarbo += carbo


                    reference.child("User").child(idUtente).child("Valori_giornalieri").child("calDaily").setValue(tempkcal.toString())
                    reference.child("User").child(idUtente).child("Valori_giornalieri").child("proteinDaily").setValue(tempprote.toString())
                    reference.child("User").child(idUtente).child("Valori_giornalieri").child("grassiDaily").setValue(tempgras.toString())
                    reference.child("User").child(idUtente).child("Valori_giornalieri").child("carboDaily").setValue(tempcarbo.toString())
            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun setId (idUser: String){
        this.idUtente = idUser
    }

    fun getId (): String {
        return idUtente
    }

    fun setClickedAliment (aliment: Aliment){

        this.clickedAliment =  aliment
        this.nomeAlimento = clickedAliment.nameAliment
    }

    fun getNameAliment (): String {
        return nomeAlimento
    }


    fun createPersonaList() : ArrayList<Aliment>{

        val myAlimentList : ArrayList<Aliment> = ArrayList()
        val size = listaCibi.size


        for (i in miaDispensaMap.entries){
            val myAliment  = i.value as Map<String,String>

            for(y in 0 until size){
                val temp = listaCibi[y].nameAliment
                println("-------------------> " + temp+ " --------------------------- >" + myAliment)
                if (temp.equals(myAliment.get("idAlimento"))) {
                    println("-------------------> " + temp+ " --------------------------- >" + myAliment)
                    //Aliment(var nameAliment: String, var calorie: String, var quantita: String, var proteine: String, var carboidrati: String, var grassi: String)
                    val tempName = listaCibi[y].nameAliment
                    val tempCal = listaCibi[y].calorie
                    val tempQuant = myAliment.get("quantita").toString()
                    val tempPro = listaCibi[y].proteine
                    val tempCarbo = listaCibi[y].carboidrati
                    val tempGras = listaCibi[y].grassi

                    val tempAliment :Aliment = Aliment(tempName,tempCal,tempQuant,tempPro,tempCarbo,tempGras)

                    println("=========================00000000000000000000000000000=====================" + tempAliment)

                    myAlimentList.add(tempAliment)
                    break
                }
            }
        }

        println(myAlimentList)
        return myAlimentList

    }

    fun setMyaliment(/*mappa : Map<String,String>*/) {


        val prova : DatabaseReference = Firebase.database.reference
        prova.child("User").child(idUtente).child("Dispensa_personale").get().addOnSuccessListener {

            if(it.value != null){
                val result = it.value as Map<String, String>
                miaDispensaMap = result
                emptyStorage = false
                println("__________________------------------ Ho caricato la dispensa personale")
                println("__________________------------------ "+emptyStorage)

            }else{
                emptyStorage = true

            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }



    }
}