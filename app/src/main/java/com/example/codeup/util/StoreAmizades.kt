package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.Amigo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreAmizades private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("amigos")
        private val AMIGOS_KEY = stringPreferencesKey("amigos_key")
        private var INSTANCE: StoreAmizades? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreAmizades {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreAmizades(context).also { INSTANCE = it }
            }
        }
    }

    val getAmigos: Flow<List<Amigo>> = context.dataStore.data.map { preferences ->
        val json = preferences[AMIGOS_KEY] ?: return@map emptyList()
        gson.fromJson(json, object : TypeToken<List<Amigo>>() {}.type) ?: emptyList()
    }

    suspend fun saveAmigos(amigos: List<Amigo>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(amigos)
            preferences[AMIGOS_KEY] = json
        }
    }
}
