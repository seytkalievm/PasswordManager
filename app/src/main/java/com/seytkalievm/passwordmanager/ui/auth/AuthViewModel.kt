package com.seytkalievm.passwordmanager.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.data.AuthRepository
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val firebaseUser = authRepository.userLiveData

    private val _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail: MutableLiveData<Boolean> get() = _isValidEmail

    private val _isValidPassword = MutableLiveData<Boolean>()
    val isValidPassword: MutableLiveData<Boolean> get() = _isValidPassword

    private val _doPasswordsMatch = MutableLiveData<Boolean>()
    val doPasswordsMatch: MutableLiveData<Boolean> get() = _doPasswordsMatch



    fun register(email: String, password: String){
        authRepository.register(email, password)
    }

    fun login(email: String, password: String){
        authRepository.login(email, password)
    }


}