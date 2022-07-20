package com.seytkalievm.passwordmanager.presentation.auth.register

data class RegistrationFormState(
    var emailError: Int? = null,
    var passwordError: Int? = null,
    var confPasswordError: Int? = null,
)