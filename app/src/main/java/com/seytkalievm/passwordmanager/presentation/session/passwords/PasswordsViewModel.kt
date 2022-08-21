package com.seytkalievm.passwordmanager.presentation.session.passwords

import androidx.lifecycle.*
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
import com.seytkalievm.passwordmanager.domain.use_case.website_credentials.DeleteCredentialsUseCase
import com.seytkalievm.passwordmanager.domain.use_case.website_credentials.GetAllCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordsViewModel @Inject constructor(
    private val deleteCredentialsUseCase: DeleteCredentialsUseCase,
    private val getAllCredentialsUseCase: GetAllCredentialsUseCase,
) : ViewModel() {


    private val _credentials = MutableLiveData<List<WebsiteCredentials>>()
    val credentials: LiveData<List<WebsiteCredentials>> get() = _credentials


    init {
        viewModelScope.launch(Dispatchers.IO){
            getAllCredentialsUseCase().collect{
                _credentials.postValue(it)
            }
        }
    }

    fun delete(credentials: WebsiteCredentials){
        viewModelScope.launch(Dispatchers.IO) {
            deleteCredentialsUseCase(credentials)
        }
    }
}