package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.Loja
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreLoja private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("loja_atual")
        private val LOJA_KEY = stringPreferencesKey("loja_key")
        private var INSTANCE: StoreLoja? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreLoja {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreLoja(context).also { INSTANCE = it }
            }
        }
    }

    // Retrieve the Usuario object
    val getLoja: Flow<Loja?> = context.dataStore.data.map { preferences ->
        preferences[LOJA_KEY]?.let { json ->
            gson.fromJson(json, Loja::class.java)
        }
    }

    // Save the Usuario object
    suspend fun saveLoja(loja: Loja) {
        context.dataStore.edit { preferences ->
            preferences[LOJA_KEY] = gson.toJson(loja)
        }
    }
}
