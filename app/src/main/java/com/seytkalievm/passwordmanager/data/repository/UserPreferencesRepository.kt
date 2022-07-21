package com.seytkalievm.passwordmanager.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "passcode_store")

private val PASSCODE = stringPreferencesKey("passcode")

class UserPreferencesRepository constructor(val context: Context){

    val passcodeFlow: Flow<String> = context.dataStore.data
        .map {
            it[PASSCODE] ?: "null"
        }

    suspend fun setPasscode(passcode: String){
        context.dataStore.edit{passcode_store ->
            passcode_store[PASSCODE] = passcode
        }
        Log.i("UserPreferencesRepo", passcode)
    }


}