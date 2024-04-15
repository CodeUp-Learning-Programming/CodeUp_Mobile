package com.example.codeup

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.codeup.ui.screens.Login
import com.example.codeup.ui.theme.CodeupTheme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                0, 0
            ),
            navigationBarStyle = SystemBarStyle.light(
                0, 0
            )
        )
        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(13, 13, 13)
                ) {
                    Login()
                }
            }
        }
    }
}