package com.example.codeup.ui.composables.tela

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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.card.CardAprenda
import com.example.codeup.ui.composables.card.CardExercicio
import com.example.codeup.ui.composables.menu.MenuHome
import com.example.codeup.ui.screens.TelaExercicio
import com.example.codeup.util.StoreFase
import kotlinx.coroutines.launch

@Composable
fun TelaMenuAprenda(
    usuario: Usuario,
    materia: Materia
) {
    val context = LocalContext.current
    val storeFase = StoreFase.getInstance(context)

    // Utilizando remember para evitar chamadas desnecessárias
    var listaExercicios by remember { mutableStateOf(emptyList<Fase>()) }
    val coroutineScope = rememberCoroutineScope()

    // Coleta de dados deve ser controlada por condições específicas
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeFase.getFases.collect { exercicios ->
                listaExercicios = exercicios
            }
        }
    }

    val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
    var selectedCardIndex by remember { mutableStateOf(-1) }


    MenuHome(
        "${R.drawable.tema_pontos}",
        materia.titulo,
        totalCoracoes = usuario.vidas,
        totalMoedas = usuario.moedas,
        totalSequencia = usuario.sequencia,
        conteudo = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                reverseLayout = false
            ) {
                var alinharDireita = true;
                items(listaExercicios) { exercicio ->
                    Column {
                        //Linha reta
                        Canvas(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
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

                        Row(
                            modifier = Modifier
                                .width(200.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = if (alinharDireita) Arrangement.End else Arrangement.Start
                        ) {
                            CardExercicio(
                                desbloqueada = exercicio.desbloqueada,
                                qtdExerciciosFase = exercicio.qtdExerciciosFase,
                                qtdExerciciosFaseConcluidos = exercicio.qtdExerciciosFaseConcluidos,
                                onClick = {
                                    setShowPopup(true)
                                    selectedCardIndex = listaExercicios.indexOf(exercicio)
                                },
                            )

                        }

                        Spacer(modifier = Modifier.height(40.dp))
                        alinharDireita = !alinharDireita;


                    }
                }

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
                            tituloFase = listaExercicios[selectedCardIndex].tituloFase,
                            desbloqueada = listaExercicios[selectedCardIndex].desbloqueada,
                            qtdExerciciosFase = listaExercicios[selectedCardIndex].qtdExerciciosFase,
                            qtdExerciciosFaseConcluidos = listaExercicios[selectedCardIndex].qtdExerciciosFaseConcluidos,
                            onClick = {
                                val telaExercicio = Intent(context, TelaExercicio::class.java)
                                context.startActivity(telaExercicio)
                            }
                        )
                    }
                }
            }
        }
    )
}