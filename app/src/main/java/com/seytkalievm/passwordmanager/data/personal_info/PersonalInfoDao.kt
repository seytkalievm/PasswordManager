package com.seytkalievm.passwordmanager.data.personal_info

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalInfoDao {
    @Query("SELECT * FROM personal_info")
    fun getAll():Flow<List<PersonalInfoEntity>>

    @Insert
    fun insert(info: PersonalInfoEntity)

    @Delete
    fun delete(info: PersonalInfoEntity)

    @Update
    fun update(info: PersonalInfoEntity)
}