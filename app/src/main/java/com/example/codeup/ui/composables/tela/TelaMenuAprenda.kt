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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun TelaMenuAprenda(
    usuario: Usuario,
    listaExercicios: List<Fase>,
    materia: Materia
) {
    MenuHome(
        "${R.drawable.tema_pontos}",
        materia.titulo,
        totalCoracoes = usuario.vidas,
        totalMoedas = usuario.moedas,
        totalSequencia = 5,
        conteudo = {
            val context = LocalContext.current

            // Variável de estado para controlar a visibilidade do pop-up
            val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
            // Índice do card selecionado
            var selectedCardIndex by remember { mutableStateOf(-1) }

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
                                    // Mostra o pop-up ao clicar no card
                                    setShowPopup(true)
                                    // Salva o índice do card selecionado
                                    selectedCardIndex = listaExercicios.indexOf(exercicio)
                                },
                            )

                        }

                        Spacer(modifier = Modifier.height(40.dp))
                        alinharDireita = !alinharDireita;


                    }
                }

            }
            // Se o pop-up estiver visível, mostra o pop-up correspondente ao card selecionado
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
                    // Interceptador de cliques para o pop-up
                    Box(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {}) // Vazio para não fazer nada quando o pop-up é clicado.
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