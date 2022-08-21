package com.seytkalievm.passwordmanager.presentation.session.passwords.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.passwordmanager.common.Resource
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
import com.seytkalievm.passwordmanager.domain.use_case.website_credentials.AddCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWebsiteCredentialsViewModel @Inject constructor(
    private val addCredentialsUseCase: AddCredentialsUseCase
) : ViewModel(){

    private var website = ""
    private var username = ""
    private var password = ""

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _status = MutableLiveData<Resource<Boolean>>()
    val status: LiveData<Resource<Boolean>> get() = _status

    fun websiteChanged(website: String) {
        this.website = website
    }

    fun usernameChanged(username: String){
        this.username = username
    }

    fun passwordChanged(password: String){
        this.password = password
    }

    fun save(){
        if (checkValidity()) {
            val credentials = WebsiteCredentials(
                link = website,
                username = username,
                password = password
            )

            viewModelScope.launch(Dispatchers.IO) {
                val result = addCredentialsUseCase(credentials)
                _status.postValue(result)
            }
        }
    }

    private fun checkValidity(): Boolean{
        when{
            website.isEmpty() -> _error.postValue("website")
            username.isEmpty() -> _error.postValue("username")
            password.isEmpty() -> _error.postValue("password")
            else -> return true
        }
        return false
    }

}