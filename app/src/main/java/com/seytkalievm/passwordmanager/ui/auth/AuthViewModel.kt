package com.seytkalievm.passwordmanager.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.seytkalievm.passwordmanager.data.LoginRepository

import com.seytkalievm.passwordmanager.ui.auth.login.LoginFormState

class AuthViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val firebaseUser = loginRepository.userLiveData

    private var email: String = ""
    private var password: String = ""
    private var confPassword: String = ""

    private val _canRegister = MutableLiveData<Boolean>(false)
    val canRegister: MutableLiveData<Boolean> get() = _canRegister

    private val _canLogin = MutableLiveData<Boolean>(false)
    val canLogin: LiveData<Boolean> get() = _canLogin

    private val _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail: MutableLiveData<Boolean> get() = _isValidEmail

    private val _isValidPassword = MutableLiveData<Boolean>()
    val isValidPassword: MutableLiveData<Boolean> get() = _isValidPassword

    private val _doPasswordsMatch = MutableLiveData<Boolean>()
    val doPasswordsMatch: MutableLiveData<Boolean> get() = _doPasswordsMatch



    fun register(){
        loginRepository.register(email, password)
    }

    fun login(){
        loginRepository.login(email, password)
    }


    fun emailChanged(email: String){
        this.email = email
        checkEmail()
    }

    fun passwordChanged(password: String){
        this.password = password
        checkPassword()
    }

    fun confPasswordChanged(confPassword: String){
        this.confPassword = confPassword
        checkPasswordsMatch()
    }


    private fun checkEmail() {
        _isValidEmail.value = Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
        checkFormValidity()
    }

    private fun checkPassword(){
        val validator = Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$")
        _isValidPassword.value = validator.matches(password)
        checkFormValidity()
    }

    private fun checkPasswordsMatch(){
        if (password.isNotEmpty() && confPassword.isNotEmpty()){
            _doPasswordsMatch.value = password == confPassword
            checkFormValidity()
        } else {
            _doPasswordsMatch.value = false
        }
    }

    private fun checkFormValidity(){
        _canRegister.value = _isValidEmail.value == true &&
                _isValidPassword.value == true && _doPasswordsMatch.value == true

        _canLogin.value = email.isNotEmpty() && password.isNotEmpty()
    }

    fun resetForms(){
        email = ""
        password = ""
        confPassword = ""
        _isValidPassword.value = true
        _isValidEmail.value = true
    }

}