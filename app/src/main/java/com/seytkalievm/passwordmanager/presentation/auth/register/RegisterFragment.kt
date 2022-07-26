package com.seytkalievm.passwordmanager.presentation.auth.register

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.common.Resource
import com.seytkalievm.passwordmanager.databinding.FragmentRegisterBinding
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity
import com.seytkalievm.passwordmanager.presentation.passcode.create.CreatePasscodeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confPassword: EditText


    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false).apply {
            email = registerEmailEt
            password = registerPasswordEt
            confPassword = registerConfPasswordEt
        }
        binding.registerFragment = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressDialog = context?.let { Dialog(it) }
        progressDialog?.setContentView(R.layout.layout_progress_bar)

        registerViewModel.registerStatus.observe(viewLifecycleOwner){ resource ->
            when (resource){
                is Resource.Loading -> {
                    progressDialog?.show()
                }
                is Resource.Success -> {
                    progressDialog?.dismiss()
                    createPasscode()
                }
                is Resource.Error -> {
                    progressDialog?.dismiss()
                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerViewModel.formState.observe(viewLifecycleOwner){
            if(it.emailError != null){
                binding.registerEmailLayout.error = getString(it.emailError!!)
            }
            if(it.passwordError != null){
                binding.registerPasswordLayout.error = getString(it.passwordError!!)
            }
            if (it.confPasswordError!=null){
                binding.registerConfPasswordLayout.error  = getString(it.confPasswordError!!)
            }

        }

        email.addTextChangedListener {
            binding.registerEmailLayout.error = null
            registerViewModel.emailChanged(it.toString())
        }

        password.addTextChangedListener {
            binding.registerPasswordLayout.error = null
            registerViewModel.passwordChanged(it.toString())
        }

        confPassword.addTextChangedListener {
            binding.registerConfPasswordLayout.error = null
            registerViewModel.confPasswordChanged(it.toString())
        }

    }
    fun register(){
        registerViewModel.register()
    }

    fun continueWithGoogle(){
        (activity as AuthActivity).signInWithGoogle()
    }

    fun goToLogin(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun createPasscode(){
        val intent = Intent(context, CreatePasscodeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        activity?.finish()
    }
}