package com.example.codeup.ui.composables.tela

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.codeup.R
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuLoja(
    user: Usuario,
) {
    MenuPadrao(
        texto = stringResource(R.string.text_loja),
        conteudo = ({

        })
    )
}

