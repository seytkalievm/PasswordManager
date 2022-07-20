package com.seytkalievm.passwordmanager.ui.auth

import androidx.lifecycle.ViewModel
import com.seytkalievm.passwordmanager.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(authRepository: AuthRepository): ViewModel() {
    private var email = ""
    private var password = ""
    private var passcode = ""
    private var googleId = ""

}