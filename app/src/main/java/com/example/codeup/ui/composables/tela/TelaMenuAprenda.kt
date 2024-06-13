package com.example.codeup.ui.composables.tela

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.data.Fase
import com.example.codeup.data.Materia
import com.example.codeup.data.UltimaMateriaAcessada
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.card.CardAprenda
import com.example.codeup.ui.composables.card.CardExercicio
import com.example.codeup.ui.composables.menu.MenuHome
import com.example.codeup.ui.screens.TelaExercicio
import com.example.codeup.ui.screens.viewmodels.AmizadeViewModel
import com.example.codeup.ui.screens.viewmodels.ExercicioViewModel
import com.example.codeup.ui.screens.viewmodels.FaseViewModel
import com.example.codeup.ui.screens.viewmodels.LojaViewModel
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.util.StoreFase
import com.example.codeup.util.StoreUserGraficoExercicio
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TelaMenuAprenda(
    usuario: Usuario,
    materia: Materia
) {
    val context = LocalContext.current
    val storeFase = StoreFase.getInstance(context)
    val storeUserGraficoExercicio = StoreUserGraficoExercicio.getInstance(context)

    // Utilizando remember para evitar chamadas desnecessárias
    var listaFases by remember { mutableStateOf(emptyList<Fase>()) }
    val coroutineScope = rememberCoroutineScope()

    var totalExercicios by remember { mutableIntStateOf(0) }
    var totalConcluidos by remember { mutableIntStateOf(0) }

    var atualizando by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing = atualizando, onRefresh = {

        coroutineScope.launch {
            atualizando = true
            //Atualizar lista de amigos
            val faseViewModel = FaseViewModel(usuario.token)
            val lojaViewModel = LojaViewModel(usuario.token)
            val amizadeViewModel = AmizadeViewModel(usuario.token)
            val usuarioViewModel = UsuarioViewModel(usuario.token)
                faseViewModel.buscarFasePelaMateria(1, context)
                lojaViewModel.carregarLoja(context)
                amizadeViewModel.listarAmigos(usuario.id, context)
                amizadeViewModel.solicitacoesRecebidas(usuario.id, context)
                usuarioViewModel.buscarExerciciosPorMes(usuario.id, context)
                usuarioViewModel.ranking(context)
                usuarioViewModel.atualizarPorId(usuario.id, context)

            delay(Random.nextLong(500, 3000))
            atualizando = false
        }

    })
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeFase.getFases.collect { exercicios ->
                listaFases = exercicios

                var totalExerciciosTemp = 0
                var totalConcluidosTemp = 0

                listaFases.forEach { fase ->
                    totalExerciciosTemp += fase.qtdExerciciosFase
                    totalConcluidosTemp += fase.qtdExerciciosFaseConcluidos
                }

                totalExercicios = totalExerciciosTemp
                totalConcluidos = totalConcluidosTemp

                storeUserGraficoExercicio.saveUltimaMateriaAcessada(
                    UltimaMateriaAcessada(
                        materia.titulo,
                        totalConcluidos,
                        totalExercicios
                    )
                )
            }
        }
    }

    val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
    var selectedCardIndex by remember { mutableStateOf(-1) }


    MenuHome(
        R.drawable.tema_estrela,
        materia.titulo,
        totalCoracoes = usuario.vidas,
        totalMoedas = usuario.moedas,
        totalSequencia = usuario.sequencia,
        conteudo = {
            Box {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp)
                        .pullRefresh(pullRefreshState),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    itemsIndexed(listaFases) { index, fase ->
                        Column {
                            //Linha reta
                            Canvas(
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                if (fase.qtdExerciciosFase == fase.qtdExerciciosFaseConcluidos && fase.qtdExerciciosFase != 0) {
                                    drawLine(
                                        color = Color(1, 169, 251),
                                        start = Offset(300f, 0f),
                                        end = Offset(300f, 500f),
                                        strokeWidth = 50f
                                    )
                                    drawLine(
                                        color = Color(1, 169, 251),
                                        start = Offset(300f, 0f),
                                        end = Offset(300f, 5500f),
                                        strokeWidth = 40f
                                    )
                                } else {
                                    drawLine(
                                        color = Color.Gray,
                                        start = Offset(300f, 0f),
                                        end = Offset(300f, 500f),
                                        strokeWidth = 50f
                                    )
                                    drawLine(
                                        color = Color.Black,
                                        start = Offset(300f, 0f),
                                        end = Offset(300f, 5500f),
                                        strokeWidth = 40f
                                    )
                                }

                            }



                            Row(
                                modifier = Modifier
                                    .width(200.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = if (index % 2 == 0) Arrangement.End else Arrangement.Start
                            ) {
                                CardExercicio(
                                    desbloqueada = fase.desbloqueada,
                                    qtdExerciciosFase = fase.qtdExerciciosFase,
                                    qtdExerciciosFaseConcluidos = fase.qtdExerciciosFaseConcluidos,
                                    onClick = {
                                        if(fase.desbloqueada){
                                            setShowPopup(true)
                                            selectedCardIndex = listaFases.indexOf(fase)
                                        }
                                    },
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }
                }

                PullRefreshIndicator(
                    refreshing = atualizando,
                    state = pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
            if (showPopup && selectedCardIndex != -1) {
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
                        CardAprenda(
                            tituloFase = listaFases[selectedCardIndex].tituloFase,
                            desbloqueada = listaFases[selectedCardIndex].desbloqueada,
                            qtdExerciciosFase = listaFases[selectedCardIndex].qtdExerciciosFase,
                            qtdExerciciosFaseConcluidos = listaFases[selectedCardIndex].qtdExerciciosFaseConcluidos,
                            onClick = {
                                coroutineScope.launch {
                                    val storeFases = StoreFase.getInstance(context);
                                    storeFases.saveFaseAtual(listaFases[selectedCardIndex])
                                }

                                val exercicioViewModel = ExercicioViewModel(usuario.token)
                                exercicioViewModel.buscarExerciciosPorIdFase(
                                    listaFases[selectedCardIndex].faseId,
                                    context
                                )


                                val telaExercicio = Intent(context, TelaExercicio::class.java)
                                context.startActivity(telaExercicio)
                            },
                        )
                    }
                }
            }
        }
    )
}
