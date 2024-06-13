package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.Fase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreFase private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("fases")
        private val FASE_KEY = stringPreferencesKey("fase_key")
        private val FASE_ATUAL_KEY = stringPreferencesKey("fase_atual_key")
        private var INSTANCE: StoreFase? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreFase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreFase(context).also { INSTANCE = it }
            }
        }
    }

    // Retrieve the list of Fase objects
    val getFases: Flow<List<Fase>> = context.dataStore.data.map { preferences ->
        val json = preferences[FASE_KEY] ?: return@map emptyList()
        gson.fromJson(json, object : TypeToken<List<Fase>>() {}.type) ?: emptyList()
    }


    // Save the list of Fase objects
    suspend fun saveFases(fases: List<Fase>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(fases)
            preferences[FASE_KEY] = json
        }
    }


    // Retrieve the Usuario object
    val getFaseAtual: Flow<Fase?> = context.dataStore.data.map { preferences ->
        preferences[StoreFase.FASE_ATUAL_KEY]?.let { json ->
            StoreFase.gson.fromJson(json, Fase::class.java)
        }
    }

    // Save the Usuario object
    suspend fun saveFaseAtual(fase: Fase) {
        context.dataStore.edit { preferences ->
            preferences[StoreFase.FASE_ATUAL_KEY] = StoreFase.gson.toJson(fase)
        }
    }
}
