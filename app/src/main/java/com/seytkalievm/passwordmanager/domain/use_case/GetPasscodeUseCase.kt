package com.seytkalievm.passwordmanager.domain.use_case

import com.seytkalievm.passwordmanager.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetPasscodeUseCase @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository
){
    suspend operator fun invoke(): String = preferencesRepository.passcode.first()
}