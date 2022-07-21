package com.seytkalievm.passwordmanager.presentation.passcode.enter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.ActivityEnterPasscodeBinding
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity
import com.seytkalievm.passwordmanager.presentation.session.SessionActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor

@AndroidEntryPoint
class EnterPasscodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterPasscodeBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var circles: List<View>
    private lateinit var numPad: List<Button>

    private val viewModel: EnterPasscodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnterPasscodeBinding.inflate(layoutInflater)

        setContentView(binding.root)

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

        circles = listOf( binding.circle1, binding.circle2, binding.circle3, binding.circle4)
        numPad = listOf(
            binding.button0,
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9,
        )
        setButtonClickListeners()

        viewModel.isPasscodeValid.observe(this){ valid ->
            if (valid){
                startSession()
            }
        }

        viewModel.coloredDots.observe(this){ coloredDots ->
            colorDots(coloredDots - 1)
        }
    }


    private fun setButtonClickListeners(){
        for (i in 0..9 ){
            setNumberListener(numPad[i], i)
        }

        binding.buttonBackspace.setOnClickListener{
            it as ImageButton
            viewModel.remove()
        }

        binding.buttonExit.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this,  AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
            startActivity(intent)
            this.finish()
        }
    }

    private fun setNumberListener(button: Button, number: Int){
        button.setOnClickListener{
            binding.buttonBackspace.setImageResource(R.drawable.ic_baseline_backspace_24)
            viewModel.numberPressed(number)
        }
    }


    private fun colorDots(number: Int){
        for (i in 0..number) {
            circles[i].setBackgroundResource(R.drawable.bg_view_blue_circle)
        }
        for (i in (number+1)..3) {
            circles[i].setBackgroundResource(R.drawable.bg_view_grey_circle)
        }
    }

    private fun showBiometricAuth(){
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Password Manager Authentication")
            .setSubtitle("Touch fingerprint sensor to log in")
            .setNegativeButtonText("Cancel")
            .build()
        biometricPrompt.authenticate(promptInfo)
    }


    private fun startSession(){
        val intent = Intent(this, SessionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }
}