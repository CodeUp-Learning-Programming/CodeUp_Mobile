package com.example.codeup.ui.composables.tela

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.codeup.R
import com.example.codeup.data.UsuarioRanking
import com.example.codeup.ui.composables.listas.ListaRanking
import com.example.codeup.ui.composables.menu.MenuPadrao
import com.example.codeup.util.StoreRanking
import kotlinx.coroutines.launch

@Composable
fun TelaMenuRanking() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val storeRanking = StoreRanking.getInstance(context)

    var listaUsuariosRanking by remember { mutableStateOf(emptyList<UsuarioRanking>()) }


    LaunchedEffect(Unit) {
        coroutineScope.launch {
            storeRanking.getRanking.collect { retrievedUser ->
                listaUsuariosRanking = retrievedUser
            }
        }
    }

    MenuPadrao(
        titulo = stringResource(R.string.text_ranking),
        onClick = {
            Log.d("Tela Ranking", "Menu")
        },
        conteudo = ({
                ListaRanking(listaUsuariosRanking = listaUsuariosRanking)
        })
    )

}