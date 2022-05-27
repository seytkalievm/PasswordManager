package com.seytkalievm.passwordmanager.ui.session.passcode

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentPasscodeBinding

class PasscodeFragment : Fragment() {

    private lateinit var binding: FragmentPasscodeBinding

    private var passCode = "2284"
    private var input = ""
    private lateinit var circles: List<View>
    private lateinit var numPad: List<Button>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasscodeBinding.inflate(inflater, container, false)

        circles = listOf( binding.circle1, binding.circle2, binding.circle3, binding.circle4)
        numPad = listOf(
            binding.button0,
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9,
        )
        setButtonClickListeners()
        return binding.root
    }

    private fun setButtonClickListeners(){
        for (i in 0..9 ){
            setNumberListener(numPad[i], i)
        }

        binding.buttonBackspace.setOnClickListener{
            it as ImageButton

            when {
                input.length > 1 -> {
                    input = input.dropLast(1)
                    Log.i("I", input)
                    circles[input.length].setBackgroundResource(R.drawable.bg_view_grey_circle)
                }
                input.length == 1 -> {
                    input = input.dropLast(1)
                    Log.i("I", input)
                    circles[input.length].setBackgroundResource(R.drawable.bg_view_grey_circle)
                    it.setImageResource(R.drawable.ic_baseline_fingerprint_24)
                }
                else -> {
                    (activity as PasscodeActivity).showBiometricAuth()
                }
            }
        }
    }

    private fun setNumberListener(button: Button, number: Int){
        button.setOnClickListener{
            binding.buttonBackspace.setImageResource(R.drawable.ic_baseline_backspace_24)
            circles[input.length].setBackgroundResource(R.drawable.bg_view_blue_circle)
            input = input.plus(number.toString())
            Log.i("I", input)
            if(input.length == 4){
                checkPasscode()
            }
        }
    }

    private fun checkPasscode(){
        for (i in 0..9){
            numPad[i].isClickable = false
        }

        if (passCode == input){
            (activity as PasscodeActivity).startSession()
        } else{
            val toast = Toast.makeText(context, "Wrong Passcode", Toast.LENGTH_SHORT)
            toast.show()
            input = ""
            binding.buttonBackspace.setImageResource(R.drawable.ic_baseline_fingerprint_24)
            for (i in 0..3){
                circles[i].setBackgroundResource(R.drawable.bg_view_grey_circle)
            }
            for (i in 0..9){
                numPad[i].isClickable = true
            }

        }
    }
}