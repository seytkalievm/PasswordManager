package com.seytkalievm.passwordmanager.data.repository

import com.seytkalievm.passwordmanager.data.credit_card.CreditCardDao
import com.seytkalievm.passwordmanager.domain.model.CreditCard
import com.seytkalievm.passwordmanager.domain.repository.CreditCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreditCardRepositoryImpl @Inject constructor(
    private val creditCardDao: CreditCardDao
): CreditCardRepository {

    override suspend fun getAll(): Flow<List<CreditCard>> {
        return creditCardDao.getAll().map {list ->
            list.map { creditCardEntity ->
                creditCardEntity.fromEntity()
            }
        }
    }

    override suspend fun save(creditCard: CreditCard) {
          creditCardDao.insert(creditCard.toEntity())
    }

    override suspend fun update(creditCard: CreditCard) {
        creditCardDao.update(creditCard.toEntity())
    }

    override suspend fun delete(creditCard: CreditCard) {
        creditCardDao.delete(creditCard.toEntity())
    }
}