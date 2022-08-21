package com.seytkalievm.passwordmanager.presentation.session.passwords.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentAddWebsiteCredentialsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWebsiteCredentialsFragment : Fragment() {

    private val viewModel: AddWebsiteCredentialsViewModel by viewModels()
    private lateinit var binding: FragmentAddWebsiteCredentialsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddWebsiteCredentialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.status.observe(viewLifecycleOwner){ status ->
            if (status.data == true){
                findNavController().navigateUp()
                Toast.makeText(context, "Password added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, status.message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner){
            val error = getString(R.string.empty_field)
            when (it){
                "website" -> binding.websiteNameTextInputLayout.error = error
                "username" -> binding.websiteUsernameTextInputLayout.error = error
                "password" -> binding.websitePasswordTextInputLayout.error = error
            }
        }

        binding.createButton.setOnClickListener{
            viewModel.save()
        }

        binding.websiteNameET.addTextChangedListener{
            viewModel.websiteChanged(it.toString())
            binding.websiteNameTextInputLayout.error = null
        }

        binding.usernameET.addTextChangedListener {
            viewModel.usernameChanged(it.toString())
            binding.websiteUsernameTextInputLayout.error = null

        }

        binding.passwordET.addTextChangedListener {
            viewModel.passwordChanged(it.toString())
            binding.websitePasswordTextInputLayout.error = null

        }

    }

}