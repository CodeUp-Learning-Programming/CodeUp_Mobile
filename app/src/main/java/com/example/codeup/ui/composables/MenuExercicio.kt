package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(50.dp)
            ) {
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

            Divider (
                color = Color(40,40,40),
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(R.color.black),
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val painter1: Painter = painterResource(id = R.drawable.icon_aprenda)
                        val painter2: Painter = painterResource(id = R.drawable.icon_medalha)
                        val painter3: Painter = painterResource(id = R.drawable.icon_amigos)
                        val painter4: Painter = painterResource(id = R.drawable.icon_usuario)
                        val contentScale = ContentScale.FillBounds

                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painter1,
                                contentDescription = stringResource(R.string.text_aprenda),
                                modifier = Modifier

                            )
                            TextoBranco(
                                texto = stringResource(R.string.text_aprenda),
                                tamanhoFonte = 12,
                                pesoFonte = "normal"
                            )
                        }
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painter2,
                                contentDescription = stringResource(R.string.text_ranking),

                                )
                            TextoBranco(
                                texto = stringResource(R.string.text_ranking),
                                tamanhoFonte = 12,
                                pesoFonte = "normal"
                            )
                        }
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painter3,
                                contentDescription = stringResource(R.string.text_amigos),

                                )
                            TextoBranco(
                                texto = stringResource(R.string.text_amigos),
                                tamanhoFonte = 12,
                                pesoFonte = "normal"
                            )
                        }
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painter4,
                                contentDescription = stringResource(R.string.text_perfil),

                                )
                            TextoBranco(
                                texto = stringResource(R.string.text_perfil),
                                tamanhoFonte = 12,
                                pesoFonte = "normal"
                            )
                        }
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
            ImageBackgroundExample(backgroundImageResId = R.drawable.tema_pontos) {
                //Colocar conteudo aqui dinamicamente
                conteudo()
            }
        }
    }
}
