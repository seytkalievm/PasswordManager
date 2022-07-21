package com.seytkalievm.passwordmanager.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity
import com.seytkalievm.passwordmanager.presentation.passcode.create.CreatePasscodeActivity
import com.seytkalievm.passwordmanager.presentation.passcode.enter.EnterPasscodeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isReady.observe(this){
            if (it){
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                val intent = if(viewModel.user!= null){
                    if (viewModel.passcode==null){
                        Intent(this, CreatePasscodeActivity::class.java)
                    } else{
                        Intent(this, EnterPasscodeActivity::class.java)
                    }
                } else {
                    Intent(this, AuthActivity::class.java)
                }
                startActivity(intent)
                this.finish()
            }
        }

        setContentView(R.layout.activity_main)
    }
}