package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.Exercicio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreExercicio private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("exercicios")
        private val FASE_KEY = stringPreferencesKey("exercicio_key")
        private var INSTANCE: StoreExercicio? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreExercicio {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreExercicio(context).also { INSTANCE = it }
            }
        }
    }

    val getExercicio: Flow<List<Exercicio>> = context.dataStore.data.map { preferences ->
        val json = preferences[FASE_KEY] ?: return@map emptyList()
        gson.fromJson(json, object : TypeToken<List<Exercicio>>() {}.type) ?: emptyList()
    }


    suspend fun saveExercicios(exercicios: List<Exercicio>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(exercicios)
            preferences[FASE_KEY] = json
        }
    }
}
