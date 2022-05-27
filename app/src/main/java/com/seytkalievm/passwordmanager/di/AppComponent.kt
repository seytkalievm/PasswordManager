package com.seytkalievm.passwordmanager.di

import android.content.Context
import com.seytkalievm.passwordmanager.ui.auth.AuthActivity
import com.seytkalievm.passwordmanager.ui.auth.login.LogInFragment
import com.seytkalievm.passwordmanager.ui.auth.register.RegisterFragment
import com.seytkalievm.passwordmanager.ui.session.main.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: AuthActivity)
    fun inject(fragment: LogInFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: HomeFragment)
}