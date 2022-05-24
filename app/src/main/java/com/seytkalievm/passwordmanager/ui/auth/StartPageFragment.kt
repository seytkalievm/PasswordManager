package com.seytkalievm.passwordmanager.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentStartPageBinding


class StartPageFragment : Fragment() {

    private lateinit var binding: FragmentStartPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startLoginBtn.setOnClickListener{
            findNavController().navigate(R.id.action_startPageFragment_to_loginFragment)
        }

        binding.startRegisterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startPageFragment_to_registerFragment)
        }
    }
}