package com.dispensa

import android.content.Context
import android.widget.Toast
import com.dispensa.User
import com.google.firebase.database.DataSnapshot
import java.time.Duration

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this,text,duration).show()
}

/*fun DataSnapshot.asUser(): User? =
        getValue(User::class.java)?.copy(uid = key)*/