package com.example.codeup.ui.screens

import BarraNavegacao
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.navigation.compose.rememberNavController
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.screens.viewmodels.FaseViewModel
import com.example.codeup.ui.screens.viewmodels.LojaViewModel
import com.example.codeup.ui.theme.CodeupTheme
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.launch

class TelaHome : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(0, 0),
            navigationBarStyle = SystemBarStyle.light(0, 0)
        )
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContent {
            CodeupTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Home()
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Home() {
    val navController = rememberNavController()

    val context = LocalContext.current
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    val materia = remember { Materia(id = 1, titulo = "Algoritmos", url = "") }

    val coroutineScope = rememberCoroutineScope()
    val storeUser = StoreUser.getInstance(context)

    // Observe and collect user data from DataStore
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeUser.getUsuario.collect { retrievedUser ->
                usuario = retrievedUser
            }
        }
    }

    usuario?.let { it ->
        val faseViewModel = FaseViewModel(it.token)
        faseViewModel.buscarFasePelaMateria(1, context)

        val lojaViewModel = LojaViewModel(it.token)
        lojaViewModel.carregarLoja(context)

        BarraNavegacao(
            navController = navController,
            usuario = it,
            materia = materia,
        )
    }
}