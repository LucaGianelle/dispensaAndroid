package com.dispensa

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object DbCommunication {

    private lateinit var reference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    fun getDbReference(){
        reference = Firebase.database.reference
    }

    fun getDbInstance(){
        reference = database.getReference()
    }


}