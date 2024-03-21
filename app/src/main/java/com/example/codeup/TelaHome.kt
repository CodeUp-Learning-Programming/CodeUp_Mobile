package com.example.codeup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.codeup.ui.theme.CodeupTheme

class TelaHome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                        Home("Android")

                }
            }
        }
    }
}





@Composable
fun Home(name: String, modifier: Modifier = Modifier) {
    ScaffoldExample("${R.mipmap.fundo}", totalCoracoes = 5, totalMoedas = 10, totalSequencia = 5)
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    CodeupTheme {
//        Home("Android")
//    }
//}
