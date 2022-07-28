package com.seytkalievm.passwordmanager.domain.model

data class CreditCard(
    val name: String,
    val number: String,
    val validThru: String,
    val cvv: String,
    val cardHolder: String
)