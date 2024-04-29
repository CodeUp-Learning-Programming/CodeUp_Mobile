package com.example.codeup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.tela.TelaMenuConfiguracoes
import com.example.codeup.ui.screens.ui.theme.CodeupTheme
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.launch

class TelaConfiguracoes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                0, 0
            ), navigationBarStyle = SystemBarStyle.light(
                0, 0
            )
        )

        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color(13, 13, 13)

                ) {
                    Configuracoes()
                }
            }
        }
    }
}

@Composable
fun Configuracoes() {
    val context = LocalContext.current
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val storeUser = StoreUser.getInstance(context)

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeUser.getUsuario.collect { retrievedUser ->
                usuario = retrievedUser
            }
        }
    }

    usuario?.let { usuario ->
        TelaMenuConfiguracoes(usuario)

    }

}

