package com.seytkalievm.passwordmanager.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.seytkalievm.passwordmanager.PasswordManagerApplication

import com.seytkalievm.passwordmanager.databinding.ActivityAuthBinding
import com.seytkalievm.passwordmanager.ui.MainActivity
import com.seytkalievm.passwordmanager.ui.session.SessionActivity
import java.util.concurrent.Executor
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this@AuthActivity, executor,
            object:BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.i("E", "Auth error")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Log.i("E", "Auth success")
                    startSession()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.i("E", "Auth failed")

                }
            })

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun startSession(){
        val intent = Intent(this, SessionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }

    fun showBiometricAuth(){
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Password Manager Authentication")
            .setSubtitle("Touch fingerprint sensor to log in")
            .setNegativeButtonText("Cancel")
            .build()
        biometricPrompt.authenticate(promptInfo)
    }
}