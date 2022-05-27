package com.seytkalievm.passwordmanager

import android.app.Application
import com.seytkalievm.passwordmanager.di.AppComponent
import com.seytkalievm.passwordmanager.di.DaggerAppComponent

class PasswordManagerApplication: Application() {

    val appComponent: AppComponent by lazy{
        DaggerAppComponent.factory().create(applicationContext)
    }

}