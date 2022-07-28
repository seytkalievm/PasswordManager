package com.seytkalievm.passwordmanager.domain.use_case

import com.seytkalievm.passwordmanager.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SetPasscodeUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
){
    suspend operator fun invoke(passcode:String){
        userPreferencesRepository.setPasscode(passcode)
    }
}