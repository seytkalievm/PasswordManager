package com.seytkalievm.passwordmanager.di

import android.content.ClipboardManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object SystemModule {

    @Singleton
    @Provides
    fun provideClipboardManager(@ApplicationContext context: Context): ClipboardManager{
        return  context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
}