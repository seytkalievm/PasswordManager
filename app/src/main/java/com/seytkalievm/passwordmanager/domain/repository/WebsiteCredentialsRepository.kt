package com.seytkalievm.passwordmanager.domain.repository

import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
import kotlinx.coroutines.flow.Flow

interface WebsiteCredentialsRepository {

    suspend fun getAll(): Flow<List<WebsiteCredentials>>

    suspend fun save(websiteCredentials: WebsiteCredentials)

    suspend fun update(websiteCredentials: WebsiteCredentials)

    suspend fun delete(websiteCredentials: WebsiteCredentials)
}
