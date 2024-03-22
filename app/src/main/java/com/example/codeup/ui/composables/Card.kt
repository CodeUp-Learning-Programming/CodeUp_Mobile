package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.theme.CodeupTheme

@Composable
fun Card(bloqueado: Boolean, totalExerciciosConcluidos: Int, totalExercicios: Int) {
    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )

    Box(
        modifier = Modifier
            .width(130.dp)
            .height(100.dp)
            .background(Color.Black) // Cor de fundo do cartão
            .border(2.dp, borderGradient, shape = RoundedCornerShape(8F)) // Borda com gradiente
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(bloqueado){
                val painter1: Painter = painterResource(id = R.mipmap.cadeado)
                Image(
                    painter = painter1,
                    contentDescription = stringResource(R.string.text_descricao_materia),
                )
            }else{
                Row(){
                    TextoBranco("$totalExerciciosConcluidos/$totalExercicios", 36, "normal")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CodeupTheme {
        Card(false, 2, 10)
    }
}
