package com.seytkalievm.passwordmanager.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentLoginBinding
import com.seytkalievm.passwordmanager.ui.auth.AuthActivity
import com.seytkalievm.passwordmanager.ui.auth.AuthViewModel

class LogInFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.fragment = this

        authViewModel.firebaseUser.observe(viewLifecycleOwner){
            if (it != null) {
                Toast.makeText(this.context, "User logged in", Toast.LENGTH_SHORT).show()
                (activity as AuthActivity).startSession()
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

    fun goToRegister(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }


}