package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.UsuarioRanking
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreRanking private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ranking")
        private val RANKING_KEY = stringPreferencesKey("ranking_key")
        private var INSTANCE: StoreRanking? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreRanking {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreRanking(context).also { INSTANCE = it }
            }
        }
    }

    // Retrieve the list of Fase objects
    val getRanking: Flow<List<UsuarioRanking>> = context.dataStore.data.map { preferences ->
        val json = preferences[RANKING_KEY] ?: return@map emptyList()
        gson.fromJson(json, object : TypeToken<List<UsuarioRanking>>() {}.type) ?: emptyList()
    }


    // Save the list of Fase objects
    suspend fun saveRanking(ranking: List<UsuarioRanking>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(ranking)
            preferences[RANKING_KEY] = json
        }
    }
}
