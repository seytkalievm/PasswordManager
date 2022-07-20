package com.seytkalievm.passwordmanager.presentation.passcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity
import com.seytkalievm.passwordmanager.presentation.session.main.SessionActivity
import java.util.concurrent.Executor

class PasscodeActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passcode)

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor,
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
            }
        )

    }

    fun startSession(){
        val intent = Intent(this, SessionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }

    fun exit(){
        val intent = Intent(this, AuthActivity::class.java)
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