package com.example.codeup.ui.composables.menu

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.composables.card.CardSair
import com.example.codeup.ui.composables.componentes.BotaoAzul
import com.example.codeup.ui.screens.TelaHome

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuExercicio(
    totalCoracoes: Int,
    onClickReportar: () -> Unit,
    onClickValidarResposta: () -> Unit,
    conteudo: @Composable () -> Unit,
    loading: Boolean = false
) {

    var mostrarPopup by remember { mutableStateOf(false) }
    val abrirPopup = { mostrarPopup = true }
    val fecharPopup = { mostrarPopup = false }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(80.dp)
                    .padding(top = 30.dp, start = 5.dp,end = 5.dp)
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

                        val painter2: Painter = painterResource(id = imagemCoracoes) // isso tem que ser dinamico
                        val reportar: Painter = painterResource(id = R.drawable.icon_bandeira)

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
                            modifier = Modifier.clickable(onClick = onClickReportar),
                            painter = reportar,
                            contentDescription = stringResource(R.string.text_icone_reportar),
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0, 0, 0),
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

                        BotaoAzul(modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.text_enviar).uppercase(), onClick = onClickValidarResposta, loading = loading)
                    }
                }
            }
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(13, 13, 13)),

            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            conteudo()
        }
    }

    // Pop-up para confirmar a ação
    if (mostrarPopup) {
        CardSair(
            onCancel = fecharPopup,
            onConfirm = {
                val home = Intent(context, TelaHome::class.java)
                context.startActivity(home)
            }
        )
    }
}
