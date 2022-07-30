package com.seytkalievm.passwordmanager.presentation.session.passwords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.seytkalievm.passwordmanager.databinding.FragmentPasswordsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordsFragment : Fragment() {

    private lateinit var binding: FragmentPasswordsBinding
    private val viewModel: PasswordsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordsBinding.inflate(inflater, container, false)
        return binding.root
    }


}