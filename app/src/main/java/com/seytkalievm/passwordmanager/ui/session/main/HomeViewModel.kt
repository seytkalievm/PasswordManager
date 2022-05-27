package com.seytkalievm.passwordmanager.ui.session.main

import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.data.AuthRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun logout(){
        authRepository.logout()
    }
}