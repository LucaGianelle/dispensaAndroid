package com.dispensa.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.dispensa.type.Aliment
import com.dispensa.type.AlimentoPersonale
import com.dispensa.type.User
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
    lateinit var miaDispensaMap: Map<String, String>
    var emptyStorage : Boolean = true
    var dataDatabase : String = ""

    fun getDbReference(){
        reference = Firebase.database.reference
    }

    fun createUser(name:String, email:String, password: String, altezza: String, peso: String, eta: String){

        utenteCorrente = User(name, email, password, altezza, peso, eta)
        Utility.kcal_giornaliere_calcolo(eta, peso)
        Utility.macronutrienti_calcolo()
    }

    fun getUser() : User {
        return utenteCorrente
    }

    fun setFoodStorage(){
        val database = Firebase.database.reference

        database.child("aliment").get().addOnSuccessListener {

           val result = it.value as ArrayList<Map<String,String>>
            createAliment(result)

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

     fun createAliment (allAliment: ArrayList<Map<String,String>>){
        val lunghezzaAliment = allAliment.size

        for(i in 0 until lunghezzaAliment){

            val tempMap = allAliment[i]

            val tempNameAliment = tempMap.get("nome").toString()
            val tempCalorieAliment = tempMap.get("calorie").toString()
            val tempQuantitaAliment = tempMap.get("quantità").toString()
            val tempValoriNutrizionali = tempMap.get("valoriNutrizionali") as Map<String,String>

            val tempProteineAliment = tempValoriNutrizionali.get("proteine").toString()
            val tempGrassiAliment = tempValoriNutrizionali.get("grassi").toString()
            val tempCarboidratiAliment = tempValoriNutrizionali.get("carboidrati").toString()

            //visualizzazione valori nutrizionali

            val tempAliment = Aliment(tempNameAliment, tempCalorieAliment, tempQuantitaAliment, tempProteineAliment, tempCarboidratiAliment, tempGrassiAliment)

            listaCibi.add(tempAliment)
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


    fun setDailyMap() {

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
                dataDatabase = result.get("dataSalvata").toString()

                dailyValuesMap =  mutableMapOf("calorie" to kcal,"carboidrati" to carbo, "grassi" to gras, "proteine" to prote)
                Utility.bLoop = false
            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun getNutritionalValues() : Map<String, Double> {
        return dailyValuesMap
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun confrontaData(): Boolean {
        var eraseData: Boolean = true
        val dataCorrente = LocalDate.now().toString()

        if (dataCorrente.equals(dataDatabase)) {
            eraseData = false
        }
        return eraseData
    }

    fun inserimentoAlimentoPersonale (quantAl: String){

        getDbReference()

        val prova : DatabaseReference = Firebase.database.reference
        prova.child("User").child(idUtente).child("Dispensa_personale").child(nomeAlimento).get().addOnSuccessListener {

            if(it.value != null){
                val result = it.value as Map<String, String>
                val temp : String = result.get("quantita").toString()
                val qt = temp.toInt()

                val finalQt = qt + quantAl.toInt()

                    reference.child("User").child(idUtente).child("Dispensa_personale").child(
                        nomeAlimento
                    ).child("quantita").setValue(finalQt)

            }else{

                val alimentoPersonale = AlimentoPersonale(nomeAlimento, quantAl)
                reference.child("User").child(idUtente).child("Dispensa_personale").child(
                    nomeAlimento
                ).setValue(alimentoPersonale)

            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

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

                    reference.child("User").child(idUtente).child("Dispensa_personale").child(
                        nomeAlimento
                    ).child("quantita").setValue(finalQt)

                }else{
                    reference.child("User").child(idUtente).child("Dispensa_personale").child(
                        nomeAlimento
                    ).child("quantita").setValue(qt)

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
        idUtente = idUser
    }

    fun setClickedAliment (aliment: Aliment){

        clickedAliment =  aliment
        nomeAlimento = clickedAliment.nameAliment
    }

    fun getClickedAliment (): Aliment {
        return clickedAliment
    }


    fun createPersonaList() : ArrayList<Aliment>{

        val myAlimentList : ArrayList<Aliment> = ArrayList()
        val size = listaCibi.size


        for (i in miaDispensaMap.entries){
            val myAliment  = i.value as Map<String,String>

            for(y in 0 until size){
                val temp = listaCibi[y].nameAliment
                if (temp.equals(myAliment.get("idAlimento"))) {

                    val tempName = listaCibi[y].nameAliment
                    val tempCal = listaCibi[y].calorie
                    val tempQuant = myAliment.get("quantita").toString()
                    val tempPro = listaCibi[y].proteine
                    val tempCarbo = listaCibi[y].carboidrati
                    val tempGras = listaCibi[y].grassi

                    val tempAliment = Aliment(tempName,tempCal,tempQuant,tempPro,tempCarbo,tempGras)


                    myAlimentList.add(tempAliment)
                    break
                }
            }
        }
        return myAlimentList

    }

    fun setMyaliment() {

        val prova : DatabaseReference = Firebase.database.reference
        prova.child("User").child(idUtente).child("Dispensa_personale").get().addOnSuccessListener {

            if(it.value != null){
                val result = it.value as Map<String, String>
                miaDispensaMap = result
                emptyStorage = false

            }else{
                emptyStorage = true

            }

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }
}