package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuExercicio(
    totalCoracoes: Int,
    conteudo: @Composable () -> Unit
) {

    var mostrarPopup by remember { mutableStateOf(false) }

    val abrirPopup = { mostrarPopup = true }
    val fecharPopup = { mostrarPopup = false }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(50.dp)
                    .padding(all = 5.dp)
            ) {

                Column {
                    //Linha de cima
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val painter1: Painter = painterResource(id = R.drawable.icon_sair)

                        val imagemCoracoes = when (totalCoracoes) {
                            0 -> R.drawable.icon_cinco_coracoes_vazios
                            1 -> R.drawable.icon_um_coracao
                            2 -> R.drawable.icon_dois_coracoes
                            3 -> R.drawable.icon_tres_coracoes
                            4 -> R.drawable.icon_quatro_coracoes
                            5 -> R.drawable.icon_cinco_coracoes
                            else -> R.drawable.icon_cinco_coracoes_vazios
                        }

                        val painter2: Painter =
                            painterResource(id = imagemCoracoes) // isso tem que ser dinamico
                        val painter3: Painter = painterResource(id = R.drawable.icon_bandeira)

                        Image(
                            modifier = Modifier.clickable(onClick = abrirPopup),
                            painter = painter1,
                            contentDescription = stringResource(R.string.text_descricao_materia),
                        )


                        Image(
                            painter = painter2,
                            contentDescription = stringResource(R.string.text_descricao_materia),
                        )
                        Image(
                            painter = painter3,
                            contentDescription = stringResource(R.string.text_descricao_materia),
                        )

                    }

                }

            }

        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(R.color.black),
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            start =
                            50.dp, end = 50.dp
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BotaoAzul(modifier = Modifier
                            .fillMaxWidth(), text = "ENVIAR", onClick = { /*TODO*/ })
                    }
                }
            }
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImageBackgroundExample(backgroundImageResId = R.drawable.tema_padrao) {
                //Colocar conteudo aqui dinamicamente
                conteudo(

                )
            }
        }
    }

    // Pop-up para confirmar a ação
    if (mostrarPopup) {
        CardSair(
            onCancel = fecharPopup,
            onConfirm = { /* Lógica para confirmar a ação */ }
        )
    }

}
