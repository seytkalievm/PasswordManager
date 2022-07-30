package com.seytkalievm.passwordmanager.data.personal_info

import androidx.room.Entity

@Entity(tableName = "personal_info", primaryKeys = ["name", "value"])
data class PersonalInfoEntity(
    val name: String,
    val value: String
)