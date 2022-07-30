package com.seytkalievm.passwordmanager.data.repository

import com.seytkalievm.passwordmanager.data.website_credentials.WebsiteCredentialsDao
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
import com.seytkalievm.passwordmanager.domain.repository.WebsiteCredentialsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WebsiteCredentialsRepositoryImpl @Inject constructor(
    private val websiteCredentialsDao: WebsiteCredentialsDao
): WebsiteCredentialsRepository {
    override suspend fun getAll(): Flow<List<WebsiteCredentials>> {
        return websiteCredentialsDao.getAll().map { list ->
            list.map {entity ->
                entity.fromEntity()
            }
        }
    }

    override suspend fun save(websiteCredentials: WebsiteCredentials) {
        websiteCredentialsDao.insert(websiteCredentials.toEntity())
    }

    override suspend fun update(websiteCredentials: WebsiteCredentials) {
        websiteCredentialsDao.update(websiteCredentials.toEntity())
    }

    override suspend fun delete(websiteCredentials: WebsiteCredentials) {
        websiteCredentialsDao.delete(websiteCredentials.toEntity())
    }
}