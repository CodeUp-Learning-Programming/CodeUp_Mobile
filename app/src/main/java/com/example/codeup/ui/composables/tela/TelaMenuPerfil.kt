package com.example.codeup.ui.composables.tela

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.card.CardExperimentarPro
import com.example.codeup.ui.composables.card.CardPerfil
import com.example.codeup.ui.composables.componentes.BotaoAzulClaro
import com.example.codeup.ui.composables.componentes.GraficoLinha
import com.example.codeup.ui.composables.componentes.GraficoTrilhaRecente
import com.example.codeup.ui.composables.componentes.MyNotification
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.menu.MenuPadrao
import com.example.codeup.ui.screens.TelaConfiguracoes
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.util.StoreUserGraficoExercicio
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TelaMenuPerfil(
    usuario: Usuario,
) {
    val context = LocalContext.current
    var listaExercicios by remember { mutableStateOf<Map<String,String>?>(null) }

    val coroutineScope = rememberCoroutineScope()
    val storeUserGraficoExercicio = StoreUserGraficoExercicio.getInstance(context)

    // Observe and collect user data from DataStore
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeUserGraficoExercicio.getListExercicios.collect { retrievedUser ->
                listaExercicios = retrievedUser
            }
        }
    }
    var atualizando by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(refreshing = atualizando, onRefresh = {

        coroutineScope.launch {
            atualizando = true
            val buscarExerciciosPorMes = UsuarioViewModel(usuario.token)
            buscarExerciciosPorMes.buscarExerciciosPorMes(usuario.id!!,context)
            delay(Random.nextLong(500, 3000))
            atualizando = false
        }

    })
    MenuPadrao(
        titulo = stringResource(R.string.text_perfil),
        imagem = R.drawable.icon_configurar,
        onClick = {
            val telaConfiguracoes = Intent(context, TelaConfiguracoes::class.java)
            context.startActivity(telaConfiguracoes)
        },
        conteudo = ({
            val (showPopup, setShowPopup) = remember { mutableStateOf(false) }

            Box(modifier = Modifier.fillMaxSize().pullRefresh(state = pullRefreshState)) {

                //Geral
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    //Perfil
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Foto
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clip(CircleShape)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(150.dp),
                                model = usuario.fotoPerfil,
                                contentDescription = stringResource(R.string.text_foto_perfil),
                            )
                        }
                        //
                        TextoBranco(texto = usuario.nome, tamanhoFonte = 16, pesoFonte = "Titulo")
                        BotaoAzulClaro(
                            modifier = Modifier,
                            text = stringResource(R.string.text_experimente_pro).uppercase(),
                            onClick = {
//                                setShowPopup(true)
//                                Log.d("PERFIL", "Exibindo pop up")
                                val notification = MyNotification(context, "TESTE", "EH O MATH")
                                notification.fireNotification()
                            })
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        CardPerfil(
                            R.drawable.icon_fogo_azul,
                            R.string.text_icon_fogo_azul,
                            usuario.sequencia.toString(),
                            stringResource(R.string.text_sequencia)
                        )
                        CardPerfil(
                            R.drawable.icon_estrela,
                            R.string.text_icon_fogo_azul,
                            "2",
                            stringResource(R.string.text_ranking)
                        )
                        CardPerfil(
                            R.drawable.icon_moeda,
                            R.string.text_icon_fogo_azul,
                            usuario.moedas.toString(),
                            stringResource(R.string.text_moedas)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .width(100.dp)
                                .background(Color(24, 24, 24))
                        )
                        Row(
                            modifier = Modifier
                                .width(100.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextoBranco(
                                texto = stringResource(R.string.text_dashboard),
                                tamanhoFonte = 12
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .width(100.dp)
                                .background(Color(24, 24, 24))
                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GraficoTrilhaRecente(
                            Materia(
                                id = 1,
                                titulo = "Algoritmo",
                                url = ""
                            )
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .background(Color(24, 24, 24))
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                   
                        GraficoLinha(titulo = stringResource(R.string.text_exercicios_feitos))

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                                .background(Color(24, 24, 24))
                        )
                        GraficoLinha(titulo = stringResource(R.string.text_maior_ranking))
                    }
                    Spacer(
                        modifier = Modifier
                            .height(50.dp)
                            .background(Color(24, 24, 24))
                    )

                }

                if (showPopup) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { setShowPopup(false) }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {})
                        ) {
                            CardExperimentarPro(
                                onClickFechar = { setShowPopup(false) },
                                onClickComprar = { setShowPopup(false) }
                            )
                        }
                    }
                }


            }
        })

    )

}