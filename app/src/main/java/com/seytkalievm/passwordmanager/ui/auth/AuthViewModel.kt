package com.seytkalievm.passwordmanager.ui.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseUser
import com.seytkalievm.passwordmanager.data.LoginRepository
import com.seytkalievm.passwordmanager.data.Result

import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.ui.auth.login.LoginFormState
import com.seytkalievm.passwordmanager.ui.auth.login.LoginResult

class AuthViewModel(private val loginRepository: LoginRepository, application: Application) : AndroidViewModel(
    application
) {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    val firebaseUser = loginRepository.userLiveData

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun register(email: String, password: String){
        loginRepository.register(email, password)
    }

    fun registerDataChanged(email: String, password: String, confirmPassword: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else if (password != confirmPassword){
            _loginForm.value = LoginFormState(passwordError = R.string.password_do_not_match)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return if (email.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}