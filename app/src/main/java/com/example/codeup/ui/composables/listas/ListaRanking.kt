package com.example.codeup.ui.composables.listas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.UsuarioRanking
import com.example.codeup.ui.composables.componentes.TextoBranco
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListaRanking(
    listaUsuariosRanking: List<UsuarioRanking>
) {

    val coroutineScope = rememberCoroutineScope()

    var atualizando by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing = atualizando, onRefresh = {

        coroutineScope.launch {
            atualizando = true
            //Atualizar lista do Ranking

            delay(Random.nextLong(500, 3000))
            atualizando = false
        }

    })

    Box {
        LazyColumn(Modifier.pullRefresh(pullRefreshState)) {
            itemsIndexed(listaUsuariosRanking) { index, usuarioRanking ->

                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(
                            if (index % 2 == 0) Color(
                                33,
                                33,
                                33
                            ) else Color(
                                22,
                                22,
                                22
                            ),
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                    ) {
                        //Medalha
                        Row(
                            modifier = Modifier
                                .width(50.dp)
                                .height(100.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                val ouro: Painter =
                                    painterResource(id = R.drawable.icon_medalha_ouro)
                                val prata: Painter =
                                    painterResource(id = R.drawable.icon_medalha_prata)
                                val bronze: Painter =
                                    painterResource(id = R.drawable.icon_medalha_bronze)

                                if (index + 1 <= 3) {
                                    Image(
                                        painter = if (index + 1 == 1) ouro else if (index + 1 == 2) prata else bronze,
                                        contentDescription = null, // A descrição é opcional
//                                    modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    TextoBranco(
                                        texto = "${index + 1}",
                                        tamanhoFonte = 16,
                                        alinhamentoTexto = TextAlign.Center
                                    )
                                }

                            }
                        }

                        //Foto
                        Row(
                            modifier = Modifier
                                .width(80.dp)
                                .height(100.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .clip(CircleShape)
                            ) {
                                AsyncImage(
                                    model = "https://helpia.ai/wp-content/uploads/2023/11/bing-creator.jpeg",
                                    contentDescription = "astronauta",
                                )
                            }
                        }
                        //Nome
                        Row(
                            modifier = Modifier
                                .height(100.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextoBranco(
                                texto = usuarioRanking.nome,
                                tamanhoFonte = 12
                            )

                        }
                    }
                    //Pontuação
                    Row(
                        modifier = Modifier.padding(end = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextoBranco(texto = usuarioRanking.xp, tamanhoFonte = 16)
                    }

                }
            }
        }
        PullRefreshIndicator(
            refreshing = atualizando,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}