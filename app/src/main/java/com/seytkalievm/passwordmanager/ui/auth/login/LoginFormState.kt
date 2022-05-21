package com.seytkalievm.passwordmanager.ui.auth.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val confPasswordError: Int? = null,
    val isDataValid: Boolean = false
)