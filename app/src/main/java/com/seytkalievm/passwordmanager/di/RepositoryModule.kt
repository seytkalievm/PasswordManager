package com.seytkalievm.passwordmanager.di

import android.content.Context
import com.seytkalievm.passwordmanager.data.AuthRepository
import com.seytkalievm.passwordmanager.data.UserPreferencesRepository
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
    ): UserPreferencesRepository{
        return UserPreferencesRepository(context)
    }

    @Singleton
    @Provides
    fun provideAuthRepository( @ApplicationContext context: Context): AuthRepository{
        return AuthRepository(context)
    }
}