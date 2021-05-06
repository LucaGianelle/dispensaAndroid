package com.dispensa

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        buttonAccesso.setOnClickListener {
            val acc = Intent (this, HomeActivity::class.java)
            startActivity(acc)
        }
        button.setOnClickListener {
            val reg = Intent (this, RegisterActivity::class.java)
            startActivity(reg)
        }

        buttonAccesso.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin() {
        if (inputNome.text.toString().isEmpty()) {
            inputNome.error = "Inserire un nome"
            inputNome.requestFocus()
            return
        }

        if (inputEmail.text.toString().isEmpty()) {
            inputEmail.error = "Inserire un indirizzo e-mail"
            inputEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()) {
            inputEmail.error = "Inserire un indirizzo e-mail valido"
            inputEmail.requestFocus()
            return
        }

        if (inputPassword.text.toString().isEmpty()) {
            inputPassword.error = "Inserire una password"
            inputPassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(inputEmailLogin.text.toString(), inputPasswordLogin.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {

                        updateUI(null)
                    }
                }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                        baseContext, "Verifica il tuo indirizzo e-mail",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }else{
            Toast.makeText(
                baseContext, "Login fallito",
                Toast.LENGTH_SHORT
            ).show()

        }

    }


}