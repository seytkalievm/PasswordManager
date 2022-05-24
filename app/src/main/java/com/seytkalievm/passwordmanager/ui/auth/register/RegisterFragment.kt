package com.seytkalievm.passwordmanager.ui.auth.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentRegisterBinding
import com.seytkalievm.passwordmanager.ui.auth.AuthActivity
import com.seytkalievm.passwordmanager.ui.auth.AuthViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confPassword: EditText
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var registerViewModel: RegisterViewModel

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

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        authViewModel.firebaseUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                Toast.makeText(this.context, "User Registered", Toast.LENGTH_SHORT).show()
                (activity as AuthActivity).startSession()
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
            Log.i("AA", it.toString())
            binding.registerEmailLayout.error = null
            registerViewModel.emailChanged(it.toString())
        }

        password.addTextChangedListener {
            Log.i("AA", it.toString())
            binding.registerPasswordLayout.error = null

            registerViewModel.passwordChanged(it.toString())
        }

        confPassword.addTextChangedListener {
            Log.i("AA", it.toString())
            binding.registerConfPasswordLayout.error = null
            registerViewModel.confPasswordChanged(it.toString())
        }

    }
    fun register(){
        registerViewModel.checkFromValidity()

        if (registerViewModel.isFromValid){
            authViewModel.register(email.text.toString(), password.text.toString())
        }
    }

    fun goToLogin(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

    }

}