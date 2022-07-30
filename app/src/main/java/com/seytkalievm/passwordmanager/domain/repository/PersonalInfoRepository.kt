package com.seytkalievm.passwordmanager.domain.repository

import com.seytkalievm.passwordmanager.domain.model.PersonalInfo
import kotlinx.coroutines.flow.Flow

interface PersonalInfoRepository {
    suspend fun getAll(): Flow<List<PersonalInfo>>

    suspend fun save(personalInfo: PersonalInfo)

    suspend fun update(personalInfo: PersonalInfo)

    suspend fun delete(personalInfo: PersonalInfo)
}