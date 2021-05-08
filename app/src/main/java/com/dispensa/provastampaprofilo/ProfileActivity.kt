package com.dispensa.provastampaprofilo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dispensa.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class ProfileActivity : AppCompatActivity() {
    private val TAG = "Profileactivity"
    private lateinit var mUser: User
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}