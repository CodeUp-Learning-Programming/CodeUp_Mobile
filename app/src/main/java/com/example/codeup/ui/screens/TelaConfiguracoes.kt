package com.example.codeup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.tela.TelaMenuConfiguracoes
import com.example.codeup.ui.screens.ui.theme.CodeupTheme

class TelaConfiguracoes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(0, 0),
            navigationBarStyle = SystemBarStyle.light(0, 0)
        )

        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Configuracoes(extras)
                }
            }
        }
    }
}

@Composable
fun Configuracoes(
    extras: Bundle?,
    modifier: Modifier = Modifier
) {

    val user = extras?.getSerializable("usuario") as? Usuario
    if(user != null){
        TelaMenuConfiguracoes(user)

    }
}

