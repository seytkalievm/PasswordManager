package com.seytkalievm.passwordmanager.di

import android.content.Context
import com.seytkalievm.passwordmanager.data.credit_card.CreditCardDao
import com.seytkalievm.passwordmanager.data.personal_info.PersonalInfoDao
import com.seytkalievm.passwordmanager.data.storage.AppDatabase
import com.seytkalievm.passwordmanager.data.website_credentials.WebsiteCredentialsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCreditCardDao(db: AppDatabase): CreditCardDao{
        return db.creditCardDao()
    }

    @Singleton
    @Provides
    fun provideWebsiteCredentialsDao(db: AppDatabase): WebsiteCredentialsDao{
        return db.websiteCredentialsDao()
    }

    @Singleton
    @Provides
    fun providePersonalInfoDao(db: AppDatabase): PersonalInfoDao{
        return db.personalInfoDao()
    }
}