package com.seytkalievm.passwordmanager.domain.use_case.website_credentials

import com.seytkalievm.passwordmanager.domain.repository.WebsiteCredentialsRepository
import javax.inject.Inject

class GetAllCredentialsUseCase @Inject constructor(
    private val repository: WebsiteCredentialsRepository
) {
    suspend operator fun invoke() = repository.getAll()
}