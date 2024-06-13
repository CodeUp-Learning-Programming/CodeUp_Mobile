package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioAtualizado
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUser private constructor(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("usuario_preferences")
        private val USER_KEY = stringPreferencesKey("user_key")
        private var INSTANCE: StoreUser? = null
        private val gson = Gson()

        fun getInstance(context: Context): StoreUser {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StoreUser(context).also { INSTANCE = it }
            }
        }
    }

    // Retrieve the Usuario object
    val getUsuario: Flow<Usuario?> = context.dataStore.data.map { preferences ->
        preferences[USER_KEY]?.let { json ->
            gson.fromJson(json, Usuario::class.java)
        }
    }

    // Save the Usuario object
    suspend fun saveUsuario(usuario: Usuario) {
        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = gson.toJson(usuario)
        }
    }

    suspend fun atualizarUsuario(id: Int, usuarioAtualizado: UsuarioAtualizado) {
        context.dataStore.edit { preferences ->
            val usuarioJson = preferences[USER_KEY] ?: return@edit
            val usuario = gson.fromJson(usuarioJson, Usuario::class.java)

            if (usuario.id == id) {
                val novoUsuario = usuario.copy(
                    moedas = usuarioAtualizado.moedas,
                    nivel = usuarioAtualizado.nivel,
                    xp = usuarioAtualizado.xp,
                    itensAdquiridos = usuarioAtualizado.itensAdquiridos ?: usuario.itensAdquiridos
                )
                preferences[USER_KEY] = gson.toJson(novoUsuario)
            }
        }
    }
}
