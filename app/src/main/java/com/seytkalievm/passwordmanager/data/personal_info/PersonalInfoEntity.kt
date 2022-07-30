package com.seytkalievm.passwordmanager.data.personal_info

import androidx.room.Entity
import com.seytkalievm.passwordmanager.domain.model.PersonalInfo

@Entity(tableName = "personal_info", primaryKeys = ["name", "value"])
data class PersonalInfoEntity(
    val name: String,
    val value: String
){
    fun fromEntity(): PersonalInfo {
        return PersonalInfo(
            name = name,
            value = value
        )
    }
}