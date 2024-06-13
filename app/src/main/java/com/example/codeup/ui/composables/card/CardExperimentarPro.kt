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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.composables.componentes.BotaoAzul
import com.example.codeup.ui.composables.componentes.TextoBranco

@Composable
fun CardExperimentarPro(
    onClickFechar: () -> Unit,
    onClickComprar: () -> Unit,
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
            .height(300.dp)
            .background(Color.Black)
            .border(1.dp, borderGradient, shape = RoundedCornerShape(8F)),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .padding(start = 15.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Primeira linha
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                val painter: Painter = painterResource(id = R.drawable.icon_sair)
                TextButton(onClick = onClickFechar) {
                    Image(
                        painter = painter,
                        contentDescription = stringResource(id = R.string.text_fechar)
                    )
                }
            }
            // Primeira linha
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                    val painter: Painter = painterResource(id = R.drawable.icon_coracoes_infinitos)

                    Image(
                        painter = painter,
                        contentDescription = null, // A descrição é opcional
                    )
            }
            //Segunda linha
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextoBranco(texto = "Vidas ilimitadas", tamanhoFonte = 20)
            }
            Column {
                //Parte e quantidade
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextoBranco(texto = stringResource(id = R.string.text_teste_gratuito), tamanhoFonte = 12, alinhamentoTexto = TextAlign.Center)
                    TextoBranco(
                        texto = "",
                        tamanhoFonte = 10,
                        pesoFonte = "normal"
                    )
                }


            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BotaoAzul(
                    text = stringResource(id = R.string.text_iniciar_teste).uppercase() , onClick = onClickComprar,
                    modifier = Modifier.fillMaxWidth(),
                    altura = 30,
                    largura = 20,
                    tamanhoFonte = 12
                )
            }


        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun CardPreview() {
//    CodeupTheme {
//        CardExperimentarPro()
//    }
//}