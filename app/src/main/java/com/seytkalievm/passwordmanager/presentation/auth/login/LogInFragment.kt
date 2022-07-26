package com.seytkalievm.passwordmanager.presentation.auth.login

import android.app.Dialog
import android.content.Intent
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
import com.seytkalievm.passwordmanager.common.Resource
import com.seytkalievm.passwordmanager.databinding.FragmentLoginBinding
import com.seytkalievm.passwordmanager.presentation.auth.AuthActivity
import com.seytkalievm.passwordmanager.presentation.passcode.create.CreatePasscodeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this

        val progressDialog = context?.let { Dialog(it) }
        progressDialog?.setContentView(R.layout.layout_progress_bar)

        loginViewModel.loginStatus.observe(viewLifecycleOwner){ resource ->
            when(resource){
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

        loginViewModel.canLogin.observe(viewLifecycleOwner){
            binding.loginLoginBtn.isEnabled = it
        }

        binding.loginEmailEt.addTextChangedListener {
            loginViewModel.emailChanged(it.toString())
        }

        binding.loginPasswordEt.addTextChangedListener{
            loginViewModel.passwordChanged(it.toString())
        }

    }

    fun logIn(){
        loginViewModel.login()
    }

    fun loginWithGoogle(){
        (activity as AuthActivity).signInWithGoogle()
    }

    fun goToRegister(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun createPasscode(){
        val intent = Intent(context, CreatePasscodeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        activity?.finish()
    }

}