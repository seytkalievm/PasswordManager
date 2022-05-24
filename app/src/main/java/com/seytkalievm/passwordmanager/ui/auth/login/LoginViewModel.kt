package com.seytkalievm.passwordmanager.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

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


}