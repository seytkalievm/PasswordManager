package com.seytkalievm.passwordmanager.presentation.passcode.enter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentPasscodeBinding
import com.seytkalievm.passwordmanager.presentation.passcode.PasscodeActivity

class EnterPasscodeFragment : Fragment() {

    private lateinit var binding: FragmentPasscodeBinding

    private lateinit var circles: List<View>
    private lateinit var numPad: List<Button>


    private val viewModel: EnterPasscodeViewModel by viewModels()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isPasscodeValid.observe(viewLifecycleOwner){ valid ->
            if (valid) (activity as PasscodeActivity).startSession()
        }

        viewModel.coloredDots.observe(viewLifecycleOwner){ coloredDots ->
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
            (activity as PasscodeActivity).exit()
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
}