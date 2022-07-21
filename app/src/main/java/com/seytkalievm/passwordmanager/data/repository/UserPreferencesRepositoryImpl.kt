package com.seytkalievm.passwordmanager.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.seytkalievm.passwordmanager.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "passcode_store")

private val PASSCODE = stringPreferencesKey("passcode")

class UserPreferencesRepositoryImpl constructor(val context: Context): UserPreferencesRepository{

   override val passcode: Flow<String> = context.dataStore.data
        .map {
            it[PASSCODE] ?: "null"
        }

    override suspend fun setPasscode(passcode: String){
        context.dataStore.edit{passcode_store ->
            passcode_store[PASSCODE] = passcode
        }
        Log.i("UserPreferencesRepo", passcode)
    }


}