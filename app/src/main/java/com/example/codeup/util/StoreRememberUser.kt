package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreRememberUser(private val context: Context) {

    //Garante que só existe uma instancia
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("User")
        val USER_EMAIL_KEY = stringPreferencesKey("email")
        val USER_PASSWORD_KEY = stringPreferencesKey("senha")

    }

    //Pega o Email
    val getEmail: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL_KEY] ?: ""
    }

    //Pega a Senha
    val getPassword: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_PASSWORD_KEY] ?: ""
    }

    // Salva o email
    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    // Salva a senha
    suspend fun savePassword(password: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = password
        }
    }
}