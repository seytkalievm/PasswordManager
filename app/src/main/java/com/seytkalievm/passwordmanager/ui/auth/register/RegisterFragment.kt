package com.seytkalievm.passwordmanager.ui.auth.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.Auth
import com.seytkalievm.passwordmanager.PasswordManagerApplication
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.FragmentRegisterBinding
import com.seytkalievm.passwordmanager.ui.auth.AuthActivity
import com.seytkalievm.passwordmanager.ui.auth.AuthViewModel
import com.seytkalievm.passwordmanager.ui.auth.ViewModelFactory
import javax.inject.Inject

const val RC_SIGN_IN = 120

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confPassword: EditText

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var registerViewModel: RegisterViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as PasswordManagerApplication).appComponent.inject(this)
        registerViewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
        Log.i("ViewModel", registerViewModel.authRepository.toString())
    }

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

        registerViewModel.firebaseUser.observe(viewLifecycleOwner) { user ->
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
        registerViewModel.checkFromValidity()

        if (registerViewModel.isFromValid){
            registerViewModel.register()
        }
    }

    fun continueWithGoogle(){
        val intent = registerViewModel.startGoogleSignInFlow()
        (activity as AuthActivity).startActivityForResult(intent, RC_SIGN_IN)

    }

    fun goToLogin(){
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

}