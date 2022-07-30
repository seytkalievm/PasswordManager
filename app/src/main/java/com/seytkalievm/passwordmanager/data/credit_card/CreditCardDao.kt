package com.seytkalievm.passwordmanager.data.credit_card

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditCardDao {
    @Query("SELECT * FROM credit_cards")
    suspend fun getAll(): Flow<List<CreditCardEntity>>

    @Insert
    suspend fun insert(card: CreditCardEntity)

    @Delete
    suspend fun delete(card: CreditCardEntity)

    @Update
    suspend fun update(card: CreditCardEntity)
}