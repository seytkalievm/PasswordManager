package com.seytkalievm.passwordmanager.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val authRepository: AuthRepository,
): ViewModel() {

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