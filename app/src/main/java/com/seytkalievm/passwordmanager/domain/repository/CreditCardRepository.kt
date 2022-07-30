package com.seytkalievm.passwordmanager.domain.repository

import com.seytkalievm.passwordmanager.domain.model.CreditCard
import kotlinx.coroutines.flow.Flow

interface CreditCardRepository {
    suspend fun getAll(): Flow<List<CreditCard>>

    suspend fun save(creditCard: CreditCard)

    suspend fun update(creditCard: CreditCard)

    suspend fun delete(creditCard: CreditCard)
}