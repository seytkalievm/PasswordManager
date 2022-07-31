package com.seytkalievm.passwordmanager.domain.use_case.credit_card

import com.seytkalievm.passwordmanager.domain.model.CreditCard
import com.seytkalievm.passwordmanager.domain.repository.CreditCardRepository
import javax.inject.Inject

class DeleteCreditCardUseCase @Inject constructor(
    private val repository: CreditCardRepository
) {
    suspend operator fun invoke(creditCard: CreditCard){
        repository.delete(creditCard)
    }
}