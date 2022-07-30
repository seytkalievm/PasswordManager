package com.seytkalievm.passwordmanager.domain.model

import com.seytkalievm.passwordmanager.data.personal_info.PersonalInfoEntity

data class PersonalInfo(
    val name: String,
    val value: String
) {
    fun toEntity() : PersonalInfoEntity{
        return PersonalInfoEntity(
            name = name,
            value = value
        )
    }
}
