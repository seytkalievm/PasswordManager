package com.seytkalievm.passwordmanager.presentation.session.credit_cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.seytkalievm.passwordmanager.databinding.FragmentCreditCardsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditCardsFragment : Fragment() {

    private lateinit var binding: FragmentCreditCardsBinding
    private val viewModel: CreditCardsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditCardsBinding.inflate(inflater, container, false)
        return binding.root
    }


}