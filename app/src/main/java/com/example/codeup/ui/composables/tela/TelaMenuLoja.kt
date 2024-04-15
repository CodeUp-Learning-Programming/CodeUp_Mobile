package com.example.codeup.ui.composables.tela

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.codeup.R
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuLoja(
) {
    MenuPadrao(
        texto = stringResource(R.string.text_loja),
        conteudo = ({
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

            }
        })
    )
}

