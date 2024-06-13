package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.Amigo
import com.example.codeup.data.AmizadeRequestReceived
import com.example.codeup.data.BuscarPorNomeResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreAmizades private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("amigos")
        private val AMIGOS_KEY = stringPreferencesKey("amigos_key")
        private val PEDIDOS_AMIZADE_KEY = stringPreferencesKey("pedidos_amizade_key")
        private val LISTA_AMIZADE_KEY = stringPreferencesKey("lista_amizade_key")
        private var INSTANCE: StoreAmizades? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreAmizades {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreAmizades(context).also { INSTANCE = it }
            }
        }
    }

    // Lista de Amigos
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

    // Lista de Pedidos de Amizade
    val getPedidoAmizade: Flow<List<AmizadeRequestReceived>> = context.dataStore.data.map { preferences ->
        val json = preferences[PEDIDOS_AMIZADE_KEY] ?: return@map emptyList()
        gson.fromJson(json, object : TypeToken<List<AmizadeRequestReceived>>() {}.type) ?: emptyList()
    }

    suspend fun savePedidosAmizade(amigos: List<AmizadeRequestReceived>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(amigos)
            preferences[PEDIDOS_AMIZADE_KEY] = json
        }
    }

    // Quantidade de Pedidos de Amizade
    val getQtdPedidoAmizade: Flow<Int> = getPedidoAmizade.map { it.size }

    val getListaBuscarAmigos: Flow<List<BuscarPorNomeResult>> = context.dataStore.data.map { preferences ->
        val json = preferences[LISTA_AMIZADE_KEY] ?: return@map emptyList()
        gson.fromJson(json, object : TypeToken<List<BuscarPorNomeResult>>() {}.type) ?: emptyList()
    }

    suspend fun saveListaBuscarAmigos(amigos: List<BuscarPorNomeResult>) {
        context.dataStore.edit { preferences ->
            val json = gson.toJson(amigos)
            preferences[LISTA_AMIZADE_KEY] = json
        }
    }
}
