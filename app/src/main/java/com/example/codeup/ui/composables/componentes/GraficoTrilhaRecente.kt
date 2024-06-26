package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codeup.data.UltimaMateriaAcessada

@Composable
fun GraficoTrilhaRecente(
    ultimaMateriaAcessada: UltimaMateriaAcessada
) {

    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )
    Box(
        modifier = Modifier
            .height(100.dp)
            .background(Color.Black)
            .border(1.dp, borderGradient, shape = RoundedCornerShape(8F)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Primeira linha
            Row(
                modifier = Modifier
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                TextoBranco(texto = "Trilha recente", tamanhoFonte = 14)
            }
            Column {
                //Parte e quantidade
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextoBranco(texto = ultimaMateriaAcessada.titulo, tamanhoFonte = 14)
                }
                //Barra de progresso
                Surface(
                    modifier = Modifier
                ) {
                    LinearProgressIndicator(
                        progress = { ultimaMateriaAcessada.totalConcluidos.toFloat() / ultimaMateriaAcessada.totalExercicios.toFloat() },
                        modifier = Modifier
                            .width(300.dp)
                            .height(8.dp),
                        color = Color(1, 167, 247),
                        trackColor = Color(49, 49, 49),
                    )

                }

            }

        }

    }

}