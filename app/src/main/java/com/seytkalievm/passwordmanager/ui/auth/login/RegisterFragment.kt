package com.seytkalievm.passwordmanager.ui.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

        setOnClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.firebaseUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                Toast.makeText(this.context, "User Registered", Toast.LENGTH_SHORT).show()
                (activity as AuthActivity).startSession()
            }
        }

        viewModel.loginFormState.observe(viewLifecycleOwner,
            Observer {
                if(it == null){
                    return@Observer
                }
                binding.registerRegisterBnt.isEnabled = it.isDataValid
                it.emailError?.let {
                    binding.registerEmailLayout.error = "Invalid Email"
                }
                it.passwordError?.let {
                    binding.registerPasswordLayout.error = "Invalid password"
                }
                it.confPasswordError?.let {
                    binding.registerConfPasswordLayout.error = "Password don't match"
                }
            }
        )

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                viewModel.registerDataChanged(
                    binding.registerEmailEt.text.toString(),
                    binding.registerPasswordEt.text.toString(),
                    binding.registerConfPasswordEt.text.toString(),
                )
            }
        }

        binding.registerEmailEt.addTextChangedListener(afterTextChangedListener)
        binding.registerPasswordEt.addTextChangedListener(afterTextChangedListener)
        binding.registerConfPasswordEt.addTextChangedListener(afterTextChangedListener)





    }

    private fun setOnClickListeners(){

        binding.registerRegisterBnt.setOnClickListener{
            viewModel.register(email.text.toString(), password.text.toString())
        }

        binding.registerLoginBtn.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

}