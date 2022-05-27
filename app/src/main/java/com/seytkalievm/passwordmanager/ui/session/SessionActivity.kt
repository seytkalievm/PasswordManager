package com.seytkalievm.passwordmanager.ui.session

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.SessionActivityBinding
import com.seytkalievm.passwordmanager.ui.auth.AuthActivity

class SessionActivity : AppCompatActivity() {

    private lateinit var binding: SessionActivityBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = SessionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun logout(){
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }
}