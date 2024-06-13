package com.example.codeup.ui.composables.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.composables.componentes.BotaoAzul
import com.example.codeup.ui.composables.componentes.TextoBranco

@Composable
fun CardReporteEnviado(
    onClickFechar: () -> Unit,
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
            .border(1.dp, borderGradient, shape = RoundedCornerShape(8F)),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .padding(start = 15.dp, end = 10.dp, bottom = 30.dp, top = 30.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Primeira linha

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextoBranco(texto = stringResource(id = R.string.text_reporte_enviado), tamanhoFonte = 24, alinhamentoTexto = TextAlign.Center)
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BotaoAzul(
                    text = stringResource(id = R.string.text_fechar).uppercase() , onClick = onClickFechar,
                    modifier = Modifier.fillMaxWidth(),
                    altura = 30,
                    largura = 20,
                    tamanhoFonte = 12
                )
            }


        }

    }

}
