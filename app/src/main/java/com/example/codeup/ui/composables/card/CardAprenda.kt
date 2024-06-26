package com.example.codeup.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
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
import com.example.codeup.ui.composables.componentes.BotaoAzul
import com.example.codeup.ui.composables.componentes.TextoBranco

@Composable
fun CardAprenda(
    tituloFase: String,
    desbloqueada: Boolean,
    qtdExerciciosFaseConcluidos: Int,
    qtdExerciciosFase: Int,
    onClick: () -> Unit,
    ) {

    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )

    Box(
        modifier = Modifier
            .width(270.dp)
            .height(170.dp)
            .background(Color.Black) // Cor de fundo do cartão
            .border(1.dp, borderGradient, shape = RoundedCornerShape(8F)) // Borda com gradiente
    ) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxSize(),
        ) {
            // Primeira linha
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextoBranco(texto = "APRENDA", tamanhoFonte = 12)

                val painter1: Painter = painterResource(id = R.drawable.icon_aprenda_selecionado)
                Image(
                    painter = painter1,
                    contentDescription = stringResource(R.string.text_descricao_materia),
                )
            }
            //Segunda linha
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                TextoBranco(texto = tituloFase, tamanhoFonte = 20, pesoFonte = "normal")
            }
            Column {
                //Parte e quantidade
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextoBranco(texto = "PARTE", tamanhoFonte = 10, pesoFonte = "normal")
                    TextoBranco(
                        texto = "$qtdExerciciosFaseConcluidos/$qtdExerciciosFase",
                        tamanhoFonte = 10,
                        pesoFonte = "normal"
                    )
                }
                //Barra de progresso
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LinearProgressIndicator(
                        progress = qtdExerciciosFaseConcluidos.toFloat() / qtdExerciciosFase.toFloat(),
                        modifier = Modifier
                            .fillMaxWidth(),
                        trackColor = Color(49, 49, 49),
                        color = Color(1, 167, 247),
                    )

                }

            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Row {
                BotaoAzul(
                    text = "CONTINUE APRENDENDO",
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    altura = 30,
                    largura = 20,
                    tamanhoFonte = 12,

                )
            }


        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun CardPreview() {
//    CodeupTheme {
//        CardAprenda(false, 2, 10)
//    }
//}
