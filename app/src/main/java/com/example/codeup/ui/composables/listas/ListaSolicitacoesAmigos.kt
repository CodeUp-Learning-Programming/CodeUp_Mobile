package com.example.codeup.ui.composables.listas

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.AmizadeRequestReceived
import com.example.codeup.data.RespostaSolicitacao
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.screens.viewmodels.AmizadeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListaSolicitacoesAmigos(
    listaAmigos: List<AmizadeRequestReceived>,
    usuario: Usuario,
    imagem: Int = 0,
) {
    val context = LocalContext.current
    var contador = 0
    var atualizando by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(refreshing = atualizando, onRefresh = {

        coroutineScope.launch {
            atualizando = true
            //Atualizar lista de amigos
            val amizadeViewModelViewModel = AmizadeViewModel(usuario.token)
            amizadeViewModelViewModel.listarAmigos(usuario.id!!, context)
            delay(Random.nextLong(500, 3000))
            atualizando = false
        }

    })

    Box() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            items(listaAmigos) { amigo ->
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(
                            if (contador % 2 == 0) Color(
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
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
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
                                    model = amigo.foto,
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
                                texto = amigo.nome,
                                tamanhoFonte = 12
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .height(20.dp)
                            .width(80.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val cancelarAddAmigo: Painter =
                            painterResource(id = R.drawable.icon_rejeitar)

                        val addAmigo: Painter = if (amigo.status == "PENDENTE") {
                            // Ícone para solicitação pendente
                            painterResource(id = R.drawable.icon_aceitar)
                        } else {
                            // Ícone para enviar solicitação
                            painterResource(id = imagem)
                        }

                            Image(
                                modifier = Modifier
                                    .clickable(onClick = {
                                            //Aceitar amizade
                                        Log.d("AMIZADE", "AMIZADE ACEITA")
                                            val amizadeViewModelViewModel =
                                                AmizadeViewModel(usuario.token)
                                            amizadeViewModelViewModel.gerenciarConvite(
                                                RespostaSolicitacao(
                                                    idReceptor = usuario.id!!,
                                                    emailSolicitante = amigo.email,
                                                    resposta = true
                                                ), context
                                            )
                                        amizadeViewModelViewModel.listarAmigos(
                                            usuario.id!!, context
                                        )
                                    }),
                                painter = addAmigo,
                                contentDescription = null, // A descrição é opcional
                            )

                            Image(
                                modifier = Modifier
                                    .clickable(onClick = {
                                        //Cancelar amizade
                                        val amizadeViewModelViewModel =
                                            AmizadeViewModel(usuario.token)
                                        amizadeViewModelViewModel.gerenciarConvite(
                                            RespostaSolicitacao(
                                                idReceptor = usuario.id!!,
                                                emailSolicitante = amigo.email,
                                                resposta = false
                                            ), context
                                        )
                                    }),
                                painter = cancelarAddAmigo,
                                contentDescription = null, // A descrição é opcional
                            )

                    }

                }
                contador++
            }
        }
        PullRefreshIndicator(
            refreshing = atualizando,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}
