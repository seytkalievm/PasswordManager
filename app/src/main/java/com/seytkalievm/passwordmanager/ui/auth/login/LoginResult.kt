package com.seytkalievm.passwordmanager.ui.auth.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val error: Int? = null
)