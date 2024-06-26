package com.example.codeup.ui.screens

import BarraNavegacao
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.codeup.R
import com.example.codeup.data.Materia
import com.example.codeup.data.NotificacaoNavBar
import com.example.codeup.data.Usuario
import com.example.codeup.ui.screens.viewmodels.AmizadeViewModel
import com.example.codeup.ui.screens.viewmodels.FaseViewModel
import com.example.codeup.ui.screens.viewmodels.LojaViewModel
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.ui.theme.CodeupTheme
import com.example.codeup.util.StoreAmizades
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.launch

class TelaHome : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    val storeAmizade = StoreAmizades.getInstance(context)
    var limparListaNoLogin by remember { mutableStateOf(false) }

    if(!limparListaNoLogin){
        limparListaNoLogin = true
        coroutineScope.launch {
            storeAmizade.saveListaBuscarAmigos(emptyList())
        }
    }
    var qtdPedidoAmizade by remember { mutableStateOf(0) }

    // Observe and collect user data from DataStore
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            storeUser.getUsuario.collect { retrievedUser ->
                usuario = retrievedUser
            }
        }

        coroutineScope.launch {
            storeAmizade.getQtdPedidoAmizade.collect { qtdPedidos ->
                qtdPedidoAmizade = qtdPedidos
            }
        }
    }

    usuario?.let { user ->
        val faseViewModel = remember { FaseViewModel(user.token) }
        val lojaViewModel = remember { LojaViewModel(user.token) }
        val amizadeViewModel = remember { AmizadeViewModel(user.token) }
        val usuarioViewModel = remember { UsuarioViewModel(user.token) }

        LaunchedEffect(Unit) {
            faseViewModel.buscarFasePelaMateria(1, context)
            lojaViewModel.carregarLoja(context)
            amizadeViewModel.listarAmigos(user.id!!, context)
            amizadeViewModel.solicitacoesRecebidas(user.id!!, context)
            usuarioViewModel.buscarExerciciosPorMes(user.id!!, context)
            usuarioViewModel.ranking(context)
        }

        BarraNavegacao(
            navController = navController,
            usuario = user,
            materia = materia,
            listOf(
                NotificacaoNavBar(
                    stringResource(id = R.string.text_aprenda),
                ),
                NotificacaoNavBar(
                    stringResource(id = R.string.text_ranking),
                ),
                NotificacaoNavBar(
                    stringResource(id = R.string.text_amigos),
                    totalNotificacoes = qtdPedidoAmizade
                ),
                NotificacaoNavBar(
                    stringResource(id = R.string.text_loja),
                ),
                NotificacaoNavBar(
                    stringResource(id = R.string.text_perfil),
                )
            )
        )
    }
}
