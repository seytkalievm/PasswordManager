package com.seytkalievm.passwordmanager.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.data.AuthRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(val authRepository: AuthRepository): ViewModel() {

    val firebaseUser = authRepository.userLiveData
    private val _canLogin = MutableLiveData(false)
    val canLogin: LiveData<Boolean> get() = _canLogin
    private var email = ""
    private var password = ""

    fun emailChanged(email: String){
        this.email = email
        checkValidity()
    }

    fun passwordChanged(password: String) {
        this.password = password
        checkValidity()
    }

    private fun checkValidity(){
        if(email.isNotEmpty() && password.isNotEmpty()){
            _canLogin.postValue(true)
        } else{
            _canLogin.postValue(false)
        }
    }

    fun login(){
        authRepository.login(email, password)
    }

}