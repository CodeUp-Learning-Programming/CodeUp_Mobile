package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserGraficoExercicio private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "exercicios_feitos")
        private val FASE_KEY = stringPreferencesKey("exercicios_feitos_key")
        private var INSTANCE: StoreUserGraficoExercicio? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreUserGraficoExercicio {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreUserGraficoExercicio(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    // Retrieve the list of Fase objects
    val getListExercicios: Flow<Map<String, String>> = context.dataStore.data
        .map { preferences ->
            val json = preferences[FASE_KEY] ?: return@map emptyMap()
            gson.fromJson(json, object : TypeToken<Map<String, String>>() {}.type) ?: emptyMap<String, String>()
        }

    // Save the list of Fase objects
    suspend fun saveListExercicios(listaExerciciosFeitos: Map<String, String>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(listaExerciciosFeitos)
            preferences[FASE_KEY] = json
        }
    }
}
