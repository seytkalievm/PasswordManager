package com.seytkalievm.passwordmanager.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.seytkalievm.passwordmanager.data.LoginRepository


class AuthViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val firebaseUser = loginRepository.userLiveData

    private val _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail: MutableLiveData<Boolean> get() = _isValidEmail

    private val _isValidPassword = MutableLiveData<Boolean>()
    val isValidPassword: MutableLiveData<Boolean> get() = _isValidPassword

    private val _doPasswordsMatch = MutableLiveData<Boolean>()
    val doPasswordsMatch: MutableLiveData<Boolean> get() = _doPasswordsMatch



    fun register(email: String, password: String){
        loginRepository.register(email, password)
    }

    fun login(email: String, password: String){
        loginRepository.login(email, password)
    }


}