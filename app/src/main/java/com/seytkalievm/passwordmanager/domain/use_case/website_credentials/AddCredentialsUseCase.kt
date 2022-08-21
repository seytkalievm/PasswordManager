package com.seytkalievm.passwordmanager.domain.use_case.website_credentials

import android.database.sqlite.SQLiteConstraintException
import com.seytkalievm.passwordmanager.common.Resource
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
import com.seytkalievm.passwordmanager.domain.repository.WebsiteCredentialsRepository
import javax.inject.Inject

class AddCredentialsUseCase @Inject constructor(
    private val repository: WebsiteCredentialsRepository
) {
    suspend operator fun invoke(credentials: WebsiteCredentials): Resource<Boolean>{
        return try{
            repository.save(credentials)
            Resource.Success(true)
        } catch (e: SQLiteConstraintException) {
            Resource.Error("Credentials already exist")
        } catch (e: Exception){
            Resource.Error("Unknown error")
        }
    }
}