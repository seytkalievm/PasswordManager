package com.seytkalievm.passwordmanager.presentation.auth.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel@Inject constructor(
    val authRepository: AuthRepository
): ViewModel() {

    val user = authRepository.user

    private var _fromState = MutableLiveData(RegistrationFormState())
    val formState: LiveData<RegistrationFormState> get() = _fromState
    private var _isFormValid: Boolean = false
    val isFromValid:Boolean get() = _isFormValid

    private var email = ""
    private var password = ""
    private var confPassword = ""


    fun emailChanged(email: String){
        this.email = email
    }

    fun passwordChanged(password: String){
        this.password = password
    }

    fun confPasswordChanged(confPassword: String){
        this.confPassword = confPassword
    }


    private fun isValidEmail(): Boolean{
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()){
            _fromState.postValue(RegistrationFormState(emailError = R.string.invalid_email))
            false
        } else {
            _fromState.value?.emailError = null
            true
        }
    }

    private fun isValidPassword(): Boolean{
        if (password.length < 6){
            _fromState.postValue(RegistrationFormState(passwordError = R.string.invalid_password_length))
        } else if(!password.contains(Regex("^(?=.*?[A-Z])"))){
            _fromState.postValue(RegistrationFormState(passwordError = R.string.invalid_password_no_upper))
        } else if (!password.contains(Regex("^(?=.*?[a-z])"))){
            _fromState.postValue(RegistrationFormState(passwordError = R.string.invalid_password_no_lower))
        } else if (!password.contains(Regex("^(?=.*?[0-9])"))){
            _fromState.postValue(RegistrationFormState(passwordError = R.string.invalid_password_numeric))
        } else {
            _fromState.postValue(RegistrationFormState(passwordError = null))
            return true
        }
        return false
    }

    private fun doPasswordsMatch(): Boolean{
        return if (password != confPassword){
            _fromState.postValue(RegistrationFormState(confPasswordError = R.string.password_do_not_match))
            false
        } else {
            _fromState.value?.confPasswordError = null
            true
        }
    }


    fun checkFromValidity(){
        _isFormValid = isValidEmail() && isValidPassword() && doPasswordsMatch()
    }

    fun register(){
        authRepository.register(email, password)
    }

}