package com.seytkalievm.passwordmanager.domain.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import com.seytkalievm.passwordmanager.common.Resource
interface AuthRepository {

    val user: LiveData<FirebaseUser>

    val loginStatus: LiveData<Resource<Boolean>>

    val registerStatus: LiveData<Resource<Boolean>>

    val logoutStatus: LiveData<Resource<Boolean>>

    fun register(email: String, password: String)

    suspend fun logout()

    fun login(email: String, password: String)
}