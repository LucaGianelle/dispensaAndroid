package com.dispensa

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object DbCommunication {

    private lateinit var database: DatabaseReference
    private lateinit var reference: DatabaseReference

    fun getDbReference(){
        database = Firebase.database.reference
    }

    fun getDbInstance(){

    }
}