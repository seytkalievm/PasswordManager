package com.seytkalievm.passwordmanager.data.credit_card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seytkalievm.passwordmanager.domain.model.CreditCard

@Entity(tableName = "credit_cards")
data class CreditCardEntity(
    val name: String,
    @PrimaryKey val number: String,
    @ColumnInfo(name = "valid_thru") val validThru: String,
    val cvv: String,
    @ColumnInfo(name = "card_holder") val cardHolder: String
) {
    fun fromEntity(): CreditCard {
        return CreditCard(
            name = name,
            number = number,
            validThru = validThru,
            cvv = cvv,
            cardHolder = cardHolder
        )
    }
}