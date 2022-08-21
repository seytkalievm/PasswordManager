package com.seytkalievm.passwordmanager.presentation.session.passwords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentPasswordsBinding
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_passwordsFragment_to_addWebsiteCredentialsFragment)
        }

        val fragmentManager = parentFragmentManager
        
        fun showDialog(credentials: WebsiteCredentials){
            PasswordDialogFragment(websiteCredentials = credentials)
                .show(fragmentManager, credentials.link)
        }
        val clickListener = WebsiteCredentialsRecyclerViewAdapter.OnClickListener{
            showDialog(it)
        }

        val adapter = WebsiteCredentialsRecyclerViewAdapter(clickListener)

        binding.credentials.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.credentials.adapter = adapter

        viewModel.credentials.observe(viewLifecycleOwner){
            adapter.submitList(it?: emptyList())
        }


    }


}