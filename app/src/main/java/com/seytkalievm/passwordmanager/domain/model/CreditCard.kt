package com.seytkalievm.passwordmanager.domain.model

import com.seytkalievm.passwordmanager.data.credit_card.CreditCardEntity

data class CreditCard(
    val name: String,
    val number: String,
    val validThru: String,
    val cvv: String,
    val cardHolder: String
) {
    fun toEntity(): CreditCardEntity{
        return CreditCardEntity(
            name = name,
            number = number,
            validThru = validThru,
            cvv = cvv,
            cardHolder = cardHolder
        )
    }
}