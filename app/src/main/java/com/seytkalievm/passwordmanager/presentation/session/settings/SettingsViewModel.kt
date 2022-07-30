package com.seytkalievm.passwordmanager.presentation.session.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.passwordmanager.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}