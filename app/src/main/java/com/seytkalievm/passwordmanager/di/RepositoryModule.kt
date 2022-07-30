package com.seytkalievm.passwordmanager.di

import android.content.Context
import com.seytkalievm.passwordmanager.data.credit_card.CreditCardDao
import com.seytkalievm.passwordmanager.data.personal_info.PersonalInfoDao
import com.seytkalievm.passwordmanager.data.repository.*
import com.seytkalievm.passwordmanager.data.website_credentials.WebsiteCredentialsDao
import com.seytkalievm.passwordmanager.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserPreferencesRepository(
        @ApplicationContext context: Context
    ): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideAuthRepository( @ApplicationContext context: Context): AuthRepository {
        return AuthRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideCreditCardRepository(creditCardDao: CreditCardDao): CreditCardRepository{
        return CreditCardRepositoryImpl(creditCardDao)
    }

    @Singleton
    @Provides
    fun providePersonalInfoRepository(personalInfoDao: PersonalInfoDao): PersonalInfoRepository{
        return PersonalInfoRepositoryImpl(personalInfoDao)
    }

    @Singleton
    @Provides
    fun provideWebsiteCredentialsRepository(websiteCredentialsDao: WebsiteCredentialsDao): WebsiteCredentialsRepository{
        return WebsiteCredentialsRepositoryImpl(websiteCredentialsDao)
    }

}