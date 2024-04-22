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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.ItemLoja
import com.example.codeup.ui.composables.BotaoAzul
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.screens.viewmodels.LojaViewModel

@Composable
fun CardComprarItem(
    itemLoja: ItemLoja,
    onClick: () -> Unit,
    lojaViewModel: LojaViewModel
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
            .height(310.dp)
            .background(Color.Black)
            .border(1.dp, borderGradient, shape = RoundedCornerShape(8F))
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
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                ) {
                    AsyncImage(
                        model = itemLoja.fotoItem,
                        contentDescription = itemLoja.descricaoItem,
                    )
                }
            }
            //Segunda linha
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextoBranco(texto = itemLoja.nomeItem, tamanhoFonte = 20)
            }
            Column {
                //Parte e quantidade
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextoBranco(texto = stringResource(id = R.string.text_comprar_por) +" "+ itemLoja.precoItem.toInt() +" " + stringResource(
                        id = R.string.text_moedas), tamanhoFonte = 10)
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
                    text = stringResource(id = R.string.text_comprar).uppercase() , onClick = {
                        lojaViewModel.comprarItem(itemLoja.id)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    altura = 30,
                    largura = 20,
                    tamanhoFonte = 12
                )
                TextButton(onClick = onClick) {
                    TextoBranco(texto = stringResource(id = R.string.text_cancelar).uppercase(), tamanhoFonte =12 )
                }
            }


        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun CardPreview() {
//    CodeupTheme {
//        CardComprarItem(itemLoja = ItemLoja(1,"Artista","https://th.bing.com/th/id/OIG2.m_aPX_OomjraUjiN6Xux?pid=ImgGn","Imagem",20.0,"Artista",true))
//    }
//}