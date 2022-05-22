package com.seytkalievm.passwordmanager.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
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
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false).apply {
            email = registerEmailEt
            password = registerPasswordEt
            confPassword = registerConfPasswordEt
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerFragment = this

        viewModel.firebaseUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                Toast.makeText(this.context, "User Registered", Toast.LENGTH_SHORT).show()
                (activity as AuthActivity).startSession()
            }
        }

        viewModel.isValidEmail.observe(viewLifecycleOwner){
            if (!it) {
                binding.registerEmailLayout.error = getString(R.string.invalid_username)
            } else {
                binding.registerEmailLayout.isErrorEnabled = false
            }
        }

        viewModel.isValidPassword.observe(viewLifecycleOwner){
            if (!it) {
                binding.registerPasswordLayout.error = getString(R.string.invalid_password)
            } else {
                binding.registerPasswordLayout.isErrorEnabled = false
            }
        }

        viewModel.doPasswordsMatch.observe(viewLifecycleOwner){
            if (!it) {
                binding.registerConfPasswordLayout.error = getString(R.string.password_do_not_match)
            } else {
                binding.registerConfPasswordLayout.isErrorEnabled = false
            }
        }

        viewModel.canRegister.observe(viewLifecycleOwner){
            binding.registerRegisterBnt.isEnabled = it
        }



        binding.registerEmailEt.addTextChangedListener {
            viewModel.emailChanged(it.toString())

            if (it != null) {
                if (it.isEmpty())
                    binding.registerEmailLayout.isErrorEnabled = false
            }
        }
        binding.registerPasswordEt.addTextChangedListener {
            viewModel.passwordChanged(it.toString())

            if (it != null) {
                if (it.isEmpty())
                    binding.registerPasswordLayout.isErrorEnabled = false
            }

        }
        binding.registerConfPasswordEt.addTextChangedListener {
            viewModel.confPasswordChanged(it.toString())

            if (it != null) {
                if (it.isEmpty())
                    binding.registerConfPasswordLayout.isErrorEnabled = false
            }
        }


    }
    fun register(){
        viewModel.register()
    }

    fun goToLogin(){
        viewModel.resetForms()
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

    }

}