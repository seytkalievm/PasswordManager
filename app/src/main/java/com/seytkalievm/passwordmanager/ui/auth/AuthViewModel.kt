package com.seytkalievm.passwordmanager.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.data.AuthRepository
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun register(email: String, password: String){
        authRepository.register(email, password)
    }

    fun login(email: String, password: String){
        authRepository.login(email, password)
    }


}