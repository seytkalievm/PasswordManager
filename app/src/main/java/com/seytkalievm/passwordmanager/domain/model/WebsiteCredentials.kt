package com.seytkalievm.passwordmanager.domain.model

import com.seytkalievm.passwordmanager.data.website_credentials.WebsiteCredentialsEntity

data class WebsiteCredentials(
    val link: String,
    val username: String,
    val password: String
) {
    fun toEntity(): WebsiteCredentialsEntity{
        return WebsiteCredentialsEntity(
            link = link,
            username = username,
            password = password
        )
    }
}