package com.seytkalievm.passwordmanager.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentLoginBinding
import com.seytkalievm.passwordmanager.ui.auth.AuthActivity
import com.seytkalievm.passwordmanager.ui.auth.AuthViewModel

class LogInFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()
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

        binding.fragment = this
        binding.loginEmailEt.addTextChangedListener {
            viewModel.emailChanged(it.toString())
        }

        binding.loginPasswordEt.addTextChangedListener{
            viewModel.passwordChanged(it.toString())
        }

        viewModel.canLogin.observe(viewLifecycleOwner){
            binding.loginLoginBtn.isEnabled = it
        }

        viewModel.firebaseUser.observe(viewLifecycleOwner){
            if (it != null) {
                Toast.makeText(this.context, "User logged in", Toast.LENGTH_SHORT).show()
                (activity as AuthActivity).startSession()
            }
        }

    }

    fun goToRegister(){
        viewModel.resetForms()
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun login(){
        viewModel.login()
    }
}