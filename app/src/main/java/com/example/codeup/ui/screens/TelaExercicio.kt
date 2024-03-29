package com.example.codeup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codeup.ui.composables.MenuExercicio
import com.example.codeup.ui.composables.TextOpcaoPergunta
import com.example.codeup.ui.screens.ui.theme.CodeupTheme

class TelaExercicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {

    var isTextfieldFocused by remember { mutableStateOf(false) }

    MenuExercicio(totalCoracoes = 5) {
        TextOpcaoPergunta(texto = "MAAAAT", label = "maaaaaat", isTextFieldFocused = false) {}
        TextOpcaoPergunta(texto = "MAAAAT", label = "maaaaaat", isTextFieldFocused = true) {}
    }
}

