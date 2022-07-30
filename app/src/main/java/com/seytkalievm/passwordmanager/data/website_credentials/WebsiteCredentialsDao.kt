package com.seytkalievm.passwordmanager.data.website_credentials

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WebsiteCredentialsDao {
    @Query("SELECT * FROM website_credentials")
    suspend fun getAll(): Flow<List<WebsiteCredentialsEntity>>

    @Insert
    suspend fun insert(credentials: WebsiteCredentialsEntity)

    @Delete
    suspend fun delete(credentials: WebsiteCredentialsEntity)

    @Update
    suspend fun update(credentials: WebsiteCredentialsEntity)
}