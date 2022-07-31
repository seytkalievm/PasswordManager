package com.seytkalievm.passwordmanager.domain.use_case.credit_card

import com.seytkalievm.passwordmanager.domain.repository.CreditCardRepository
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(
    private val repository: CreditCardRepository
){
    suspend operator fun invoke() = repository.getAll()

}