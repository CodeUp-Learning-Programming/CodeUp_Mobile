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
fun Menu(
    imagem: String,
    nomeMateria: String,
    totalCoracoes: Int,
    totalSequencia: Int,
    totalMoedas: Int,
    conteudo: @Composable () -> Unit
) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(140.dp)
                    .padding(top = 50.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .width(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Linha de cima
                    Row(
                        modifier = Modifier
                            .width(350.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //materia
                        Row {
                            val painter: Painter =
                                painterResource(id = R.drawable.icon_javascript_logo)
                            val contentScale = ContentScale.FillBounds

                            Image(
                                painter = painter,
                                contentDescription = stringResource(R.string.text_descricao_materia),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .width(170.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //coracao
                            //fogo
                            //moedas
                            val painter1: Painter =
                                painterResource(id = R.drawable.icon_coracao_cheio)
                            val painter2: Painter = painterResource(id = R.drawable.icon_fogo_azul)
                            val painter3: Painter = painterResource(id = R.drawable.icon_moeda)
                            val contentScale = ContentScale.FillBounds
                            Row(
                                modifier = Modifier.width(50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(
                                    texto = "${totalCoracoes}",
                                    tamanhoFonte = 24,
                                    pesoFonte = "normal"
                                )
                                Image(
                                    painter = painter1,
                                    contentDescription = stringResource(R.string.text_descricao_materia),
                                )
                            }
                            Row(
                                modifier = Modifier.width(50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(
                                    texto = "${totalSequencia}",
                                    tamanhoFonte = 24,
                                    pesoFonte = "normal"
                                )
                                Image(
                                    painter = painter2,
                                    contentDescription = stringResource(R.string.text_descricao_materia),
                                )
                            }
                            Row(
                                modifier = Modifier.width(50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(
                                    texto = "${totalMoedas}",
                                    tamanhoFonte = 24,
                                    pesoFonte = "normal"
                                )
                                Image(
                                    painter = painter3,
                                    contentDescription = stringResource(R.string.text_descricao_materia),
                                )
                            }

                        }
                    }
                    //Linha de baixo
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //menu hamburg
                        //materia
                        TextoBranco(
                            texto = nomeMateria,
                            tamanhoFonte = 20,
                            pesoFonte = "normal"
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
                        val painter1: Painter = painterResource(id = R.drawable.icon_aprenda_selecionado)
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


@Composable
fun ImageBackgroundExample(
    backgroundImageResId: Int,
    content: @Composable () -> Unit
) {
    val painter: Painter = painterResource(id = backgroundImageResId)
    val contentScale = ContentScale.FillBounds

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painter,
            contentDescription = null, // A descrição é opcional
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}