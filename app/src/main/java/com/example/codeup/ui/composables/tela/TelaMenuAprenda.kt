package com.example.codeup.ui.composables.tela

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
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.data.Usuario
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.composables.card.CardExercicio
import com.example.codeup.ui.composables.card.CardPopup
import com.example.codeup.ui.composables.menu.MenuHome

@Composable
fun TelaMenuAprenda(
    user: Usuario,
    listaExercicios: List<DadosDoCard>

) {
    val interactionSource = remember { MutableInteractionSource() }

    MenuHome(
        "${R.drawable.tema_pontos}",
        user.nome,
        totalCoracoes = 5,
        totalMoedas = user.moedas,
        totalSequencia = 5,
        conteudo = {

            // Variável de estado para controlar a visibilidade do pop-up
            val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
            // Índice do card selecionado
            var selectedCardIndex by remember { mutableStateOf(-1) }

            var i = 0;
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
                                    //exibir card
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
                        .background(Color(13, 13, 13).copy(alpha = 0.2f))
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = {
                                // Fecha o pop-up ao clicar no botão de fechar
                                setShowPopup(false)
                                // Reseta o índice do card selecionado
                                selectedCardIndex = -1
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CardPopup(
                        tituloFase = listaExercicios[selectedCardIndex].tituloFase,
                        desbloqueada = listaExercicios[selectedCardIndex].desbloqueada,
                        qtdExerciciosFase = listaExercicios[selectedCardIndex].qtdExerciciosFase,
                        qtdExerciciosFaseConcluidos = listaExercicios[selectedCardIndex].qtdExerciciosFaseConcluidos,
                    )
                }

            }
        }
    )
}