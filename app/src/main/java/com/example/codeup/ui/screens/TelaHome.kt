package com.example.codeup.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.BarraNavegacao
import com.example.codeup.ui.screens.viewmodels.FaseViewModel
import com.example.codeup.ui.theme.CodeupTheme

class TelaHome : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras


        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(0, 0),
            navigationBarStyle = SystemBarStyle.light(0, 0)
        )

        setContent {
            CodeupTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(extras = extras)
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Home(
    extras: Bundle?,
    modifier: Modifier = Modifier
) {
    val user = extras?.getSerializable("usuario") as? Usuario
    val faseViewModel = FaseViewModel(user?.token, 1)
    val fasesState = faseViewModel.fases.observeAsState()

    var fasesCarregadas by remember { mutableStateOf(false) }

    Log.d("Home", "fasesCarregadas: $fasesCarregadas, fasesState: ${fasesState.value}")

    if (!fasesCarregadas && fasesState.value.isNullOrEmpty()) {
        LaunchedEffect(faseViewModel) {
            Log.d("Home", "Buscando fases...")
//            faseViewModel.buscarFasePelaMateria(2)
            fasesCarregadas = true
        }
    }

    val fases = fasesState.value ?: emptyList()
    BarraNavegacao(rememberNavController(), user!!, fases, Materia(id = 1, titulo = "Algoritmos", url = ""))
}
