package com.seytkalievm.passwordmanager.presentation.passcode.create.create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentCreatePasscodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePasscodeFragment : Fragment() {

    private lateinit var binding: FragmentCreatePasscodeBinding

    private val viewModel: CreatePasscodeViewModel by viewModels()

    private lateinit var circles: List<View>
    private lateinit var numPad: List<Button>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePasscodeBinding.inflate(inflater, container, false)
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

        viewModel.coloredDots.observe(viewLifecycleOwner){
            colorDots(it - 1)
            if (it == 4) {
                Log.i("CreatePasscodeFragment", "onViewCreated: navigating to the confirm")
                val passcode = viewModel.passcode
                viewModel.remove()
                findNavController().navigate(CreatePasscodeFragmentDirections
                    .actionCreatePasscodeFragmentToConfirmPasscodeFragment(passcode))

            }
        }

    }

    private fun setButtonClickListeners(){
        for (i in 0..9 ){
            numPad[i].setOnClickListener {
                viewModel.numberPressed(i)
            }
        }

        binding.buttonBackspace.setOnClickListener{
            viewModel.remove()
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