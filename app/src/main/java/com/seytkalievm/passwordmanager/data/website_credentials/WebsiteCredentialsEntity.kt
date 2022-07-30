package com.seytkalievm.passwordmanager.data.website_credentials

import androidx.room.Entity
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials

@Entity(tableName = "website_credentials", primaryKeys = ["link", "username"])
data class WebsiteCredentialsEntity(
    val link: String,
    val username: String,
    val password: String
) {
    fun fromEntity(): WebsiteCredentials {
        return WebsiteCredentials (
            link = link,
            username = username,
            password = password,
        )
    }
}
