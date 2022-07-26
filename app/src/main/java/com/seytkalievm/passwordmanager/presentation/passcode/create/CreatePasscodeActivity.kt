package com.seytkalievm.passwordmanager.presentation.passcode.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seytkalievm.passwordmanager.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreatePasscodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_passcode)
    }


    override fun onBackPressed() {
        moveTaskToBack(false)
    }
}