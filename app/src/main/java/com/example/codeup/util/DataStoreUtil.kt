package com.example.codeup.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.codeup.dataStore

object DataStoreUtil {
    private const val DATA_STORE_NAME = "user_data"

    fun provideDataStore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}