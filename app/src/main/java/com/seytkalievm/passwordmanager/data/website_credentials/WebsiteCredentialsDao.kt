package com.seytkalievm.passwordmanager.data.website_credentials

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WebsiteCredentialsDao {
    @Query("SELECT * FROM website_credentials")
    fun getAll(): Flow<List<WebsiteCredentialsEntity>>

    @Insert
    fun insert(credentials: WebsiteCredentialsEntity)

    @Delete
    fun delete(credentials: WebsiteCredentialsEntity)

    @Update
    fun update(credentials: WebsiteCredentialsEntity)
}