package com.seytkalievm.passwordmanager.ui.session.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.passwordmanager.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}