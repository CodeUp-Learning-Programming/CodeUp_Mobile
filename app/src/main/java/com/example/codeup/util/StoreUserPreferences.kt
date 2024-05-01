package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserPreferences(private val context: Context) {

    //Garante que só existe uma instancia
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserToken")
        val USER_TOKEN_KEY = stringPreferencesKey("bearerToken")
    }

    //Pega o Bearer Token
    val getToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: ""
    }

    // Salva o Bearer Token
    suspend fun saveToken(bearerToken: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = bearerToken
        }
    }
}