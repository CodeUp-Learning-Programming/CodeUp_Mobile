package com.example.codeup.ui.composables.card

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codeup.ui.composables.componentes.BotaoPretoBordaBranca
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.theme.CodeupTheme

@Composable
fun CardConclusaoExercicio(

    moedas: Int,
    xp: Int,
    onClickVoltar: () -> Unit,
) {
    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )

    Box(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
            .background(Color.Black)
            .border(1.dp, borderGradient, shape = RoundedCornerShape(8F))
    ) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxSize(),
        ) {
            //Segunda linha
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextoBranco(texto = "Parabéns por concluir essa Fase!", tamanhoFonte = 16,alinhamentoTexto = TextAlign.Center)
            }
            Column {
                //Parte e quantidade
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val mensagem ="Você conseguiu $moedas moedas e $xp de experiência por concluir essa fase"

                    TextoBranco(
                        texto = mensagem,
                        tamanhoFonte = 10,
                        alinhamentoTexto = TextAlign.Center
                    )

                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    BotaoPretoBordaBranca(text = "Ir para o RoadMap", onClick = onClickVoltar)

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CodeupTheme {
        CardConclusaoExercicio(moedas =5, xp= 100, onClickVoltar = {})
    }
}