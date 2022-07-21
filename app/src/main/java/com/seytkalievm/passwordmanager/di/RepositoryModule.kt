package com.seytkalievm.passwordmanager.di

import android.content.Context
import com.seytkalievm.passwordmanager.data.repository.AuthRepositoryImpl
import com.seytkalievm.passwordmanager.data.repository.UserPreferencesRepositoryImpl
import com.seytkalievm.passwordmanager.domain.repository.AuthRepository
import com.seytkalievm.passwordmanager.domain.repository.UserPreferencesRepository
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
}