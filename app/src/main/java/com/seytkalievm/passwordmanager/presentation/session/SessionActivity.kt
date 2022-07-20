package com.seytkalievm.passwordmanager.presentation.session

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seytkalievm.passwordmanager.databinding.ActivitySessionBinding
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity

class SessionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySessionBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySessionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun logout(){
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }
}