package com.seytkalievm.passwordmanager.data.credit_card

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditCardDao {
    @Query("SELECT * FROM credit_cards")
    fun getAll(): Flow<List<CreditCardEntity>>

    @Insert
    fun insert(card: CreditCardEntity)

    @Delete
    fun delete(card: CreditCardEntity)

    @Update
    fun update(card: CreditCardEntity)
}