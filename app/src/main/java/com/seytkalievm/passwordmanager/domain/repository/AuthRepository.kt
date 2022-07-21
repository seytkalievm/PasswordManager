package com.seytkalievm.passwordmanager.domain.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val user: LiveData<FirebaseUser>

    fun register(email: String, password: String)

    suspend fun logout()

    fun login(email: String, password: String)
}