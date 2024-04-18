package com.example.codeup.ui.composables.tela

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.codeup.R
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.listas.ListaLoja
import com.example.codeup.ui.composables.menu.MenuLoja
import com.example.codeup.ui.screens.viewmodels.LojaViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TelaMenuLoja(
    usuario: Usuario,
    lojaViewModel: LojaViewModel = LojaViewModel(usuario.token)
) {
    val loja = lojaViewModel.loja.observeAsState().value

    MenuLoja(
        texto = stringResource(R.string.text_loja),
        moedas = 1,
        conteudo = ({
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (loja != null) {
                    val itensPorTipo = loja.itensLoja.groupBy { it.tipoItem }

                    itensPorTipo.forEach { (tipoItem, itens) ->
                        ListaLoja(categoria = tipoItem, listaItemLoja = itens)
                    }
                } else {
                }
            }
        })
    )
}

