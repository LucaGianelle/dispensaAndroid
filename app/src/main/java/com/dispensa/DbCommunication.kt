package com.dispensa

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DbCommunication {

    private lateinit var database: DatabaseReference


    fun getDbReference(){
        database = Firebase.database.reference
    }
}