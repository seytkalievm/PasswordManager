package com.seytkalievm.passwordmanager.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val passcode: Flow<String>

    suspend fun setPasscode(passcode: String)
}