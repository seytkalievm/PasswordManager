package com.seytkalievm.passwordmanager.data.website_credentials

import androidx.room.Entity

@Entity(tableName = "website_credentials", primaryKeys = ["link", "username"])
data class WebsiteCredentialsEntity(
    val link: String,
    val username: String,
    val password: String
)
