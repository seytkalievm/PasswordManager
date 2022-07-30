package com.seytkalievm.passwordmanager.data.personal_info

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalInfoDao {
    @Query("SELECT * FROM personal_info")
    suspend fun getAll():Flow<List<PersonalInfoEntity>>

    @Insert
    suspend fun insert(info: PersonalInfoEntity)

    @Delete
    suspend fun delete(info: PersonalInfoEntity)

    @Update
    suspend fun update(info: PersonalInfoEntity)
}