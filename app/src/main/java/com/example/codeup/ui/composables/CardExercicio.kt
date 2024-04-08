package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R

@Composable
fun CardExercicio(
    desbloqueada: Boolean = false,
    qtdExerciciosFaseConcluidos: Int = 0,
    qtdExerciciosFase: Int = 0,
    onClick: () -> Unit
) {

    val borderGradientdesbloqueada = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )

    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(92, 92, 92),
            Color(92, 92, 92),
        )
    )

    val borda = if (desbloqueada) borderGradientdesbloqueada else borderGradient

    Box(
        modifier = Modifier
            .width(130.dp)
            .height(100.dp)
            .background(Color.Black) // Cor de fundo do cartão
            .border(2.dp, borda, shape = RoundedCornerShape(8F)) // Borda com gradiente
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!desbloqueada) {
                val painter1: Painter = painterResource(id = R.drawable.icon_cadeado)
                Image(
                    painter = painter1,
                    contentDescription = stringResource(R.string.text_descricao_materia),
                )
            } else {
                Row() {
                    TextoBranco("$qtdExerciciosFaseConcluidos/$qtdExerciciosFase", 36, "normal")
                }
            }

        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CodeupTheme {
        Card(false, 2, 10)
    }
}
*/
