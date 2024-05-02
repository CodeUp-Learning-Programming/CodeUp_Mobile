package com.example.codeup.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.composables.componentes.TextoBranco

@Composable
fun CardExercicio(
    desbloqueada: Boolean = false,
    qtdExerciciosFaseConcluidos: Int = 0,
    qtdExerciciosFase: Int = 0,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val borderGradientdesbloqueada = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )

    val borderGradientcompleta = Brush.horizontalGradient(
        colors = listOf(
            Color(1, 169, 247),
            Color(1, 169, 247),
        )
    )

    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(92, 92, 92),
            Color(92, 92, 92),
        )
    )
    var faseCompleta by remember { mutableStateOf(false) }

    if (qtdExerciciosFaseConcluidos > 0 && qtdExerciciosFase > 0 && qtdExerciciosFaseConcluidos == qtdExerciciosFase) {
        faseCompleta = true
    }

    val borda = if (desbloqueada && !faseCompleta) borderGradientdesbloqueada else if(faseCompleta) borderGradientcompleta else borderGradient

    Box(
        modifier = Modifier
            .width(130.dp)
            .height(100.dp)
            .background(
                if (!faseCompleta) Color.Black else Color(
                    1,
                    169,
                    247
                ), shape = RoundedCornerShape(8F)
            ) // Cor de fundo do cartão
            .border(2.dp, borda, shape = RoundedCornerShape(8F)) // Borda com gradiente
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
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
                    contentDescription = stringResource(R.string.text_exercicio_bloqueado),
                )
            } else if (faseCompleta) {
                val painter1: Painter = painterResource(id = R.drawable.icon_check)
                Image(
                    painter = painter1,
                    contentDescription = stringResource(R.string.text_check),
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
