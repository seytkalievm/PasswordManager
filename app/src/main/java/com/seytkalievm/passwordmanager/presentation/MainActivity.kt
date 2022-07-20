package com.seytkalievm.passwordmanager.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity
import com.seytkalievm.passwordmanager.presentation.passcode.PasscodeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser

        if(user!= null){
            val passcodeIntent = Intent(this, PasscodeActivity::class.java)
            startActivity(passcodeIntent)
            this.finish()
        } else {
            val authIntent = Intent(this, AuthActivity::class.java)
            startActivity(authIntent)
            this.finish()
        }

        setContentView(R.layout.activity_main)
    }
}