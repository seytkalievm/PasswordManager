package com.seytkalievm.passwordmanager.data.repository

import com.seytkalievm.passwordmanager.data.personal_info.PersonalInfoDao
import com.seytkalievm.passwordmanager.domain.model.PersonalInfo
import com.seytkalievm.passwordmanager.domain.repository.PersonalInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonalInfoRepositoryImpl @Inject constructor(
    private val personalInfoDao: PersonalInfoDao
): PersonalInfoRepository {
    override suspend fun getAll(): Flow<List<PersonalInfo>> {
        return personalInfoDao.getAll().map {list ->
            list.map{entity ->
                entity.fromEntity()
            }
        }
    }

    override suspend fun save(personalInfo: PersonalInfo) {
        personalInfoDao.insert(personalInfo.toEntity())
    }

    override suspend fun update(personalInfo: PersonalInfo) {
        personalInfoDao.update(personalInfo.toEntity())
    }

    override suspend fun delete(personalInfo: PersonalInfo) {
        personalInfoDao.delete(personalInfo.toEntity())
    }
}