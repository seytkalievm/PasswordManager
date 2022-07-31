package com.seytkalievm.passwordmanager.domain.use_case.personal_info

import com.seytkalievm.passwordmanager.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class GetAllPersonalInfoUseCase @Inject constructor(
    private val repository: PersonalInfoRepository
){
    suspend operator fun invoke() = repository.getAll()

}